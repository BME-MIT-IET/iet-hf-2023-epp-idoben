package field;
import resources.Aminoacid;
import resources.Resource;
import com.main.Skeleton;
import com.main.Virologist;

public class WareHouse extends Field {

    private Resource resources;

    public WareHouse(Resource res, String name, int x, int y){
        super(name, x, y);
        resources = res;
    }

    /**
     * Ezzel a metódussal lehet a mezőről felszedni a nyersanyagokat
     *
     * @param v a virológus, aki felszedi
     */
    public void Collect (Virologist v){
        Skeleton.functionCall("WareHouse.PickupResource(Virologist v)  ID: " + id);
        if(resources!=null) {
            if (v.PickupResource(resources))
                resources = null;
        }
        Skeleton.functionReturn("WareHouse.PickupResource(Virologist v)  Return");
    }

    /**
     * Ezzel a függvénnyel lehet elpusztítani a mezőn lévő nyersanyagokat
     */
    public void Destroy(){
        resources = null;
    }

    /**
     * Visszaadja a dolog nevét, ami megjelenik a grafikán
     * @return dolog neve
     */
    public String mark(){
        if (resources == null){
            return "";
        }
        if (resources.getClass() == Aminoacid.class){
            return "Ra";
        }
        return "Rn";
    }

    public String toString(){
        String s = super.toString();
        if(resources!=null){
            if(!s.equals(""))
                s+=";";
           s+= resources.toString();
        }
        return s;
    }
}