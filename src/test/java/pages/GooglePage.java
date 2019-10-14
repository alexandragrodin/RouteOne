package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {

    WebDriver driver;

    public GooglePage(WebDriver driver)
    {
        this.driver = driver;
    }

    //Set input value of google search
    public void setInputValue(String input)
    {
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(input);
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
    }

    //Loop through 1st page of results to find matching link
    public void selectGoogleResult(String googleResultLink)
    {
    	//Section 1 of Google Results
    	WebElement googleResultsSection1 = driver.findElement(By.cssSelector("div#rso > div:nth-child(1)"));
    	List<WebElement> resultsSection1 = googleResultsSection1.findElements(By.className("g"));
    	
    	//Section 2 of Google Results
    	WebElement googleResultsSection2 = driver.findElement(By.cssSelector("div#rso > div:nth-child(2) > div.srg"));
    	List<WebElement> resultsSection2 = googleResultsSection2.findElements(By.className("g"));

    	//For loop through Section 1 of Google Results
    	for (WebElement resultSection1 : resultsSection1) 
    	{
    		//If a result has a matching link, click on link in 1st section
			if (resultSection1.findElement(By.cssSelector("div > div.rc > div.r > a")).getAttribute("href").contains(googleResultLink)) 
			{
				resultSection1.findElement(By.cssSelector("div > div.rc > div.r > a > h3")).click();
			}
			//If a result does not have a matching link, loop through 2nd section of results
			else 
			{
		    	//For loop through Section 2 of Google Results
				for (WebElement resultSection2 : resultsSection2) 
		    	{
		    		//If a result has the matching link, click on link in 2nd section
					if (resultSection2.findElement(By.cssSelector("div > div.rc > div.r > a")).getAttribute("href").contains(googleResultLink)) 
					{
						resultSection2.findElement(By.cssSelector("div > div.rc > div.r > a > h3")).click();
					}
					else
					{
						System.out.println("This link does not exist");
					}
		    	}
		    }
		}
    }
    
}