package com.moondesk.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import net.bytebuddy.asm.Advice.Enter;
import pages.PageRegister;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class UserTestSignUp extends Principal {

	@BeforeMethod
	public void goToRegister() {
		driver.navigate().to("https://app.moondesk.design/account/register");

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		String emailWindow = "window.open('https://outlook.live.com/owa/')";
		javascriptExecutor.executeScript(emailWindow);
		this.tabs = new ArrayList<String>(driver.getWindowHandles());
	}
	@Test
	public void registerAccount() {
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.register("lautaro.fernandez.453@outlook.com");
		
		//pageRegister.verifyRegister(tabs, "fabian.diaz.456@outlook.com","Diazfabian456");
		
	    //Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Tu cuenta de MoonDesk fue creada con éxito. Confirma tu cuenta para poder comenzar a trabajar con MoonDesk."));
	    //Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Your MoonDesk account was created successfully. Confirm your account to start working with MoonDesk."));
	    Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div/app-frame/div/section/app-email-sent/section/header/h2")).getText().contains("Te hemos enviado un e-mail para confirmar tu cuenta."));
	}
	
	@Test
	public void verifyEmailactivateAccount() {
		PageRegister pageRegister = new PageRegister(driver);		
		pageRegister.verifyRegister(tabs, "lautaro.fernandez.453@outlook.com","Fernandezlautaro153");
		
	    //Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Tu cuenta de MoonDesk fue creada con éxito. Confirma tu cuenta para poder comenzar a trabajar con MoonDesk."));
	    Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Your MoonDesk account was created successfully. Confirm your account to start working with MoonDesk."));
	}
	
	@Test
	public void notActivated() {
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.register("lautaro.fernandez.453@outlook.com");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("User with this email already exists"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("User registered but not confirmed. Resending email"));
	}
	
	@Test
	public void activateAccountRequiredFields() {			
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.verifyRegister(tabs, "lautaro.fernandez.453@outlook.com","Fernandezlautaro153");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageRegister.activateAccountRequiredFields("Lautaro","fernandez","lautarofer4352","FernandezLautaro443");
		//Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Your MoonDesk account was created successfully. Confirm your account to start working with MoonDesk."));
	}
	
	@Test
	public void activateAccount() {			
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.verifyRegister(tabs, "fabian.diaz.456@outlook.com","Diazfabian456");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageRegister.activateAccount("Juan","Perez","juanpe1345","perezjuan1995","LautaroF15");
		//Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) tr:nth-child(3) > td")).getText().contains("Your MoonDesk account was created successfully. Confirm your account to start working with MoonDesk."));
		Assert.assertTrue(driver.findElement(By.cssSelector(".titles")).getText().contains("Novedades"));
	}
	
	@Test
	public void emptyaccount() {
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.enterRegister("");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Por favor, escribe una dirección de correo válida."));
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Please enter a valid e-mail address."));
		Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Please write a valid email address"));
	}
	
	@Test
	public void duplicateaccount() {
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.register("feragustin131995@gmail.com");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("El usuario con este correo electrónico ya existe"));
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Make sure the address is well-written."));
		Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("User with this email already exists"));
	}

	@Test
	public void invalidemail() {
		PageRegister pageRegister = new PageRegister(driver);
		pageRegister.register("feragustin131995@g");
	    //driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).click();
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Correo electrónico inválido"));
		//Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Make sure the address is well-written."));
		Assert.assertTrue(driver.findElement(By.cssSelector(".mat-simple-snackbar > span")).getText().contains("Invalid email"));
	}
	
}