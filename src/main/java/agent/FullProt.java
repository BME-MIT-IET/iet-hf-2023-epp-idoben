package agent;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Timer;

/**
 * FullProt osztály, a FullProt vakcina hatásaiért felel
 */
public class FullProt extends Agent{
    /**
     * A FullProt konstruktora
     * @param id - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param timeToLive - Az ágens élettartama, amennyiben azt valakire Effect-ként rakták rá
     * @param owner - Az effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     * @param nucleotidRequired - az ágens elkészítéséhez szükséges nukleotid szám
     * @param aminoacidRequired - az ágens elkészítéséhez szükséges aminosav szám
     */
    public FullProt(String id, int timeToLive, Virologist owner, int nucleotidRequired, int aminoacidRequired){
        super(id, timeToLive, owner, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Ezen Effect hatására védve van-e a Virológus
     * @return - true, mivel véd a hatása
     */
    public boolean Protected(){
        Skeleton.functionCall("FullProt.Protected()  ID: " + id);
        Skeleton.functionReturn("FullProt.Protected()  Return true");
        return true;
    }

    public Agent CreateEffectFor(Virologist v){
        return new FullProt(id, timetolive, v, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Lépteti az FullProt számlálóját, majd ha az lejárt, akkor törli a FullProt-t
     */
    public void Step(){
        Skeleton.functionCall("FullProt.Step()  ID: " + id);
        timetolive--;
        if(timetolive <= 0){
            Timer.getInstance().Remove(this);
            owner.RemoveEffect(this);
        }
        Skeleton.functionReturn("FullProt.Step()  Return");
    }
}
