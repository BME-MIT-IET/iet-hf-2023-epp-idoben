package field;
import agent.Agent;
import agent.BearVirus;
import com.main.Virologist;

public class Lab extends Field {

    private Agent geneticCode;

    private boolean hasBearVirus;

    /**
     * Konstruktor, beállítja az objektum nevét
     *
     * @param name a neve
     */
    public Lab(String name, Agent a, boolean b,  int x, int y){
        super(name, x, y);
        geneticCode = a;
        hasBearVirus = b;
    }

    /**
     * Ezzel a függvénnyel lehet megtanulni a laborban lévő genetikai kódot
     *
     * @param v Virológus, aki tanulja a kódot
     */
    public void Collect (Virologist v){
        com.main.Skeleton.functionCall("Lab.Collect(Virologist v)  ID: " + id);
        v.LearnGenCode(geneticCode);
        com.main.Skeleton.functionReturn("Lab.Collect(Virologist v)  Return");
    }

    /**
     * Ez a függvény abban különbözik a ős Accept függvényétől, hogy, ha van medvevírus a mezőn akkor azzal megfertőzi a rálépő virológust
     *
     * @param v a virológus
     */
    public void Accept(Virologist v){
        super.Accept(v);
        if (hasBearVirus && !v.HasBearVirus()){
            v.ReceiveEffectFrom(null, new BearVirus("Cb", v));
            v.MoveRandom();
        }
    }

    /**
     * Visszaadja a dolog nevét, ami megjelenik a grafikán
     * @return dolog neve
     */
    public String mark(){
        if (this.hasBearVirus){
            return "Cb";
        }
        if (geneticCode == null){
            return "";
        }
        return geneticCode.getId();
    }

    @Override
    public String toString(){
        String s = super.toString();
        if(geneticCode!=null){
            if(!s.equals(""))
                s+= ";";
            s+=(geneticCode.getId());
        }
        if(hasBearVirus){
            if(!s.equals(""))
                s+= ";";
            s+="Cb";
        }
        return s;
    }
}