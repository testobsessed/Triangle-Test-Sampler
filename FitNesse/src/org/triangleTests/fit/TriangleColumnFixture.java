package org.triangleTests.fit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import fit.ColumnFixture;

public class TriangleColumnFixture extends ColumnFixture {

  public String side1;

  public String side2;

  public String side3;

  private Selenium testedPage = null;

  private Pattern digitPattern = Pattern.compile("^\\d.*$");

  private Pattern coordinatePattern = Pattern
      .compile("(-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)\\) \\((-*[0-9]+),(-*[0-9]+)");

  public String identifiedAs() {
    return testedPage.getText("triangle_type");
  }

  public void execute() {
    initTestPage();
    testedPage.type("triangle_side1", side1);
    testedPage.type("triangle_side2", side2);
    testedPage.type("triangle_side3", side3);
  }

  private void initTestPage() {
    if (testedPage == null) {
      String browser = getArgs()[0];
      String page = getArgs()[1];

      testedPage = new DefaultSelenium("localhost", SeleniumServerFixture
          .getServer().getPort(), browser, page);
      testedPage.start();
      testedPage.open(page);
      testedPage.setSpeed("1000");
      testedPage.windowMaximize();
    }
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
