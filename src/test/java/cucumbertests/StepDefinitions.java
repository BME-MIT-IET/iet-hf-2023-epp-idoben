package cucumbertests;

import agent.*;
import com.main.Game;
import com.main.Virologist;
import equipment.Axe;
import equipment.Gloves;
import equipment.ProtSuit;
import equipment.Sack;
import field.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Aminoacid;

import static org.junit.Assert.*;

public class StepDefinitions {
    static Virologist v1;
    static Virologist v2;
    static Simple simple;
    static SafeHouse safeHouse1;
    static SafeHouse safeHouse2;
    static SafeHouse safeHouse3;
    static WareHouse warehouse1;
    static Lab lab;
    static Aminoacid aminoacid;
    static FullProt fullProt;
    static Agent agent;
    static ProtSuit p1;
    static ProtSuit p2;
    static Sack s1;
    static Gloves g1;
    static Axe a1;
    static Game game;

    @Given("a virologist and a safe house with a ProtSuit")
    public void virologistandsafehouse() {
        v1 = new Virologist("Virologist");
        p1 = new ProtSuit(5, null, "protsuit p1");
        safeHouse1 = new SafeHouse("safeHouse1", p1, 1, 2);
        simple = new Simple("simple", 3, 4);
        safeHouse1.AddNeighbour("0",simple);
        simple.AddNeighbour("0", safeHouse1);
        simple.Accept(v1);
    }

    @Given("a safe house with an Axe")
    public void safehousewithanaxe() {
        a1 = new Axe(null, "axe a1");
        safeHouse2 = new SafeHouse("safeHouse2", a1, 1, 3);
        safeHouse1.AddNeighbour("1", safeHouse2);
        safeHouse2.AddNeighbour("1", safeHouse1);
    }

    @Given("a safe house with a ProtSuit")
    public void safehousewithaprotsuit() {
        p2 = new ProtSuit(5, null, "protsuit p2");
        safeHouse3 = new SafeHouse("safeHouse2", p2, 1, 4);
        safeHouse2.AddNeighbour("0", safeHouse3);
        safeHouse3.AddNeighbour("0", safeHouse2);
    }


    @Given("the virologist has a Sack")
    public void virologisthassack() {
        s1 = new Sack(5, null, "sack s1");
        v1.PickupEq(s1);
    }

    @Given("the virologist has a Glove")
    public void virologisthasgloves() {
        g1 = new Gloves(5, 5, null, "gloves g1");
        v1.PickupEq(g1);
    }

    @Given("a virologist and a warehouse with an amino acid")
    public void aVirologistAndAWarehouseWithAnAminoAcid() {
        v1 = new Virologist("Virologist");
        aminoacid = new Aminoacid();
        warehouse1 = new WareHouse(aminoacid, "warehouse1", 1, 1);
        warehouse1.Accept(v1);
    }
    @Given("the virologist has 10 amino acids")
    public void theVirologistHasAminoAcids() {
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
        v1.PickupResource(new Aminoacid());
    }

    @Given("a virologist and a lab with a FullProt")
    public void aVirologistAndALabWithAFullProt() {
        v1 = new Virologist("Virologist");
        fullProt = new FullProt("Cf", 5, null, 5, 5);
        lab = new Lab("Lab", fullProt, false, 0, 0);
        lab.Accept(v1);
    }

    @Given("the virologist has {string}")
    public void theVirologistHasOblivion(String arg0) {
        switch (arg0) {
            case "Oblivion" -> agent = new Oblivion("Co", v1, 1, 1);
            case "Paralysis" -> agent = new Paralysis("Cp", 3, v1, 1, 1);
            case "VirusDance" -> agent = new VirusDance("Cv", v1, 1, 1);
        }
    }

    @Given("the virologist is paralized")
    public void theVirologistIsParalized() {
        agent = new Paralysis("Cp", 3, v1, 1, 1);
        v1.AddEffect(agent);
    }

    @Given("another virologist")
    public void anotherVirologist() {
        v2 = new Virologist("Virologist2");
        lab.Accept(v2);
    }


    @When("the virologist steps on the safehouse with a ProtSuit")
    public void step1() {
        v1.Move("0");
    }

    @When("the virologist collects the equipment from the field")
    public void collect1() {
        v1.Collect();
    }

    @When("the virologist steps on the safehouse with an Axe")
    public void step2() {
        v1.Move("1");
    }

    @When("the virologist tries to pick up the amino acid")
    public void theVirologistTriesToPickUpTheAminoAcid() {
        v1.Collect();
    }

    @When("the virologist picks up the virus")
    public void theVirologistPicksUpTheVirus() {
        v1.Collect();
    }

    @When("the second virologist tries to rob the first one")
    public void theSecondVirologistTriesToRobTheFirstOne() {
        v2.StealEqFrom(v1, g1);
    }


    @Then("the virologist is in the safe house")
    public void virologistinsafehouse() {
        assertTrue(safeHouse1.IsHere(v1));
    }

    @Then("the virologist is not on the first field")
    public void virologistnotonfield() {
        assertFalse(simple.IsHere(v1));
    }

    @Then("the virologist is not on the safe house with ProtSuit")
    public void virologistnotonsafehouse() {
        assertFalse(safeHouse1.IsHere(v1));
    }

    @Then("the virologist is in the safe house with Axe")
    public void virologistinsafehousewithaxe() {
        assertTrue(safeHouse2.IsHere(v1));
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

    @Then("the amino acid stays on the field")
    public void theAminoAcidStaysOnTheField() {
        assertTrue(warehouse1.toString().contains("Ra"));
    }

    @Then("the virologist gets the virus")
    public void theVirologistGetsTheVirus() {
        assertEquals(v1.getCodes().get(0).toString(), "Cf");
    }


    @Then("the game is over")
    public void theGameIsOver() {
        assertTrue(Game.gameEnded);
    }


    @Then("then first virologist no longer has the item")
    public void thenFirstVirologistNoLongerHasTheItem() {
        assertTrue(v1.getEquipments().isEmpty());
    }

    @And("the second virologist has the item")
    public void theSecondVirologistHasTheItem() {
        assertFalse(v2.getEquipments().isEmpty());
        assertSame(v2.getEquipment(0), g1);
    }
}
