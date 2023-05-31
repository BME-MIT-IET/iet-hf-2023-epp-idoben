package equipment;

import com.main.Virologist;

public class Axe extends Equipment{
    /**
     * Balta konstruktora
     *
     * @param v    - A balta tulajdonosa, ha értéke null, akkor a védőfelszerelés még senki tulajdonában nem áll, egy Óvóhely mezőn található.
     * @param name - Az objektum azonosítója
     */
    public Axe(Virologist v, String name) {
        super(-1, v, name);
    }

    /**
     * Ezzel a függvénnyel lehet ellenőrizni, hogy a felszereléssel lehet-e ölni, balta esetén igazat ad vissza
     *
     * @return Igazat ad vissza, mivel a baltával lehet ölni
     */
    @Override
    public boolean Kills(){
        return timetolive==-1;
    }

    /**
     * Ezzel a függvénnyel lehet elhasználni a baltát, ekkor eltávolítódik a virológustól és a Timerből
     */
    public void Use(){
        timetolive=-2;
    }
}
