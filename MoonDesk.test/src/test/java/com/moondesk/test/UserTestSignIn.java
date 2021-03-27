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
import pages.PageLogin;

import org.openqa.selenium.WebDriver;

public class UserTestSignIn extends Principal{

	@BeforeMethod
	public void goToLogin() {
		//driver.findElement(By.xpath("//*[@id=\"comp-k239erkc\"]")).click();
		driver.navigate().to("https://app.moondesk.design/account/login");
	}

	@Test
	public void wrongpassword() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("feragustin131995@gmail.com", "aqwerty123");
		Assert.assertTrue(driver.findElement(By.cssSelector(".error-message")).getText().contains("Wrong password"));
	}
	
	@Test
	public void invalidaccount() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("feragustin131995", "aqwerty123");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".error-message")).getText().contains("Nombre de usuario inválido"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".error-message")).getText().contains("User name is invalid"));
	}
	
	@Test
	public void invaliddata() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.enterLogin("", "");
		Assert.assertTrue(driver.findElement(By.cssSelector(".error-message")).getText().contains("Invalid login data"));
		//Assert.assertTrue(driver.findElement(By.cssSelector(".error-message")).getText().contains("Datos de inicio de sesión no válidos"));
	}

	@Test
	public void login() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("feragustin131995@gmail.com", "Juanperez13");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".greeting > span")).getText().contains("¡Hola! Bienvenido nuevamente."));
		Assert.assertTrue(driver.findElement(By.cssSelector(".titles")).getText().contains("Novedades"));
	}
	
	
	@Test
	public void loginAccountNotVerified() {
		PageLogin pageLogin = new PageLogin(driver);
		//pageLogin.login("feragustin131995@gmail.com", "Juanperez13");
		pageLogin.login("fabian.diaz.456@outlook.com", "Juanperez13");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".titles")).getText().contains("Account is not verified"));
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/app-root/div/app-frame/div/section/app-login-form/section/main/p")).getText().contains("Account is not verified"));
	}

	
}
