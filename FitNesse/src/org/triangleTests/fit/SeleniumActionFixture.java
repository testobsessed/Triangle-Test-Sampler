package org.triangleTests.fit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import fit.ActionFixture;

public class SeleniumActionFixture extends ActionFixture {

  private static final String TESTED_PAGE_URL = "http://practice.agilistry.com/triangle/";

  private Selenium testedPage;

  private Pattern digitPattern = Pattern.compile("^\\d.*$");

  private Pattern coordinatePattern = Pattern
      .compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

  public SeleniumActionFixture() {
    testedPage = new DefaultSelenium("localhost", SeleniumServerFixture
        .getServer().getPort(), "firefox", TESTED_PAGE_URL);
    testedPage.start();
    testedPage.open(TESTED_PAGE_URL);
  }

  public void side1(String value) {
    testedPage.type("triangle_side1", value);
  }

  public void side2(String value) {
    testedPage.type("triangle_side2", value);
  }

  public void side3(String value) {
    testedPage.type("triangle_side3", value);
  }

  public String triangleIs() {
    return testedPage.getText("triangle_type");
  }

  public void seleniumSpeed(String speed) {
    testedPage.setSpeed(speed);
  }

  public void maximizeBrowserWindow() {
    testedPage.windowMaximize();
  }

  public void close() {
    testedPage.stop();
  }

  public String coordinates() {
    return testedPage
        .getText("//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]");
  }

  public boolean triangleIsDrawnInsideCanvas() {
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
