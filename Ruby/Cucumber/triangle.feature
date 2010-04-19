Feature: Triangle Page
  The triangle page identifies triangles based on the size of three sides
  and draws a picture of the triangle.

  Scenario: Equilaterals
    When I enter 1 for side1
    And I enter 1 for side2
    And I enter 1 for side3
    Then triangle displays "Equilateral" as the triangle type
    
  Scenario: Right Triangles
    When I enter 3 for side1
    And I enter 4 for side2
    And I enter 5 for side3
    Then triangle displays "Right" as the triangle type

  Scenario: Acute Scalene Triangles
    When I enter 4 for side1
    And I enter 5 for side2
    And I enter 6 for side3
    Then triangle displays "Scalene" as the triangle type

  Scenario: Obtuse Scalene Triangles
    When I enter 2 for side1
    And I enter 5 for side2
    And I enter 6 for side3
    Then triangle displays "Scalene" as the triangle type
    And draws the triangle inside the canvas

  Scenario: Scalene with floating point
    When I enter 4.2 for side1
    And I enter 5.6 for side2
    And I enter 6.1 for side3
    Then triangle displays "Scalene" as the triangle type

  Scenario: 0-length side
    When I enter 0 for side1
    And I enter 4 for side2
    And I enter 5 for side3
    Then triangle displays "Invalid" as the triangle type

  Scenario: largest bigger than the sum of the other two sides
    When I enter 1 for side1
    And I enter 4 for side2
    And I enter 6 for side3
    Then triangle displays "Invalid" as the triangle type
