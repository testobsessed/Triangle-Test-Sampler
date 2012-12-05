package org.jnario.triangletests;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TriangleTestsRule implements TestRule {

	private WebDriver driver;

	public TrianglePage trianglePage;
	
	public Statement apply(final Statement base, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				driver = new FirefoxDriver();
				driver.get("http://practice.agilistry.com/triangle");
				trianglePage = PageFactory.initElements(driver, TrianglePage.class);

				base.evaluate();

				driver.close();
				driver.quit();
			}
		};
	}

	
	
}
