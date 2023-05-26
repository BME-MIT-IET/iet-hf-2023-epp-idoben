package agent;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Timer;

/**
 * Paralysis osztály, a paralysis vírus hatásaiért felel
 */
public class Paralysis extends Agent{
    /**
     * A Paralysis konstruktora
     * @param id - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param timeToLive - Az ágens élettartama, amennyiben azt valakire Effect-ként rakták rá
     * @param owner - Az effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     * @param nucleotidRequired - az ágens elkészítéséhez szükséges nukleotid szám
     * @param aminoacidRequired - az ágens elkészítéséhez szükséges aminosav szám
     */
    public Paralysis(String id, int timeToLive, Virologist owner, int nucleotidRequired, int aminoacidRequired){
        super(id, timeToLive, owner, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Ezen Effect hatására tud-e mozogni a virológus
     * @return - false, mivel bénít a hatása
     */
    @Override
    public boolean Moveable(){
        return false;
    }

    public Agent CreateEffectFor(Virologist v){
        return new Paralysis(id, timetolive, v, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Lépteti az Paralysis számlálóját, majd ha az lejárt, akkor törli a paralysis-t
     */
    @Override
    public void Step(){
        Skeleton.functionCall("Paralysis.Step()  ID: " + id);
        timetolive--;
        if(timetolive <= 0){
            Timer.getInstance().Remove(this);
            owner.RemoveEffect(this);
        }
        Skeleton.functionReturn("Paralysis.Step()  Return");
    }
}
