package org.triangleTests.slim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumDriver {

	private Selenium testedPage;

	private Pattern digitPattern = Pattern.compile("^\\d.*$");

	private Pattern coordinatePattern = Pattern
			.compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

	public void openBrowser(int port, String browser, String url) {
		testedPage = new DefaultSelenium("localhost", port, browser, url);
		testedPage.start();
		testedPage.open(url);
	}

	public void closeBrowser() {
		if (testedPage != null) {
			testedPage.close();
			testedPage.stop();
		}
		testedPage = null;
	}

	public void setSeleniumSpeed(String speed) {
		testedPage.setSpeed(speed);
	}

	public void maximizeBrowserWindow() {
		testedPage.windowMaximize();
	}

	public void inputText(String locator, String text) {
		testedPage.type(locator, text);
	}

	public String getText(String locator) {
		return testedPage.getText(locator);
	}

	public String coordinates() {
		return testedPage
				.getText("//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]");
	}

	public boolean coordinatesAreValid() {
		String coordinates = coordinates();

		Matcher matcher = matchCoordinates(coordinates);

		if (!matcher.find())
			return false;

		for (int matchCount = 1; matchCount <= matcher.groupCount(); matchCount++) {
			String singleMatch = matcher.group(matchCount);
			if (!isPositiveDigit(singleMatch))
				return false;
			if (Double.parseDouble(singleMatch) > 200.0)
				return false;
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
