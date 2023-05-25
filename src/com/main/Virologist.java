package com.main;

import agent.Agent;
import agent.BearVirus;
import agent.Effect;
import equipment.Axe;
import equipment.Equipment;
import equipment.Gloves;
import field.Field;
import resources.Resource;
import timer.Timer;

import java.util.ArrayList;
import java.util.HashSet;

public class Virologist {
    /**
     * A virológus felszereléseit tárolja
     */
    private ArrayList<Equipment> equipment = new ArrayList<Equipment>();
    /**
     * A mező amin a virológus áll
     */
    private Field field;
    /**
     * A virológusra aktavan ható effektek
     */
    private ArrayList<Effect> activeeffects = new ArrayList<Effect>();
    /**
     * A már megtanult ágensek
     */
    private HashSet<Agent> knownagents = new HashSet<Agent>();
    /**
     * A nyersanyagok heterogén kollekciója
     */
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    /**
     * Kenéshez szükséges amino mennyiség
     */
    private final int requiredAmino = 5;
    /**
     * Kenéshez szükséges nucleo mennyiség
     */
    private final int requiredNucleo = 8;
    /**
     * A maximális nyersanyag szám
     */
    private final int maxResourceCount = 10;
    /**
     * A maximális felszerelés szám
     */
    private final int maxEquipmentCount = 3;
    /**
     * A maximális ágens szám
     */
    private final int maxAgentCount = 4;
    /**
     * Az objektum azonosítója
     */
    private String id;
    /**
     * Ez a tagváltozó jelzi, hogy a virológus halott-e
     */
    public boolean dead = false;

    /**
     * A virológus konstruktora
     * @param name az adott objektum neve
     */
    public Virologist(String name){
        id = name;
        Timer.getInstance().Add(this);
    }

    /**
     * Lépteti a virológus összes dolgát
     */
    public void Step(){
        ArrayList<Equipment> tmpeq = new ArrayList<>(equipment);
        for (Equipment e : tmpeq){
            e.Step();
        }
        ArrayList<Effect> tmpeff = new ArrayList<>(activeeffects);
        for (Effect e : tmpeff){
            e.Step();
        }
    }

    /**
     * A virológus léptetése n számmal jelzett irányba
     * @param n az irányt jelölő szám
     */
    public void Move(String n){
        Skeleton.functionCall("Virologist.Move(n: int)  ID: " + id);
        if (this.Moveable()){
            field.Move(this, n);
        }
        Skeleton.functionReturn("Virologist.Move(n: int)  Return");
    }

    /**
     * Visszaadja hogy van e medve vírus rajta
     * @return van-e
     */
    public boolean HasBearVirus(){
        for (Effect e : activeeffects) {
            if (e.SpreadBearVirus()) {
                return true;
            }
        }
        return false;
    }

    /**
     * A virológusnak megadja az új helyét
     * @param f új mező amin áll a virológus
     */
    public void Accept(Field f, HashSet<Virologist> vs){
        Skeleton.functionCall("Virologist.Accept(f: Field)  ID: " + id);
        this.field = f;
        for (Effect e : activeeffects){
            if (e.SpreadBearVirus()){
                for (Virologist v : vs){
                    if(v!=this) {
                        v.ReceiveEffectFrom(this, ((BearVirus) e).CreateEffectFor(v));
                    }
                }
                f.Destroy();
                break;
            }
        }
        Skeleton.functionReturn("Virologist.Accept(f: Field)  Return");
    }

