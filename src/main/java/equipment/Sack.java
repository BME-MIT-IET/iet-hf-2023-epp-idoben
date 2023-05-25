package equipment;

import com.main.Skeleton;
import com.main.Virologist;
/**
 * A játékban a Zsák tipusú védofelszerelést képviseli, ha valamelyik virológus birtokolja, akkor kétszeres az anyaggyűjtő képessége
 */
public class Sack extends Equipment{
    /**
     * Zsák konstruktora
     * @param ttl - A zsák élettartama, ha eléri a 0-t, a zsák eltörik
     * @param v - A zsák tulajdonosa, ha értéke null, akkor a zsák még senki tulajdonában nem áll, egy Óvóhely mezőn található.
     * @param name - Az objektum azonositoja
     */
    public Sack(int ttl, Virologist v, String name){
        super(ttl,v,name);
    }

    /**
     * A zsák tulajdonosa kétszeres anyaggyűjtő képességgel rendelkezik, ezen függvény segitségével lekérdezhető, hogy a zsák kifejti-e
     * ezt a hatást
     * @return - igazzal tér vissza
     */
    @Override
    public boolean DoubleResource() {
        Skeleton.functionCall("Sack.DoubleResource() ID: " + id);
        Skeleton.functionReturn("Sack.DoubleResource()  Return true");
        return true;
    }
}
