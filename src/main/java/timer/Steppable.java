package timer;

/**
 * Interface, amely játékban a léptethető objektumokat képviseli - effect, equipment
 */
public interface Steppable {
    /**
     * A léptető függvény, amellyel minden ezen interface-t megvalósitó osztály rendelkezik
     */
    void Step();
}