    /**
     * Véletlenszerű irányba lépteti a virológust
     */
    public void MoveRandom(){
        Skeleton.functionCall("Virologist.MoveRandom()  ID: " + id);
        Game.enableActions(false);
        if (this.Moveable()){
            int stepsNeeded = Game.remainingSteps;
            for (int i = 0; i < stepsNeeded; i++){
                field.Move(this, field.GetRandomNeighbor());
                Game.updateGame();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Game.enableActions(true);
        Skeleton.functionReturn("Virologist.MoveRandom()  Return");
    }

    /**
     * Visszaadja, hogy az adott virológus tud-e mozogni
     * @return tud-e mozogni
     */
    public boolean Moveable(){
        Skeleton.functionCall("Virologist.Moveable()  ID: " + id);
        for (Effect ef : activeeffects){
            if (!ef.Moveable()){
                Skeleton.functionReturn("Virologist.Moveable()  Return false");
                return false;
            }
        }
        Skeleton.functionReturn("Virologist.Moveable()  Return true");
        return true;
    }

    /**
     * Egy virológusra lehet ezzel a metódussal effektet rakni (másik virológustól kapva), feltéve, hogy a célzott nem védett
     * @param v effekt küldője
     * @param e küldött effekt
     */
    public void ReceiveEffectFrom(Virologist v, Effect e){
        Skeleton.functionCall("Virologist.ReceiveEffectFrom(v: Virologist, e: Effect)  ID: " + id);
        if(v!=null && e.getClass()==BearVirus.class && HasBearVirus()){
            Skeleton.functionReturn("Virologist.ReceiveEffectFrom(v: Virologist, e: Effect)  Return");
            return;
        }
        boolean throwback = false;
        Equipment glove = null;
        for (Equipment eq : equipment){
            throwback |= eq.ThrowBack();
            if(eq.ThrowBack())
                glove = eq;
        }
        if (throwback && v != null){
            v.ReceiveEffectFrom(this, e);
            e.NewOwner(v);
            ((Gloves)glove).Use();

            Skeleton.functionReturn("Virologist.ReceiveEffectFrom(v: Virologist, e: Effect)  Return");
            return;
        }
        boolean protect = false;
        for (Equipment eq : equipment){
            protect |= eq.Protects();
        }
        for (Effect ef : activeeffects){
            protect |= ef.Protected();
        }
        if (!protect){
            this.AddEffect(e);
        }
        Skeleton.functionReturn("Virologist.ReceiveEffectFrom(v: Virologist, e: Effect)  Return");
    }

    /**
     * Ezzel a metódussal indítható egy másik virológusra a kenés, ez akkor történik meg, ha egy mezőn állnak és elegendő a nyersanyag
     * @param v célzott virológus
     * @param a kenni kívánt ágens
     */
    public void ThrowAgentOn(Virologist v, Agent a){
        Skeleton.functionCall("Virologist.ThrowAgentOn(v: Virologist, a: Agent)  ID: " + id);
        int amino = 0;
        int nucleo = 0;
        for(Resource r : resources){
            amino += r.availableAmino();
            nucleo += r.availableNucleo();
        }

        if (field.IsHere(v) && amino >= a.aminoacidRequired && nucleo >= a.nucleotidRequired){
            int useamino = a.aminoacidRequired;
            int usenucleo = a.nucleotidRequired;
            ArrayList<Resource> resources_copy =  new ArrayList<>(resources);
            for(Resource r : resources_copy){
                if (r.availableAmino() == 1 && useamino > 0){
                    resources.remove(r);
                    useamino--;
                }
                if (r.availableNucleo() == 1 && usenucleo > 0){
                    resources.remove(r);
                    usenucleo--;
                }
            }
            a.UseOn(v, this);
        }
        Skeleton.functionReturn("Virologist.ThrowAgentOn(v: Virologist, a: Agent)  Return");
    }

    /**
     * Ezzel a metódussal lehet eltávolítani egy effektet a virológustól
     * @param e eltávolítandó effekt
     */
    public void RemoveEffect(Effect e){
        Skeleton.functionCall("Virologist.RemoveEffect(e: Effect)  ID: " + id);
        activeeffects.remove(e);
        Skeleton.functionReturn("Virologist.RemoveEffect(e: Effect)  Return");
    }

    /**
     * Effekt hozzáadása a virológushoz
     * @param e hozzáadni kívánt effekt
     */
    public void AddEffect(Effect e){
        Skeleton.functionCall("Virologist.AddEffect(e: Effect)  ID: " + id);
        activeeffects.add(e);
        Skeleton.functionReturn("Virologist.AddEffect(e: Effect)  Return");
    }

    /**
     * Eszközrablás függvénye, ezzel lehet egy másik virológustól kezdeményezni az eszköz rablását
     * @param v célzott virológus
     * @param e rablásra kiválasztott felszerlés
     */
    public void StealEqFrom(Virologist v, Equipment e){
        Skeleton.functionCall("Virologist.StealEqFrom(v: Virologist, e: Equipment)  ID: " + id);
        if (this.Moveable() && field.IsHere(v)){
            v.BeingRobbed(this, e);
        }
        Skeleton.functionReturn("Virologist.StealEqFrom(v: Virologist, e: Equipment)  Return");
    }

    /**
     * A virológust kirabolja egy másik virológus, és elveszi tőle az egyik felszerelését
     * @param v - a virológus, aki kirabolja
     * @param e - a felszerelés, amit ellop
     */
    public void BeingRobbed(Virologist v, Equipment e){
        Skeleton.functionCall("Virologist.BeingRobbed(v: Virologist, e: Equipment)  ID: " + id);
        if(!Moveable()){
            if(v.PickupEq(e)){
                RemoveEq(e);
            }
        }
        Skeleton.functionReturn("Virologist.BeingRobbed(v: Virologist, e: Equipment)  Return");
    }

    /**
     * Töröl egy felszerelést
     * @param e - a felszerelés amit töröl
     */
    public void RemoveEq(Equipment e){
        Skeleton.functionCall("Virologist.RemoveEq(e: Equipment)  ID: " + id);
        equipment.remove(e);
        Skeleton.functionReturn("Virologist.RemoveEq(e: Equipment)  Return");
    }

    /**
     * Megpróbál felvenni egy felszerelést, amennyiben elfér még nála
     * @param e - a felszerelés
     * @return - igaz, amennyiben sikeresen felvette
     */
    public boolean PickupEq(Equipment e){
        Skeleton.functionCall("Virologist.PickupEq(e: Equipment): boolean  ID: " + id);
        if(equipment.size()<maxEquipmentCount){
            equipment.add(e);
            e.setOwner(this);
            Skeleton.functionReturn("Virologist.PickupEq(e: Equipment): boolean  Return: true");
            return true;
        }
        Skeleton.functionReturn("Virologist.PickupEq(e: Equipment): boolean  Return: false");
        return false;
    }

    /**
     * Megtanulja a mezőn lévő kódot (ágenst), amennyiben ez volt az utolsó megtanulanó ágens, úgy véget ér a játék
     * @param c - a magtanulandó ágens
     */
    public void LearnGenCode(Agent c){
        Skeleton.functionCall("Virologist.LearnCode(c: Agent)  ID: " + id);
        knownagents.add(c);
        if(knownagents.size()==maxAgentCount){
            Game.EndGame(id);
        }
        Skeleton.functionReturn("Virologist.LearnCode(c: Agent)  Return");
    }

    /**
     * Felveszi a nyersanyagot, amennyiben elfér nála
     * @param r - a felvevendő nyersanyag
     * @return - sikeres volt-e a felvétele
     */
    public boolean PickupResource(Resource r){
        Skeleton.functionCall("Virologist.PickupResource(r: Resource): boolean  ID: " + id);
        int cnt = 0;
        for(Resource tmp : resources){
            if(tmp.getClass()==r.getClass()){
                cnt++;
            }
        }
        int doubleNeeded = 1;
        for(Equipment tmp : equipment){
            if(tmp.DoubleResource()){
                doubleNeeded=2;
                break;
            }
        }
        if(cnt<maxResourceCount*doubleNeeded){
            resources.add(r);
            Skeleton.functionReturn("Virologist.PickupResource(r: Resource): boolean  Return: true");
            return true;
        }
        Skeleton.functionReturn("Virologist.PickupResource(r: Resource): boolean  Return: false");
        return false;
    }

    /**
     * Felveszi/megtanulja a mezőn lévő felvehető/megtanulható dolgokat
     */
    public void Collect(){
        Skeleton.functionCall("Virologist.Collect()  ID: " + id);
        field.Collect(this);
        Skeleton.functionReturn("Virologist.Collect()  Return");
    }

    /**
     * Elfelejti az összes eddig megtanult genetikai kódot
     */
    public void Forget(){
        Skeleton.functionCall("Virologist.Forget()  ID: " + id);
        knownagents.clear();
        Skeleton.functionReturn("Virologist.Forget()  ID: " + id);
    }

    /**
     * Ezzel a metódussal lehet eldobni felszerelést, ami törlődik a virológustól
     *
     * @param e Az eldobásra szánt felszerelés
     */
    public void ThrowEq(Equipment e){
        this.RemoveEq(e);
    }

    /**
     * Ezzel a függvénnyel lehet megölni egy másik virológust feltéve, hogy van hozzá baltája és tud mozogni
     *
     * @param v célzott virológus
     */
    public void Kill(Virologist v){
        if (!field.IsHere(v) || !this.Moveable()){
            return;
        }

        for (Equipment e : equipment){
            if (e.Kills()){
                v.GetKilled();
                ((Axe)e).Use();
                break;
            }
        }
    }

    /**
     * visszaadja a virológus jelenlegi mezőjét
     * @return
     */
    public Field getField(){
        return this.field;
    }

    /**
     * Visszaadja az i-s sorszámú felszerelést
     * @param i a felszerelés indexe
     * @return a felszerelés
     */
    public Equipment getEquipment(int i){
        if(equipment.size()>i)
                return equipment.get(i);
        else return null;
    }

    /**
     * Ezzel a függvénnyel lehet megölni az adott virológust
     */
    public void GetKilled(){
        dead = true;
        Game.virologists.remove(this.id);
        field.Remove(this);
    }

    /**
     * Visszaadja a virológus azonosítóját
     * @return az azonosító
     */
    public String getId(){
        return id;
    }

    /**
     * Kiírja a virológust és annak a dolgait
     * @return a string
     */
    public String toString(){
        String s = "";
        String sub = "";
        if(equipment.size()==0){
            s+="-";
        }
        for(Equipment e : equipment){
            if(!sub.equals(""))
                sub+=";";
            sub+=e.toString();
        }
        s+=sub + " ";
        sub="";

        if(activeeffects.size()==0){
            s+="-";
        }
        for(Effect e : activeeffects){
            if(!sub.equals(""))
                sub+=";";
            sub+=e.toString();
        }
        s+=sub + " ";
        sub="";

        if(knownagents.size()==0){
            s+="-";
        }
        for(Agent e : knownagents){
            if(!sub.equals(""))
                sub+=";";
            sub+=e.getId();
        }
        s+=sub + " ";
        sub="";

        int ra=0;
        int rn=0;
        for(Resource r : resources){
            if(r.toString().equals("Ra"))
                ra++;
            else rn++;
        }
        s+=(ra+";"+rn+" ");
        return s;
    }


    /**
     * Visszaadja a virológus felszereléseinek a listáját
     * @return lista
     */
    public ArrayList<String> getEquipments(){
        ArrayList<String> s = new ArrayList<>();
        for(Equipment e : equipment){
            s.add(e.toString());
        }
        return s;
    }


    /**
     * Visszaadja a megtanult kódok listáját
     * @return lista
     */
    public ArrayList<String> getCodes(){
        ArrayList<String> s = new ArrayList<>();
        for(Agent c : knownagents){
            s.add(c.getId());
        }
        return s;
    }


    /**
     * Visszaadja a paraméterül kapott ágens névhez tartozó referenciát
     * @param s a neve
     * @return az ágens referencia
     */
    public Agent getAgent(String s){
        for(Agent a : knownagents){
            if(a.getId().equals(s))
                return a;
        }
        return null;
    }
}
