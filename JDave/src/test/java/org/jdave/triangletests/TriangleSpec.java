package org.jdave.triangletests;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class TriangleSpec extends Specification<WebDriver>{

	public class ValidTriangle {
		private WebDriver driver;
		
		private TrianglePage page;
		
		public TrianglePage create() {
			driver = new FirefoxDriver();
			driver.get("http://practice.agilistry.com/triangle/");
			page = PageFactory.initElements(driver, TrianglePage.class);
			return page;
		}
		
		public void destroy() {
			driver.close();
		}
		
		public void triangleWithSameSideLengthIsEquilateral() throws Exception {
			page.enterSideLengths("1", "1", "1");
			specify(page.triangleType(), should.equal("Equilateral"));
			specify(page.coordinatesAreValid());
		}
		
		public void rightTriangle() throws Exception {
			page.enterSideLengths("3", "4", "5");
			specify(page.triangleType(), should.equal("Right"));
			specify(page.coordinatesAreValid());
		}
		
		public void scaleneTriangleOne() throws Exception {
			page.enterSideLengths("4", "5", "6");
			specify(page.triangleType(), should.equal("Scalene"));
			specify(page.coordinatesAreValid());
		}
		
		public void scaleneTriangleTwo() throws Exception {
			page.enterSideLengths("2", "5", "6");
			specify(page.triangleType(), should.equal("Scalene"));
			specify(page.coordinatesAreValid());
		}
		
		public void scaleneTriangleThree() throws Exception {
			page.enterSideLengths("4.2", "5.6", "6.1");
			specify(page.triangleType(), should.equal("Scalene"));
			specify(page.coordinatesAreValid());
		}
		
	}
	
	public class InvalidTriangle {
		private WebDriver driver;
		
		private TrianglePage page;
		
		public TrianglePage create() {
			driver = new FirefoxDriver();
			driver.get("http://practice.agilistry.com/triangle/");
			page = PageFactory.initElements(driver, TrianglePage.class);
			return page;
		}
		
		public void destroy() {
			driver.close();
		}

		public void zeroSideLength() throws Exception {
			page.enterSideLengths("0", "4", "5");
			specify(page.triangleType(), should.equal("Invalid"));
		}

		public void degenerateCase() throws Exception {
			page.enterSideLengths("1", "4", "6");
			specify(page.triangleType(), should.equal("Invalid"));
		}

	}
	
}
