package pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PageAccountRecovery {
	
	private WebDriver driver;
	private By newpasswordAccount;
	private By confirmnewpasswordAccount;
	
	private By pageAccountRecovery;
	private By userRecovery;
	private By accountRecoveryButton;

	public PageAccountRecovery(WebDriver driver) {
		this.driver = driver;		
		newpasswordAccount = By.xpath("//*[@id=\"mat-input-0\"]");
		confirmnewpasswordAccount = By.xpath("//*[@id=\"mat-input-1\"]");

		pageAccountRecovery = By.id("div:nth-child(1) > .moon-text-btn");
		accountRecoveryButton = By.id("mat-input-2");
		userRecovery = By.cssSelector(".full-primary-btn > span");	
	}
	public void accountRecovery(String account) {
		 driver.findElement(pageAccountRecovery).click();
		    driver.findElement(userRecovery).click();
		    driver.findElement(userRecovery).sendKeys(account);
		    driver.findElement(accountRecoveryButton).click();
	}
	public void verifyEmailPasswordRecovery(ArrayList<String> tabs, String username, String password) {
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
	
	public void recoveryAccountPassword(ArrayList<String> tabs, String username, String password, String newpassword) {
		String linkActiveAccount = driver.findElement(By.xpath("//*[@id=\"ReadingPaneContainerId\"]/div/div/div/div[2]/div/div[1]/div/div/div/div[3]/div/div/div/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr[5]/td/a")).getText();
		driver.navigate().to(linkActiveAccount);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(newpasswordAccount).click();
		driver.findElement(newpasswordAccount).sendKeys(newpassword);
		driver.findElement(confirmnewpasswordAccount).sendKeys(newpassword);
		driver.findElement(confirmnewpasswordAccount).sendKeys(Keys.ENTER);
	}

}
