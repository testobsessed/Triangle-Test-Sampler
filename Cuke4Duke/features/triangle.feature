Feature: Triangle Page
  The triangle page identifies triangles based on the size of three sides
  and draws a picture of the triangle.
  
  Scenario Outline: Draw triangles
    When I enter <side1>, <side2> and <side3> as side lengths
    Then triangle displays "<triangle_type>" as the triangle type
    And draws the triangle inside the canvas

    Examples:
      | side1 | side2 | side3 | triangle_type |
      | 1 | 1 | 1 | Equilateral |
      | 3 | 4 | 5 | Right |
      | 4 | 5 | 6 | Scalene |
      | 2 | 5 | 6 | Scalene |
      | 4.2 | 5.6 | 6.1 | Scalene |
      
  
  Scenario Outline: Invalid triangles
    When I enter <side1>, <side2> and <side3> as side lengths
    Then triangle displays "<triangle_type>" as the triangle type

    Examples:
      | side1 | side2 | side3 | triangle_type |
      | 0 | 4 | 5 | Invalid |
      | 1 | 4 | 6 | Invalid |