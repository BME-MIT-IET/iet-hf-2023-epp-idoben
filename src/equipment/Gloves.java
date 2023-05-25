package equipment;

import com.main.Skeleton;
import com.main.Virologist;
import timer.Timer;

/**
 * A játékban a Kesztyű tipusú védofelszerelést képviseli, ha valamelyik virológus birtokolja, akkor a rákent ágenseket visszadobja a
 * küldő virológusra
 */
public class Gloves extends Equipment{
    int uses;

    /**
     * Kesztyű konstruktora
     * @param ttl - A kesztyű élettartama, ha eléri a 0-t, a kesztyű eltörik
     * @param u - A kesztű hátralévő használatainak a száma
     * @param v - A kesztyű tulajdonosa, ha értéke null, akkor a kesztyű még senki tulajdonában nem áll, egy Óvóhely mezőn található.
     * @param name - Az objektum azonositoja
     */
    public Gloves(int ttl, int u, Virologist v, String name){
        super(ttl,v, name);
        uses = u;
    }

    /**
     * Ezzel a függvénnyel lehet elhasználni a kesztyűt. Az utolsó használata után törlődik
     */
    public void Use(){
        uses--;
        if (uses == 0){
            owner.RemoveEq(this);
            Timer.getInstance().Remove(this);
        }
    }

    /**
     * A kesztyű hatására a viselője minden rá kent ágenst visszadob a küldőnek, ezen függvény segitségével tudjuk lekérdezni, hogy
     * visszadobja-e az ágenst, a válasz mindig igen.
     * @return - igazat térit vissza
     */
    @Override
    public boolean ThrowBack() {
        Skeleton.functionCall("Gloves.ThrowBack() ID: " + id);
        Skeleton.functionReturn("Gloves.ThrowBack()  Return true");
        return true;
    }

    @Override
    public String toString(){
        String s = getId();
        s+=("("+uses+")");
        return s;
    }
}
