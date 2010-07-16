package org.triangleTests.fitlibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import fitlibrary.CalculateFixture;

public class CalculateTriangleFixture extends CalculateFixture {

  private Selenium testedPage;

  private Pattern digitPattern = Pattern.compile("^\\d.*$");

  private Pattern coordinatePattern = Pattern
      .compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

  public String triangleTypeSide1Side2Side3(String side1, String side2,
      String side3) {
    initTestedPage();
    inputTriangleSideLengths(side1, side2, side3);
    return testedPage.getText("triangle_type");
  }

  private void initTestedPage() {
    if (testedPage == null) {
      String browser = getArgs()[0];
      String page = getArgs()[1];
      String speed = getArgs()[2];

      testedPage = new DefaultSelenium("localhost",
          SeleniumServerFixture.getServerPort(), browser, page);
      testedPage.start();
      testedPage.open(page);
      testedPage.setSpeed(speed);
      testedPage.windowMaximize();
    }
  }

  private void inputTriangleSideLengths(String side1, String side2, String side3) {
    testedPage.type("triangle_side1", side1);
    testedPage.type("triangle_side2", side2);
    testedPage.type("triangle_side3", side3);
  }

  public boolean drawnInsideCanvasSide1Side2Side3(String side1, String side2,
      String side3) {
    inputTriangleSideLengths(side1, side2, side3);
    return drawnInsideCanvas();
  }

  public String coordinates() {
    return testedPage
        .getText("//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]");
  }

  public boolean drawnInsideCanvas() {
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
