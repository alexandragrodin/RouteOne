package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.RouteOnePage;

public class RouteOneTest {		
	    WebDriver driver;
	    RouteOnePage objRouteOne;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.routeone.com/
	     * Verify RouteOne Home Page & Logo
	     */
		@Test				
		public void routeOneTest()
		{	
			//Create RouteOne Page object
			objRouteOne = new RouteOnePage(driver);
	
			//Navigate to Google
	        driver.get("https://www.routeone.com/");

	        //Wait for RouteOne Page to Load
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page")));	        
	        Assert.assertTrue(driver.findElement(By.id("logo")).isDisplayed());

			objRouteOne.hoverOverTabOnNavBar("about-us");
			objRouteOne.selectLinkOnSubNav("careers");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page")));
			
			Assert.assertTrue(driver.findElement(By.cssSelector("h1#page-title")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("h1#page-title")).getText().equals("Careers"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".button.cta")));
			Assert.assertTrue(driver.findElement(By.cssSelector(".button.cta")).getText().equals("VIEW ALL CURRENT OPENINGS"));
			driver.findElement(By.cssSelector(".button.cta")).click();

		    // Switch to new window opened
		    for(String winHandle : driver.getWindowHandles()){
		        driver.switchTo().window(winHandle);
		    }
		    
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("careercenter-main-container")));	        

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("mdf-searchbox-input")));

			objRouteOne.setInputValue("test");

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tileCurrentOpenings")));	        

	        objRouteOne.verifyJobOpenings("Software Developer in Test", "US");
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}