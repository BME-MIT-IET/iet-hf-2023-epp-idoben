package equipment;
import com.main.Virologist;
import timer.Steppable;
import com.main.Skeleton;

/**
 * Absztrakt osztály, a játékban a védőfelszerelést képviseli, amelyeket a virológusok felvehetnek az Óvóhelyekről,
 * és segiti a játékost.
 * Egybizonyos ideig érvényesek csak, egy idő után elkopnak.
 */
public abstract class Equipment implements Steppable{
    /**
     * A védőfelszerelés érvényességi ideje, ha eléri a 0-t, a felszerelés eltörik és a virológus elvesziti azt
     */
    protected int timetolive;
    /**
     * A védőfelszerelés tulajdonos virológusa
     */
    protected Virologist owner;
    /**
     * Az objektum azonositója
     */
    protected String id;

    /**
     * Védőfelszerelés konstruktora
     * @param ttl - A védőfelszerelés élettartama, ha eléri a 0-t, a védőfelszerelés eltörik
     * @param v - A védőfelszerelés tulajdonosa, ha értéke null, akkor a védőfelszerelés még senki tulajdonában nem áll, egy Óvóhely mezőn található.
     * @param name - Az objektum azonositoja
     */
    public Equipment(int ttl, Virologist v, String name){
        timetolive=ttl;
        owner=v;
        id=name;
    }

    /**
     * A védőfelszerelés tipusa alapján megmondja, hogy megvédi-e a tulajdonosát attól, hogy ágenst kenjenek rá
     * @return - véd-e ágens felkenés ellen, alapértelmezett értéke hamis
     */
    public boolean Protects(){
        Skeleton.functionCall("Equipment.Protects() ID: " + id);
        Skeleton.functionReturn("Equipment.Protects()  Return false");
        return false;
    }

    /**
     * A védőfelszerelés tipusa alapján megmondja, hogy visszadobható-e vele a tulajdonosára felkent ágens
     * @return - vissza lehet-e dobni az ágenst a küldőnek, alapértelmezett értéke hamis
     */
    public boolean ThrowBack(){
        Skeleton.functionCall("Equipment.ThrowBack() ID: " + id);
        Skeleton.functionReturn("Equipment.ThrowBack()  Return false");
        return false;
    }

    /**
     * A védőfelszerelés tipusa alapján megmondja, hogy hatására kétszer annyi nyersanyagot tud-e összegyűjteni
     * @return - nagyobb-e a nyersanyaggyűjtő képesség az eredetinél, alapértelmezett értéke hamis
     */
    public boolean DoubleResource(){
        Skeleton.functionCall("Equipment.DoubleResource() ID: " + id);
        Skeleton.functionReturn("Equipment.DoubleResource()  Return false");
        return false;
    }

    /**
     * A Steppable interface megvalósitása miatt kell,
     * Csökkenti a védőfelszerelés élettartamát, majd ellenőrzi, hogy az elérte-e a 0-t, ha igen akkor törli a felszerelést,
     * mert azt jeletni, hogy az elkopott
     */
    public void Step(){
        Skeleton.functionCall("Equipment.Step() ID: " + id);
        if (timetolive > 0){
            timetolive--;
        }
        if(timetolive == 0){
            owner.RemoveEq(this);
        }
        Skeleton.functionReturn("Equipment.Step()  Return");
    }

    /**
     * Beállitja a védőfelszerelés tulajdonosát
     * @param v - A felszerelés tulajdonosa
     */
    public void setOwner(Virologist v){
        Skeleton.functionCall("Equipment.setOwner() ID: " + id);
        owner=v;
        Skeleton.functionReturn("Equipment.setOwner()  Return");
    }

    /**
     * Visszaadja, hogy ki a védőfelszerelés tulajdonosa
     * @return - A védőfelszerelés tulajdonosa
     */
    public Virologist getOwner(){
        Skeleton.functionCall("Equipment.getOwner() ID: " + id);
        Skeleton.functionReturn("Equipment.getOwner()  Return");
        return owner;
    }

    /**
     * A felszerelés típusa alapján megmondja, hogy lehet-e ölni vele. Alap eseten false.
     * @return Igazat ad vissza, ha lehet ölni a felszereléssel
     */
    public boolean Kills(){
        return false;
    }

    /**
     * id lekérése
     * @return id
     */
    public String getId(){
        return id;
    }

    /**
     * Kiírás
     * @return kiírás
     */
    public String toString(){
        String s = getId();
        s+=("("+timetolive+")");
        return s;
    }


}
