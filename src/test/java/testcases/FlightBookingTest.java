/*
 * Author : Chandan Das
 * Last modified : 6/11/2017
 * Test Case Name : Flight Booking for one way travel on Cleartrip 
 * Test case purpose: Validating the Search Summary
 *  
 */


package testcases;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {

    WebDriver driver = new ChromeDriver();
    
    @BeforeTest
    
    public void setup(){
    setDriverPath();    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    driver.get("https://www.cleartrip.com/");
    }
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	
    	
    	driver.findElement(By.id("OneWay")).click();
			
			//        driver.findElement(By.id("FromTag")).clear();
			//        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
			//
			//        //wait for the auto complete options to appear for the origin
			//
			//        waitFor(2000);
			//        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			//        originOptions.get(0).click();
    	
    	
    	   //Origin Input:    	
	 	   WebElement originName = driver.findElement(By.name("origin"));
	 	   originName.sendKeys("Bangalore");
	 	   waitFor(2000);
	 	   originName.sendKeys((Keys.ENTER));
			        
				      
			 	   	   
			
			//        driver.findElement(By.id("toTag")).clear();
			//        driver.findElement(By.id("toTag")).sendKeys("Delhi");
			//
			//        //wait for the auto complete options to appear for the destination
			//
			//        waitFor(2000);
			//        //select the first item from the destination auto complete list
			//        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
			//        destinationOptions.get(0).click();
			
			//        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
	 	   
	 	   
	 	  //Destination Input:	 	   
	 	   WebElement destinationName=driver.findElement(By.name("destination"));
	 	   destinationName.sendKeys("Delhi");
	 	   waitFor(2000);
	 	   destinationName.sendKeys((Keys.ENTER));
 	   
 	   
	 	   driver.findElement(By.xpath("//*[@id='DepartDate']")).click();
	 	   driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
	 	   
	 	   //all fields filled in. Now click on search:	 	   
	        driver.findElement(By.id("SearchBtn")).click();
	
	        waitFor(2000);
	        //verify that result appears for the provided journey search:
	        Assert.assertTrue(isElementPresent(By.className("searchSummary")));		        	
	       
	        
	
	    }
				     
			 	   
    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
    
    
    @AfterTest
    public void tearDown(){
    	
    	//close the browser:
    	driver.quit();
    }
}