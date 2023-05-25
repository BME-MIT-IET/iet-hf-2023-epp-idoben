package resources;

/**
 * Resource osztály, a nyersanyagok gyűjtőosztálya
 */
public abstract class Resource{
    /**

     */
    public Resource(){}

    public abstract int availableAmino();

    public abstract int availableNucleo();

    abstract public String toString();

}
