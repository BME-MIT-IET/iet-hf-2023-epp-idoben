Feature: Virologist stepping and collecting equipment

  Scenario: Virologist steps on safehouse
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse with a ProtSuit
    Then the virologist is in the safe house
    And the virologist is not on the first field

  Scenario: Virologist collects ProtSuit
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse with a ProtSuit
    And the virologist collects the equipment from the field
    Then the virologist has ProtSuit

  Scenario: Virologist steps but does not collect ProtSuit
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse with a ProtSuit
    Then the virologist does not have equipment

  Scenario: A Virologist steps on the safe house and collects the ProtSuit
    Given a virologist and a safe house with a ProtSuit
    And the virologist has a Sack
    When the virologist steps on the safehouse with a ProtSuit
    And the virologist collects the equipment from the field
    Then the virologist has 2 equipments

  Scenario: A Virologist steps on the safe house but does not collect ProtSuit
    Given a virologist and a safe house with a ProtSuit
    And the virologist has a Sack
    When the virologist steps on the safehouse with a ProtSuit
    Then the virologist has 1 equipments

  Scenario: A Virologist tries to collect too many equipments
    Given a virologist and a safe house with a ProtSuit
    And a safe house with an Axe
    And a safe house with a ProtSuit
    And the virologist has a Glove
    When the virologist steps on the safehouse with a ProtSuit
    And the virologist collects the equipment from the field
    And the virologist steps on the safehouse with an Axe
    And the virologist collects the equipment from the field
    And the virologist steps on the safehouse with a ProtSuit
    And the virologist collects the equipment from the field
    Then the virologist has 3 equipments

  Scenario: A Virologist steps multiple times
    Given a virologist and a safe house with a ProtSuit
    And a safe house with an Axe
    When the virologist steps on the safehouse with a ProtSuit
    And the virologist steps on the safehouse with an Axe
    Then the virologist is not on the first field
    And the virologist is not on the safe house with ProtSuit
    And the virologist is in the safe house with Axe


  Scenario: A Virologist tries to collect too many resources
    Given a virologist and a warehouse with an amino acid
    And the virologist has 10 resources of each
    When the virologist tries to pick up the amino acid
    Then the amino acid stays on the field

  Scenario: A virologist collects a virus
    Given a virologist and a lab with a FullProt
    When the virologist picks up the virus
    Then the virologist gets the virus

  Scenario: A virologist wins when collecting the final virus
    Given a virologist and a lab with a FullProt
    And the virologist has "Oblivion"
    And the virologist has "Paralysis"
    And the virologist has "VirusDance"
    When the virologist picks up the virus
    Then the game is over

  Scenario: A virologist robs a paralized virologist
    Given a virologist and a lab with a FullProt
    And the virologist has a Glove
    And the virologist is paralized
    And another virologist
    When the second virologist tries to rob the first one
    Then then first virologist no longer has the item
    And the second virologist has the item

  Scenario: A virologist rubs a paralize virus on another virologist
    Given a virologist and a lab with a FullProt
    And the virologist has 10 resources of each
    And the virologist has "Paralysis"
    And another virologist
    When the virologist rubs paralizis on the other virologist
    Then the other virologist is paralized