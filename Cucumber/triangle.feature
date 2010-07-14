Feature: Triangle Page
  The triangle page identifies triangles based on the size of three sides
  and draws a picture of the triangle.
  
  Scenario Outline: Draw triangles
    When I enter <side1> for side1
    And I enter <side2> for side2
    And I enter <side3> for side3
    Then triangle displays "<triangle_type>" as the triangle type
    And draws the triangle inside the canvas

    Examples:
      | side1 | side2 | side3 | triangle_type |
      | 1 | 1 | 1 | Equilateral |
      | 3 | 4 | 5 | Right |
      | 4 | 5 | 6 | Scalene |
      | 2 | 5 | 6 | Scalene |
      | 4.2 | 5.6 | 6.1 | Scalene |
      | 0 | 4 | 5 | Invalid |
      | 1 | 4 | 6 | Invalid |