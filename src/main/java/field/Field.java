package field;

import com.main.Skeleton;
import com.main.Virologist;

import java.util.*;

public abstract class Field {
    /**
     * A mező elhelyezkedése
     */
    public int x, y;

    /**
     * A szomszédok
     */
    HashMap<String, Field> neighbours = new HashMap<>();
    /**
     * A mezőn lévő virológusok
     */
    public HashSet<Virologist> virologist = new HashSet<>();
    /**
     * Az objektum azonosítója
     */
    protected String id;

    /**
     * Konstruktor, beállítja az objektum nevét
     * @param name a neve
     */
    protected Field(String name, int x, int y){
        this.x = x;
        this.y = y;
        id = name;
    }

    /**
     * Felszedi ezen a mezőn lévő dolgokat
     * @param v a virológus, aki felszedi
     */
    public void Collect(Virologist v){}

    /**
     * Ezzel a függvénnyel lehet elpusztítani a mezőn lévő nyersanyagokat. Csak raktár esetén hatásos
     */
    public void Destroy(){}

    /**
     * Visszaadja az adott sorszámú szomszédot
     * @param n a szomszéd sorszáma
     * @return a szomszéd
     */
    public Field GetNeighbor(String n) {
        Skeleton.functionCall("Field.GetNeighbour(n: int): Field  ID: " + id);
        Skeleton.functionReturn("Field.GetNeighbour(n: int): Field  Return: Field");
        return neighbours.get(n);
    }

    /**
     * Visszaadja egy random szomszédját
     * @return random szomszéd
     */
    public String GetRandomNeighbor(){
        Set<String> keySet = neighbours.keySet();
        ArrayList<String> tmp = new ArrayList<String>(keySet);
        Random rnd = new Random();
        return tmp.get(rnd.nextInt(tmp.size()));
    }


    public String getId(){
        return id;
    }

    /**
     * Elfogadja a virológust, erről a virológusnak is szól
     * @param v a virológus
     */
    public void Accept (Virologist v){
        Skeleton.functionCall("Field.Accept(v: Virologist)  ID: " + id);
        virologist.add(v);
        v.Accept(this, virologist);
        Skeleton.functionReturn("Field.Accept(v: Virologist)  Return");
    }

    /**
     * Törli magáról az egyik virológust
     * @param v a virológus
     */
    public void Remove (Virologist v){
        Skeleton.functionCall("Field.Remove(v: Virologist)  ID: " + id);
        virologist.remove(v);
        Skeleton.functionReturn("Field.Remove(v: Virologist)  Return");
    }

    /**
     * Mozgatja a virológust az adott sorszámú szomszédra
     * @param v a virológus
     * @param n a szomszéd sorszáma
     */
    public void Move (Virologist v, String n){
        Skeleton.functionCall("Field.Move(v: Virologist, n: int)  ID: " + id);
        if (neighbours.get(n) == null){
            return;
        }
        Remove(v);
        neighbours.get(n).Accept(v);
        Skeleton.functionReturn("Field.Move(v: Virologist, n: int)  Return");
    }

    /**
     * Ellenőrzi, ezen a mezőn van-e a virológus
     * @param v a virológus
     * @return itt van-e
     */
    public boolean IsHere(Virologist v){
        Skeleton.functionCall("Field.IsHere(v: Virologist): boolean  ID: " + id);
        Skeleton.functionReturn("Field.IsHere(v: Virologist): boolean  Return: " + virologist.contains(v));
        return virologist.contains(v);
    }

    /**
     * Hozzáad egy új szomszédot ehhez a mezőhöz
     * @param n a szomszéd sorszáma
     * @param f a szomszéd
     */
    public void AddNeighbour(String n, Field f){
        Skeleton.functionCall("Field.AddNeighbour(n: int, f: Field)  ID: " + id);
        neighbours.put(n, f);
        Skeleton.functionReturn("Field.AddNeighbour(n: int, f: Field)  Return");
    }

    public String mark(){
        return "";
    }

    @Override
    public String toString(){
        String s="";
        ArrayList<Virologist> cpy = new ArrayList<>(virologist);
        cpy.sort((v1,v2)->v1.getId().compareTo(v2.getId()));
        for(Virologist v : cpy){
            if(!s.equals(""))
                s+=(";");
            s+=(v.getId());
        }

        return s;
    }
}