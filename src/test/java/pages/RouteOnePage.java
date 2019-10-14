package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class RouteOnePage {

    WebDriver driver;

    public RouteOnePage(WebDriver driver)
    {
        this.driver = driver;
    }

    //Select tab on nav bar
    public void hoverOverTabOnNavBar(String tabName)
    {
    	WebElement navBar = driver.findElement(By.cssSelector(".tb-megamenu-nav.nav.level-0"));
    	List<WebElement> tabs = navBar.findElements(By.cssSelector(".tb-megamenu-item.level-1"));
    	for (WebElement tab : tabs) 
    	{
    		if (tab.findElement(By.tagName("a")).getAttribute("href").contains(tabName))
			{
    			Actions hover = new Actions(driver);
    			hover.moveToElement(tab).build().perform();
			}
    	}
    }

	//Select link on sub nav
    public void selectLinkOnSubNav(String linkName)
    {
    	WebElement subNav = driver.findElement(By.cssSelector(".tb-megamenu-subnav.mega-nav.level-1"));
    	List<WebElement> links = subNav.findElements(By.cssSelector(".tb-megamenu-item.level-2"));
    	for (WebElement link : links) 
    	{
    		if (link.findElement(By.tagName("a")).getAttribute("href").contains(linkName))
			{
				link.click();
				break;
			}
    	}
    }
    
    public void verifyJobOpenings(String jobName, String jobCountry)
    {
    	WebElement jobOpenings = driver.findElement(By.className("current-openings-list"));
    	List<WebElement> jobs = jobOpenings.findElements(By.className("current-openings-item"));
    	
    	String numOfJobResults = driver.findElement(By.className("vdl-tile__title")).getText();
    	numOfJobResults = numOfJobResults.substring(numOfJobResults.length()-2).replace("(", "").replace(")", "");
    	Assert.assertTrue(Integer.valueOf(numOfJobResults).equals(jobs.size()));
    	
    	for (WebElement job : jobs) 
    	{
    		Assert.assertTrue(job.findElement(By.tagName("span")).getText().contains(jobName));
    		Assert.assertTrue(job.findElement(By.cssSelector(".current-opening-locations > .current-opening-location-item")).getText().contains(jobCountry));
    	}
    }
    
    
    //Set input value of routeone job search
    public void setInputValue(String input)
    {
        driver.findElement(By.className("mdf-searchbox-input")).sendKeys(input);
        driver.findElement(By.className("mdf-searchbox-input")).sendKeys(Keys.ENTER);
    }

}