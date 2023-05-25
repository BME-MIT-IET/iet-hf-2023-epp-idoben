package resources;

/**
 * Nucleotid osztály, a nukleotid nyersanyag kezeléséért felelős
 */
public class Nucleotid extends Resource{
    /**

     */
    public Nucleotid(){}

    public int availableAmino(){
        return 0;
    }

    public int availableNucleo(){
        return 1;
    }

    @Override
    public String toString() {
        return "Rn";
    }
}
