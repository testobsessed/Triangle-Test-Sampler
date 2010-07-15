package org.triangleTests.fit;

import org.openqa.selenium.server.SeleniumServer;

import fit.ActionFixture;

public class SeleniumServerFixture extends ActionFixture {

  private static SeleniumServer server = null;

  public SeleniumServerFixture() throws Exception {
    if (server == null) {
      server = new SeleniumServer();
    }
    server.start();
  }

  public void stop() {
    server.stop();
  }

  public static SeleniumServer getServer() {
    return server;
  }
}
