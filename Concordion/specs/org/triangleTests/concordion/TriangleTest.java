package org.triangleTests.concordion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@RunWith(ConcordionRunner.class)
public class TriangleTest {

	private static WebDriver driver;
	private TrianglePage page;

	@BeforeClass
	public static void suiteSetUp() {
		driver = firefoxDriver();
		driver.get("http://practice.agilistry.com/triangle/");
	}

	private static WebDriver firefoxDriver() {
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		return firefoxDriver;
	}

	@Before
	public void setUp() {
		page = PageFactory.initElements(driver, TrianglePage.class);
	}

	@AfterClass
	public static void suiteTearDown() {
		driver.close();
	}

	public TrianglePage triangleFor(String side1, String side2, String side3)
			throws Exception {
		page.enterSideLengths(side1, side2, side3);
		return page;
	}

	public static class TrianglePage {

		private Pattern digitPattern = Pattern.compile("^\\d.*$");

		private Pattern coordinatePattern = Pattern
				.compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

		@FindBy(id = "triangle_side1")
		private WebElement side1Element;

		@FindBy(id = "triangle_side2")
		private WebElement side2Element;

		@FindBy(id = "triangle_side3")
		private WebElement side3Element;

		@FindBy(id = "triangle_type")
		private WebElement triangleType;

		private WebDriver driver;

		public TrianglePage(WebDriver driver) {
			this.driver = driver;
		}

		public void enterSideLengths(String side1, String side2, String side3) throws Exception {
			enterValueToElement(side1Element, side1);
			enterValueToElement(side2Element, side2);
			enterValueToElement(side3Element, side3);
			waitUntilNewResultAppears(side1, side2, side3);
		}

		private void enterValueToElement(WebElement element, String value) {
			element.clear();
			element.sendKeys(value, Keys.ENTER);
		}

		public String triangleType() {
			return triangleType.getText();
		}
		
		private void waitUntilNewResultAppears(String side1, String side2,
				String side3) throws Exception {
			long pollIntervall = 500;
			int timeout = 30;

			String xpath = String.format("//div[contains(@class, 'triangle_row') and div[1] = '%s' and div[2] = '%s' and div[3] = '%s']", side1, side2, side3);

			int foundElements = driver.findElements(By.xpath(xpath)).size();
			while (timeout > 0 && foundElements < 1) {
				Thread.sleep(pollIntervall);
				timeout--;
				foundElements = driver.findElements(By.xpath(xpath)).size();
			}
		}
		
		private String coordinates() {
			return driver
					.findElement(
							By.xpath("//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]"))
					.getText();
		}

		public boolean coordinatesAreValid() {
			String coordinates = coordinates();

			Matcher matcher = matchCoordinates(coordinates);

			if (!matcher.find())
				throw new IllegalArgumentException("coordinate row not found");

			for (int matchCount = 1; matchCount <= matcher.groupCount(); matchCount++) {
				String singleMatch = matcher.group(matchCount);
				if (!isPositiveDigit(singleMatch))
					throw new RuntimeException(String.format("negative coordinate found in '%s'", coordinates));
				if (Double.parseDouble(singleMatch) > 200.0)
					throw new RuntimeException(String.format("too large (> 200) coordinate found in '%s'", coordinates));
			}
			return true;
		}

		private Matcher matchCoordinates(String coordinates) {
			Matcher matcher = coordinatePattern.matcher(coordinates);
			return matcher;
		}

		private boolean isPositiveDigit(String singleMatch) {
			return digitPattern.matcher(singleMatch).find();
		}
	}
}
