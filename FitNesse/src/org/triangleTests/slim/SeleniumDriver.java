package org.triangleTests.slim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumDriver {

  private SeleniumServer server;
  private Selenium testedPage;

  public void startSeleniumServer() throws Exception {
    server = new SeleniumServer();
    server.start();
  }

  public void openBrowser(String browser, String url) {
    testedPage = new DefaultSelenium("localhost", server.getPort(), browser,
        url);
    testedPage.start();
    testedPage.open(url);
  }

  public void setSeleniumSpeed(String speed) {
    testedPage.setSpeed(speed);
  }

  public void maximizeBrowserWindow() {
    testedPage.windowMaximize();
  }

  public boolean stopSeleniumServer() {
    server.stop();
    return true;
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
    Pattern coordinatePattern = Pattern
        .compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");
    Matcher matcher = coordinatePattern.matcher(coordinates);
    return matcher;
  }

  Pattern digitPattern = Pattern.compile("^\\d.*$");

  private boolean isPositiveDigit(String singleMatch) {
    return digitPattern.matcher(singleMatch).find();
  }
}
