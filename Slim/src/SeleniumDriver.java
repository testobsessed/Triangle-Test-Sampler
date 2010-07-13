import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumDriver {

  private SeleniumServer server;
  private Selenium selenium;

  public void startSeleniumServer() throws Exception {
    server = new SeleniumServer();
    server.start();
  }

  public void openBrowser(String browser, String url) {
    selenium = new DefaultSelenium("localhost", server.getPort(), browser, url);
    selenium.start();
    selenium.open(url);
  }

  public void setSeleniumSpeed(String speed) {
    selenium.setSpeed(speed);
  }

  public void maximizeBrowserWindow() {
    selenium.windowMaximize();
  }

  public void stopSeleniumServer() {
    selenium.stop();
    server.stop();
  }

  public void inputText(String locator, String text) {
    selenium.type(locator, text);
  }

  public boolean verifyText(String locator, String expected) {
    return expected.equals(getText(locator));
  }

  public String getText(String locator) {
    return selenium.getText(locator);
  }
}
