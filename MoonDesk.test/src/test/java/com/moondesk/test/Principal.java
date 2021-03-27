package com.moondesk.test;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Principal {
	protected WebDriver driver;
	ArrayList<String> tabs;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.navigate().to("https://www.moondesk.co/");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
}
