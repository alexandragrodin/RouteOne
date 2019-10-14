package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorPage {

    WebDriver driver;

    public CalculatorPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //Loop through numbers on calculator
    public void selectNumber(String number)
    {
		WebElement calculator = driver.findElement(By.cssSelector("#sciout > tbody > tr:nth-child(2) > td:nth-child(2) > div"));
		List<WebElement> numberRows = calculator.findElements(By.tagName("div"));
		
		//Loop through each row of numbers
		for (WebElement numberRow : numberRows) 
    	{
			List<WebElement> numberCells = numberRow.findElements(By.tagName("span"));
			
			//Loop through each cell of each row
			for (WebElement numberCell : numberCells) 
	    	{
				if (numberCell.getText().equals(number))
				{
					numberCell.click();
				}
	    	}
		}
	}

    
    
}