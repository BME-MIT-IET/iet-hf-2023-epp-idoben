Feature: Virologist stepping

  Scenario: Virologist steps on safehouse
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse
    Then the virologist is in the safe house
    And the virologist is not on the previous field

  Scenario: Virologist collects ProtSuit
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse
    And the virologist collects the ProtSuit
    Then the virologist has ProtSuit

  Scenario: Virologist steps but does not collect ProtSuit
    Given a virologist and a safe house with a ProtSuit
    When the virologist steps on the safehouse
    Then the virologist does not have equipment

  Scenario: A Virologist steps on the safe house and collects the ProtSuit
    Given a virologist and a safe house with a ProtSuit
    And the virologist has a Sack
    When the virologist steps on the safehouse
    And the virologist collects the ProtSuit
    Then the virologist has 2 equipments

  Scenario: A Virologist steps on the safe house but does not collect ProtSuit
    Given a virologist and a safe house with a ProtSuit
    And the virologist has a Sack
    When the virologist steps on the safehouse
    Then the virologist has 1 equipments

