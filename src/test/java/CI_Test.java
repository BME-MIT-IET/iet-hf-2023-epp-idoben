import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import com.main.*;
import field.*;
import agent.*;
import equipment.*;

import java.util.HashSet;

public class CI_Test {

    static Virologist v1;
    static Simple simple;
    static SafeHouse safeHouse;

    @BeforeAll
    public static void initField(){
        v1=new Virologist("Virologist");
        FullProt fullProt = new FullProt("fullProt",1,v1,1,0);
        v1.AddEffect(fullProt);
        safeHouse = new SafeHouse("safeHouse",new Sack(1,null,"sack"), 1, 2);
        simple = new Simple("simple", 3, 4);
        safeHouse.AddNeighbour("0",simple);
        simple.AddNeighbour("0", safeHouse);
        safeHouse.Accept(v1);
        v1.Accept(safeHouse, new HashSet<Virologist>());
    }

    @Test
    public void testSomeMethod() {
        v1.Move("0");
        Assertions.assertTrue(simple.IsHere(v1));
        Assertions.assertTrue(!safeHouse.IsHere(v1));
    }
}
