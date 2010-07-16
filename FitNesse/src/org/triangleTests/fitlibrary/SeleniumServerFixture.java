package org.triangleTests.fitlibrary;

import org.openqa.selenium.server.SeleniumServer;

import fitlibrary.DoFixture;

public class SeleniumServerFixture extends DoFixture {

  private static SeleniumServer server;

  public static int getServerPort() {
    if (server == null) return -1;
    return server.getPort();
  }

  public void startServer() throws Exception {
    server = new SeleniumServer();
    server.start();
  }

  public void stopServer() throws Exception {
    server.stop();
  }

}
