package equipment;
import com.main.Game;
import com.main.Skeleton;
import com.main.Virologist;

import java.util.Random;

/**
 * A játékban a Védőköpeny tipusú védofelszerelést képviseli, ha valamelyik virológus birtokolja, akkor 83.2 százalékban megvédi
 * attól, hogy ágenst kenjenek rá.
 */
public class ProtSuit extends Equipment{
    /**
     * A Védőfelszerelés konstruktora
     * @param ttl - A védőfelszerelés élettartama, ha eléri a 0-t, a védőfelszerelés eltörik
     * @param v - A védőfelszerelés tulajdonosa, ha értéke null, akkor a védőfelszerelés még senki tulajdonában nem áll, egy Óvóhely mezőn található.
     * @param name - Az objektum azonositoja
     */
    public ProtSuit(int ttl, Virologist v,String name){
        super(ttl,v,name);
    }

    /**
     * A védőköpeny 83.2 százalékos védelmet biztosit, ezen függvény segitségével lekérdezhető, hogy éppen megvédi-e a virológust, vagy nem
     * @return - A köpeny hatásos-e, vagy nem védi meg viselőjét
     */
    @Override
    public boolean Protects() {
        Skeleton.functionCall("ProtSuit.Protects() ID: " + id);
        Random rand = new Random();
        int r = rand.nextInt(1000);
        if (r <= (Game.random ? 823 : 1001)){
            Skeleton.functionReturn("ProtSuit.Protects()  Return true");
            return true;
        }
        Skeleton.functionReturn("ProtSuit.Protects()  Return false");
        return false;
    }
}
