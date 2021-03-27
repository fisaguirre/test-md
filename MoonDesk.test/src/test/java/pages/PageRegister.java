package pages;

import java.awt.Window;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class PageRegister {
	private WebDriver driver;
	private By userField;
	private By createAccountButton;
	private By acceptRegisterButton;
	private By nameAccount;
	private By lastnameAccount;
	private By usernameAccount;
	private By passwordAccount;
	private By passwordAccountRepeat;
	private By formButtonAccount;
	private By workspacename;

	public PageRegister(WebDriver driver) {
		this.driver = driver;
		userField = By.id("mat-input-0");
		createAccountButton = By.cssSelector(".full-primary-btn");
		acceptRegisterButton = By.cssSelector(".def-btn:nth-child(2) > span");
		
		nameAccount = By.xpath("//*[@id=\"mat-input-1\"]");
		lastnameAccount = By.id("mat-input-2");
		usernameAccount = By.id("mat-input-3");
		passwordAccount = By.id("mat-input-4");
		passwordAccountRepeat = By.id("mat-input-5");
		
		workspacename = By.id("mat-input-6");
	}

	public void register(String email) {
		driver.findElement(userField).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    driver.findElement(createAccountButton).click();
	    driver.findElement(acceptRegisterButton).click();
	    
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void enterRegister(String email) {
		driver.findElement(userField).sendKeys(email);
		driver.findElement(userField).sendKeys(Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void verifyRegister(ArrayList<String> tabs, String username, String password) {
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.xpath("/html/body/header/div/aside/div/nav/ul/li[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i0116")).sendKeys(username);
		driver.findElement(By.id("i0116")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("i0118")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("i0118")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"lightbox\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div/div")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[1]/div/div/div[1]/div[2]/div/div[1]/div/div[2]/div[2]")).click();
	}
	
	public void activateAccountRequiredFields(String name, String lastname, String username, String password) {
		String linkActiveAccount = driver.findElement(By.xpath("//*[@id=\"ReadingPaneContainerId\"]/div/div/div/div[2]/div/div[1]/div/div/div/div[3]/div/div/div/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr[5]/td/a")).getText();
		driver.navigate().to(linkActiveAccount);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(nameAccount).click();
		driver.findElement(nameAccount).sendKeys(name);
		driver.findElement(passwordAccountRepeat).click();
		driver.findElement(passwordAccountRepeat).sendKeys(Keys.ENTER);
	  
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div/app-frame/div/section/app-profile/section/main/form/div/mat-form-field[2]/div/div[2]/div")).getText().contains("Last name is required."));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mat-error-2\"]")).getText().contains("Username is required."));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mat-error-3\"]")).getText().contains("Password is required."));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mat-error-4\"]")).getText().contains("Password confirmation is required."));	
		
	}
	
	public void activateAccount(String name, String lastname, String username, String password, String nameWork) {
		String linkActiveAccount = driver.findElement(By.xpath("//*[@id=\"ReadingPaneContainerId\"]/div/div/div/div[2]/div/div[1]/div/div/div/div[3]/div/div/div/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr[5]/td/a")).getText();
		driver.navigate().to(linkActiveAccount);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(nameAccount).click();
		driver.findElement(nameAccount).sendKeys(name);
		driver.findElement(lastnameAccount).sendKeys(lastname);
		driver.findElement(usernameAccount).sendKeys(username);
		driver.findElement(passwordAccount).sendKeys(password);
		driver.findElement(passwordAccountRepeat).sendKeys(password);
		driver.findElement(passwordAccountRepeat).sendKeys(Keys.ENTER);
		driver.findElement(workspacename).click();
		driver.findElement(workspacename).sendKeys(nameWork);
		driver.findElement(workspacename).sendKeys(Keys.ENTER);
	}
	
}
