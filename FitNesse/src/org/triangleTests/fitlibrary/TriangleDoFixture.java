package org.triangleTests.fitlibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import fitlibrary.DoFixture;

public class TriangleDoFixture extends DoFixture {

  private static Selenium testedPage;

  private Pattern digitPattern = Pattern.compile("^\\d.*$");

  private Pattern coordinatePattern = Pattern
      .compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

  public static boolean openBrowser(String browser, String url) {
    testedPage = new DefaultSelenium("localhost",
        SeleniumServerFixture.getServerPort(), browser, url);
    testedPage.start();
    testedPage.open(url);
    return true;
  }

  public static void setSeleniumSpeed(String speed) {
    testedPage.setSpeed(speed);
  }

  public static void browserWindowMaximize() {
    testedPage.windowMaximize();
  }

  public static boolean closeBrowser() {
    testedPage.close();
    return true;
  }

  public void inputValues(String side1, String side2, String side3) {
    testedPage.type("triangle_side1", side1);
    testedPage.type("triangle_side2", side2);
    testedPage.type("triangle_side3", side3);
  }

  public boolean verifyTriangleIsIdentifiedAs(String triangleType) {
    return triangleType.equals(testedPage.getText("triangle_type"));
  }

  public String coordinates() {
    return testedPage
        .getText("//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]");
  }

  public boolean verifyTriangleIsDrawnInsideCanvas() {
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
