package resources;

/**
 * Aminoacid osztály, az Aminoacid nyersanyag kezeléséért felelős
 */
public class Aminoacid extends Resource{
    /**

     */
    public Aminoacid(){}

    public int availableAmino(){return 1;}

    public int availableNucleo(){return 0;}

    @Override
    public String toString() {
        return "Ra";
    }
}
