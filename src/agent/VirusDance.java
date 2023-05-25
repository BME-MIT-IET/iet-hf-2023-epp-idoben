package agent;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Timer;

/**
 * VirusDance osztály, a VirusDance vírus hatásaiért felel
 */
public class VirusDance extends Agent{
    /**
     * A VirusDance konstruktora
     * @param id - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param owner - Az effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     * @param nucleotidRequired - az ágens elkészítéséhez szükséges nukleotid szám
     * @param aminoacidRequired - az ágens elkészítéséhez szükséges aminosav szám--
     */
    public VirusDance(String id, Virologist owner, int nucleotidRequired, int aminoacidRequired){
        super(id, 1, owner, nucleotidRequired, aminoacidRequired);
    }


    public Agent CreateEffectFor(Virologist v){
        return new VirusDance(id, v, nucleotidRequired, aminoacidRequired);
    }

    /**
     * Lépteti az VirusDance számlálóját, majd ha az lejárt, akkor törli a VirusDance-t
     */
    public void Step(){
        Skeleton.functionCall("VirusDance.Step()  ID: " + id);
        timetolive--;
        if(timetolive <= 0){
            owner.MoveRandom();
            Timer.getInstance().Remove(this);
            owner.RemoveEffect(this);
        }
        Skeleton.functionReturn("VirusDance.Step()  Return");
    }
}
