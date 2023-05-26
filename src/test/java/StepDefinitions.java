import agent.FullProt;
import com.main.Virologist;
import equipment.Sack;
import field.SafeHouse;
import field.Simple;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {
    @Given("")
    public void today_is_sunday(){
        System.out.println("xd");
    }


    static Virologist v1;
    static Simple simple;
    static SafeHouse safeHouse;

    @Given("a virologist and a full prot in a safe house")
    public static void initField(){
        v1=new Virologist("Virologist");
        FullProt fullProt = new FullProt("fullProt",1,v1,1,0);
        v1.AddEffect(fullProt);
        safeHouse = new SafeHouse("safeHouse",new Sack(1,null,"sack"), 1, 2);
        simple = new Simple("simple", 3, 4);
        safeHouse.AddNeighbour("0",simple);
        simple.AddNeighbour("0", safeHouse);

    }

    @When("the virologist steps on the safehouse")
    public void testSomeMethod() {
        safeHouse.Accept(v1);
        v1.Accept(safeHouse, new HashSet<Virologist>());
    }

    @Then("the virologist is in the safe house")
    public void x() {
        v1.Move("0");
        assertTrue(simple.IsHere(v1));
        assertTrue(!safeHouse.IsHere(v1));
    }

}
