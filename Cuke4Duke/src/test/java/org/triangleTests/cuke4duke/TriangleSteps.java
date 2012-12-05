package org.triangleTests.cuke4duke;

import static junit.framework.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import cuke4duke.StepMother;
import cuke4duke.Steps;
import cuke4duke.annotation.After;
import cuke4duke.annotation.I18n.EN.*;

public class TriangleSteps extends Steps {

	private WebDriver driver;
	private TrianglePage trianglePage;
	
	public TriangleSteps(StepMother stepMother) {
		super(stepMother);
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
