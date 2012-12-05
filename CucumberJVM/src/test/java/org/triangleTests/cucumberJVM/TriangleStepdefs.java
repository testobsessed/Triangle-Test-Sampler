package org.triangleTests.cucumberJVM;

import static junit.framework.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TriangleStepdefs {

	private WebDriver driver;
	private TrianglePage trianglePage;
	
	public TriangleStepdefs() {
		driver = new FirefoxDriver();
		driver.get("http://practice.agilistry.com/triangle");
		trianglePage = PageFactory.initElements(driver, TrianglePage.class);
	}

	@When("^I enter (.*), (.*) and (.*) as side lengths$")
	public void enterSideLength(String side1, String side2, String side3) throws Exception {
		trianglePage.enterSideLengths(side1, side2, side3);
	}
	
	@Then("^triangle displays \"([^\"]*)\" as the triangle type$")
	public void triangleDisplaysTriangleType(String triangleType) {
		assertEquals(triangleType, trianglePage.triangleType());
	}

	@Then("^draws the triangle inside the canvas$")
	public void drawsTheTriangleInsideTheCanvas() {
		assertTrue(trianglePage.coordinatesAreValid());
	}
	
	@After
	public void closeBrowserWindow() {
		driver.quit();
	}
	
}
