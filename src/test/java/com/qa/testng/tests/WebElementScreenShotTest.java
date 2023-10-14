package com.qa.testng.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class WebElementScreenShotTest {
	WebDriver driver=null;
	@BeforeTest
	public void lanch() {
 
		driver= WebDriverManager.chromedriver().create();
	}
	
	@Test
	public void doLogin() {
		
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/Login.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement userName=driver.findElement(By.cssSelector("input[id$='username']"));
		WebElement passWord=driver.findElement(By.cssSelector("input[id$='password']"));
		WebElement loginBtn=driver.findElement(By.cssSelector("input[id$='login_button']"));
		takeWEScreenshot(userName,"userName");
		takeWEScreenshot(passWord,"passWord");
		takeWEScreenshot(loginBtn,"loginBtn");
		userName.sendKeys("Tester");
		passWord.sendKeys("test");
		takeWEScreenshot(userName,"userNameAfter");
		takeWEScreenshot(passWord,"passWordAfter");
		
	
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}

	public void takeWEScreenshot(WebElement element,String fileName) {
		
		File src=element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./target/screenshot/"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
