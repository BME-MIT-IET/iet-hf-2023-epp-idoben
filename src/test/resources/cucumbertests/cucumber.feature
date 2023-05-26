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
    And the virologist has 10 amino acids
    When the virologist tries to pick up the amino acid
    Then the amino acid stays on the field