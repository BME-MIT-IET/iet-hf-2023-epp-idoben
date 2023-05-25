package agent;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Steppable;

/**
 * Effect absztrakt osztály, ezt kapja a virológus mint a rá kent ágens hatása, egy idő után lekopik
 */
public abstract class Effect implements Steppable {
    /**
     * A Effect ennyi ideig marad meg, amennyiben 0-ra csökken, akkor az Effect elveszik
     */
    int timetolive;
    /**
     * Az Effect hatása alatt álló virológus
     */
    Virologist owner;
    /**
     * Ezen objektum azonosítója
     */
    protected String id;

    /**
     * Az effekt konstruktora
     * @param id - Az objektum azonosítója
     * @param timeToLive - Az Effect élettartalma
     * @param owner - Az Effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     */
    public Effect(String id, int timeToLive, Virologist owner){
        this.timetolive = timeToLive;
        this.owner = owner;
        this.id = id;
    }

    /**
     * Ezen Effect hatására tud-e mozogni a virológus
     * @return - igaz, amennyiben tud
     */
    public boolean Moveable(){
        return true;
    }

    /**
     * Ezen Effect hatására f ertőzött-e medvevírussal és terjeszteni kell-e
     * @return - igaz, amennyiben fertőzött
     */
    public boolean SpreadBearVirus(){
        return false;
    }

    /**
     * Ezen Effect hatására védett lesz-e a virológus
     * @return
     */
    public boolean Protected(){
        Skeleton.functionCall("FullProt.Protected()  ID: " + id);
        Skeleton.functionReturn("FullProt.Protected()  Return false");
        return false;
    }

    public void NewOwner(Virologist v){
        owner = v;
    }

    /**
     * Lépteti az Effect számlálóját, majd ha az lejárt, akkor törli az Effect-et
     */
    public void Step(){
        Skeleton.functionCall("Effect.Step()  ID: " + id);
        if (timetolive > 0){
            timetolive--;
        }
        if(timetolive == 0){
            owner.RemoveEffect(this);
        }
        Skeleton.functionReturn("Effect.Step()  Return");
    }

    public String getId(){
        return id;
    }

    public String toString(){
        String s=getId();
        s+=("("+timetolive+")");
        return s;
    }
}
