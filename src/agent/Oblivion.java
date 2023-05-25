package agent;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Timer;

/**
 * Oblivion osztály, a Oblivion vírus hatásaiért felel
 */
public class Oblivion extends Agent{
    /**
     * A Oblivion konstruktora
     * @param id - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param owner - Az effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     * @param nucleotidRequired - az ágens elkészítéséhez szükséges nukleotid szám
     * @param aminoacidRequired - az ágens elkészítéséhez szükséges aminosav szám
     */
    public Oblivion(String id, Virologist owner, int nucleotidRequired, int aminoacidRequired){
        super(id, 1, owner, nucleotidRequired, aminoacidRequired);
    }

    public Agent CreateEffectFor(Virologist v){
        return new Oblivion(id, v, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Lépteti az Oblivion számlálóját, majd ha az lejárt, akkor törli a Oblivion-t
     */
    public void Step(){
        Skeleton.functionCall("Oblivion.Step()  ID: " + id);
        timetolive--;
        if(timetolive <= 0){
            owner.Forget();
            Timer.getInstance().Remove(this);
            owner.RemoveEffect(this);
        }
        Skeleton.functionReturn("Oblivion.Step()  Return");
    }
}
