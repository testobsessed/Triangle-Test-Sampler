package org.jnario.triangletests

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.PageFactory

describe "Triangle"{
  
  static val driver = new FirefoxDriver()
  extension static TrianglePage trianglePage
  
  before all{
    driver.get("http://practice.agilistry.com/triangle");
    trianglePage = PageFactory::initElements(driver, typeof(TrianglePage))
  }
  
  def valid_examples{
    | side1 | side2 | side3 | triangle_type |
      | 1     | 1     | 1     | "Equilateral" |
      | 3     | 4     | 5     | "Right" |
      | 4     | 5     | 6     | "Scalene" |
      | 2     | 5     | 6     | "Scalene" |
      | 4.2   | 5.6   | 6.1   | "Scalene" |
      }
  
  fact valid_examples.forEach[
    enterSideLengths(side1.toString, side2.toString, side3.toString)
    triangleType => triangle_type
    coordinatesAreValid => true 
  ]

  def invalid_examples{
    | side1 | side2 | side3 | triangle_type |
      | 0 | 4 | 5 | "Invalid" |
      | 1 | 4 | 6 | "Invalid" | 
      }
  
  fact invalid_examples.forEach[
    enterSideLengths(side1.toString, side2.toString, side3.toString)
    triangleType => triangle_type
  ]
  
  after all{
    driver.close();
    driver.quit();
  }

}