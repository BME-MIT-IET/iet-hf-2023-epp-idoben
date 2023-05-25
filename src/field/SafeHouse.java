package field;
import com.main.Virologist;
import equipment.Equipment;

public class SafeHouse extends Field{

    private Equipment equipment;

    public SafeHouse(String name, Equipment e, int x, int y){
        super(name, x, y);
        equipment = e;
    }

    public void Collect (Virologist v){
        com.main.Skeleton.functionCall("SafeHouse.Collect(Virologist v)");
        if(equipment!=null) {
            if (v.PickupEq(equipment))
                equipment = null;
        }
        com.main.Skeleton.functionReturn("SafeHouse.Collect(Virologist v)  Return");
    }

    /**
     * Visszaadja a dolog nevét, ami megjelenik a grafikán
     * @return dolog neve
     */
    public String mark(){
        if (equipment == null){
            return "";
        }
        return equipment.getId();
    }

    public String toString(){
        String s = super.toString();
        if (equipment!=null){
            if(!s.equals("")){
                s+=";";
            }
            s+= equipment.getId();
        }
        return s;
    }
}
