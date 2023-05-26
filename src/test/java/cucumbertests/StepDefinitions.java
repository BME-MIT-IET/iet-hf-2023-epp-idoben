package cucumbertests;

import agent.FullProt;
import com.main.Virologist;
import equipment.ProtSuit;
import equipment.Sack;
import field.SafeHouse;
import field.Simple;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;

import static org.junit.Assert.*;

public class StepDefinitions {
    static Virologist v1;
    static Simple simple;
    static SafeHouse safeHouse;
    static ProtSuit p1;
    static Sack s1;

    @Given("a virologist and a safe house with a ProtSuit")
    public void virologistandsafehouse(){
        v1 = new Virologist("Virologist");
        p1 = new ProtSuit(5, null, "protsuit p1");
        safeHouse = new SafeHouse("safeHouse", p1, 1, 2);
        simple = new Simple("simple", 3, 4);
        safeHouse.AddNeighbour("0",simple);
        simple.AddNeighbour("0", safeHouse);
    }

    @Given("the virologist has a Sack")
    public void virologisthassack() {
        s1 = new Sack(5, null, "sack s1");
        v1.PickupEq(s1);
    }

    @When("the virologist steps on the safehouse")
    public void step1() {
        safeHouse.Accept(v1);
        v1.Accept(safeHouse, new HashSet<Virologist>());
        v1.Move("0");
    }

    @When("the virologist collects the ProtSuit")
    public void collect1() {
        safeHouse.Collect(v1);
    }

    @Then("the virologist is in the safe house")
    public void virologistinsafehouse() {
        assertTrue(simple.IsHere(v1));
    }

    @Then("the virologist is not on the previous field")
    public void virologistnotonfield() {
        assertFalse(safeHouse.IsHere(v1));
    }

    @Then("the virologist has ProtSuit")
    public void virologisthasprotsuit() {
        assertFalse(v1.getEquipments().isEmpty());
        assertSame(v1.getEquipment(0), p1);
    }

    @Then("the virologist does not have equipment")
    public void virologistnoequipment() {
        assertTrue(v1.getEquipments().isEmpty());
    }

    @Then("the virologist has {int} equipments")
    public void theVirologistHasEquipments(int arg0) {
        assertEquals(v1.getEquipments().size(), arg0);
    }
}
