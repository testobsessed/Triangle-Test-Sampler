package org.jnario.triangletests

import org.junit.Rule

import static extension org.jnario.lib.JnarioIterableExtensions.*
import static extension org.jnario.lib.Should.*

Feature: Traingle Page
  The triangle page identifies triangles based on the size of three sides
  and draws a picture of the triangle.
	
  Scenario: Draw equilateral triangle
	@Rule public TriangleTestsRule context = new TriangleTestsRule
	  	
	When  I enter "1", "1", "1" as side lengths
	    	context.trianglePage.enterSideLengths(args.first, args.second, args.third)
    Then triangle displays "Equilateral" as the triangle type
    		context.trianglePage.triangleType => args.first
	    And draws the triangle inside the canvas
	    	context.trianglePage.coordinatesAreValid => true

Scenario: Draws right triangle
	When  I enter "3", "4", "5" as side lengths
	Then triangle displays "Right" as the triangle type
	    And draws the triangle inside the canvas
	    
Scenario: Draws scalene triangle
	When  I enter "4", "5", "6" as side lengths
	Then triangle displays "Scalene" as the triangle type
	    And draws the triangle inside the canvas

Scenario: Draws another scalene triangle
	When  I enter "2", "5", "6" as side lengths
	Then triangle displays "Scalene" as the triangle type
	    And draws the triangle inside the canvas

Scenario: Draws a scalene triangle with floating point values
	When  I enter "4.2", "5.6", "6.1" as side lengths
	Then triangle displays "Scalene" as the triangle type
	    And draws the triangle inside the canvas

Scenario: Notices an invalid triangle with zero side length
	When  I enter "0", "4", "5" as side lengths
	Then triangle displays "Invalid" as the triangle type

Scenario: Notices an invalid triangle with invliad side lengths
	When  I enter "1", "4", "6" as side lengths
	Then triangle displays "Invalid" as the triangle type
