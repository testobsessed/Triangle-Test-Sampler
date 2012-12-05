package org.triangleTests.jbehave;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TriangleTestSteps extends Embedder {

	private static WebDriver driver;
	private TrianglePage trianglePage;
	
	@When("I enter <side1>, <side2> and <side3> as side lengths")
	public void enterSideLengths(@Named("side1") String side1, @Named("side2") String side2, @Named("side3") String side3) throws Exception {
		trianglePage.enterSideLengths(side1, side2, side3);
	}
	
	@Then("triangle displays <triangle_type> as the triangle type")
	public void triangleTypeBecomes(@Named("triangle_type") String triangleType) {
		assertEquals(triangleType, trianglePage.triangleType());
	}

	@Then("draws the triangle inside the canvas")
	public void drawsTheTriangleInsideTheCanvas() {
		assertTrue(trianglePage.coordinatesAreValid());
	}
	
	@BeforeStory
	public static void suiteSetUp() {
		driver = firefoxDriver();
		driver.get("http://practice.agilistry.com/triangle/");
	}

	private static WebDriver firefoxDriver() {
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		return firefoxDriver;
	}

	@BeforeScenario
	public void setUp() {
		trianglePage = PageFactory.initElements(driver, TrianglePage.class);
	}

	@AfterStory
	public static void suiteTearDown() {
		driver.close();
	}

}
