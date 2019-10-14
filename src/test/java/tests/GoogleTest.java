package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GooglePage;

public class GoogleTest {		
	    WebDriver driver;
	    GooglePage objGoogleSearch;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.google.com
	     * Verify Google Home Page & Logo
	     * Search for 'RouteOne'
	     * Loop through 1st page of results to find https://www.routeone.com/
	     * Click on https://www.routeone.com/
	     * Verify RouteOne Home Page & Logo
	     */
		@Test				
		public void googleTest()
		{	
			//Create Google Page object
			objGoogleSearch = new GooglePage(driver);
	
			//Navigate to Google
	        driver.get("https://www.google.com");

	        //Wait for Google Page to Load
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewport")));
	        
		    //Verify Google Logo
	        Assert.assertTrue(driver.findElement(By.id("hplogo")).isDisplayed());
		    Assert.assertEquals(driver.findElement(By.tagName("img")).getAttribute("alt"), "Google");
		    
		    //Verify Google Search Bar
		    Assert.assertTrue(driver.findElement(By.xpath("//input[@title='Search']")).isDisplayed());
		   
		    //Set input value for Search & Enter
		    objGoogleSearch.setInputValue("RouteOne");

		    //Wait for results to load
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rhscol")));
	        
	        //loop through results on first page for www.routeone.com and click it
	        objGoogleSearch.selectGoogleResult("https://www.routeone.com/"); 
	        
	        //Wait for RouteOne Page to Load
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page")));
	        
		    //Verify RouteOne Logo
	        Assert.assertTrue(driver.findElement(By.id("logo")).isDisplayed());
		    Assert.assertEquals(driver.findElement(By.tagName("img")).getAttribute("alt"), "RouteOne");
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}