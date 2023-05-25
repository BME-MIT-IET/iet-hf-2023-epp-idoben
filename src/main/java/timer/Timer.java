package timer;
import com.main.Skeleton;
import com.main.Virologist;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton osztály, a játékban az időzitőt képviseli. Másodpercenként meghivódik a Tick függvénye, és lépteti a játékban az összes
 * léptethető dolgot.
 */
public class Timer {
    /**
     * Az egyetlen példány, amit mindig visszaadunk, ha valaki meghivja a getInstanceof() függvényt
     */
    private static Timer single_instance_timer=null;
    /**
     * A léptethető dolgok listája
     */
    private List<Virologist> steppables;

    /**
     * A Timer konstruktora, mivel singleton osztály, ezért privát.
     */
    private Timer(){
        steppables = new ArrayList<>();
    }

    /**
     * Visszaadja a singleton Timer egy példányát. Ha még nem létezik a Timer, akkor létrehozza a konstruktor segitségével, és
     * hozzárendel egy taszkot a java.util.Timer tipusu timerhez, amelyet másodpercenként végrehajt, periodikusan.
     * Ez most a Tick() függvény lesz, minden óraketyegésre végrehajtódik.
     * @return A Timer egy példánya
     */
    public static Timer getInstance(){
        if(single_instance_timer==null)
        {
            single_instance_timer = new Timer();
        }
        return single_instance_timer;
    }

    /**
     * A Timer minden ketyegésére, azaz másodpercenként lépteti az eltárolt léptethető dolgokat a játékban.
     */
    public void Tick(Virologist v){
        v.Step();
    }

    /**
     * Hozzáad új léptethető dolgot a léptethető dolgok listájához
     * @param s - új léptethető dolog, amely bekerül a listába
     */
    public void Add(Virologist s)
    {
        Skeleton.functionCall("Timer.Add(Steppable s)  ID: Timer");
        steppables.add(s);
        Skeleton.functionReturn("Timer.Add(Steppable s)  Return");
    }

    /**
     * Eltávolitja a paraméterül kapott léptethető dolgot, a léptethető dolgok listájából.
     * @param s - Az eltávolitandó léptethető dolog
     */
    public void Remove(Steppable s)
    {
        Skeleton.functionCall("Timer.Remove(Steppable s)  ID: Timer");
        steppables.remove(s);
        Skeleton.functionReturn("Timer.Remove(Steppable s)  Return");
    }
}
