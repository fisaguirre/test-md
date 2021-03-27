package com.moondesk.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//HAY QUE CORRERLO COMO TESTNG primero para que me agregue todas las dependencias de maven
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import pages.PageAccountRecovery;
import pages.PageLogin;

import org.openqa.selenium.WebDriver;

public class UserTestAccountRecovery extends Principal {
	
	@BeforeMethod
	public void asd() {
		//driver.findElement(By.xpath("//*[@id=\"comp-k239erkc\"]")).click();
		driver.navigate().to("https://app.moondesk.design/account/resetpassword/request");

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
		String emailWindow = "window.open('https://outlook.live.com/owa/')";
		javascriptExecutor.executeScript(emailWindow);
		
		this.tabs = new ArrayList<String>(driver.getWindowHandles());
	}
	
	@Test
	public void emptyEmailAccountRecovery() {
		PageAccountRecovery pageAccountRecovery = new PageAccountRecovery(driver);
		pageAccountRecovery.accountRecovery("federico.baez.554@outlook.com");		
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div/app-frame/div/section/app-reset-password/section/main/p")).getText().contains("Tienes que colocar el nombre de usuario o e-mail de una cuenta de MoonDesk."));
	}
	
	@Test
	public void sendEmailAccountRecovery() {
		PageAccountRecovery pageAccountRecovery = new PageAccountRecovery(driver);
		pageAccountRecovery.accountRecovery("federico.baez.554@outlook.com");
		Assert.assertTrue(driver.findElement(By.cssSelector(".display-text")).getText().contains("Te hemos enviado un e-mail para recuperar tu contraseña."));
	}
	
	@Test
	public void verifyEmailAccountRecovery() {
		PageAccountRecovery pageAccountRecovery = new PageAccountRecovery(driver);
		pageAccountRecovery.verifyEmailPasswordRecovery(this.tabs,"federico.baez.554@outlook.com", "Baezfederico554");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"ReadingPaneContainerId\"]/div/div/div/div[2]/div/div[1]/div/div/div/div[3]/div/div/div/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td")).getText().contains("Recibimos una solicitud de restablecimiento de contraseña."));
	}
	
	@Test
	public void recoveryAccountPassword() {
		PageAccountRecovery pageAccountRecovery = new PageAccountRecovery(driver);
		pageAccountRecovery.verifyEmailPasswordRecovery(this.tabs,"federico.baez.554@outlook.com", "Baezfederico554");
		pageAccountRecovery.recoveryAccountPassword(this.tabs,"federico.baez.554@outlook.com", "Baezfederico554","FedeBaez3345");
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div/app-frame/div/section/app-new-password/section/main/header/h2")).getText().contains("Tu contraseña ha sido actualizada con éxito."));
	}
	
	
	
	
}