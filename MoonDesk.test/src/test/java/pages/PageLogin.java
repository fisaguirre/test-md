package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import helpers.Helpers;

public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	
	

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.id("mat-input-0");
		passwordField = By.id("mat-input-1");
		loginButton = By.cssSelector(".full-primary-btn > span");
			
	}

	public void login(String username, String password) {
		driver.findElement(userField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void enterLogin(String username, String password) {
		driver.findElement(userField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
	    driver.findElement(passwordField).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	
}
