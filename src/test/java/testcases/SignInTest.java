/*
 * Author : Chandan Das
 * Last modified : 6/11/2017
 * Test Case Name : Sign in  Error meassage Test on Cleartrip 
 * Test case purpose: Validating the Sign in Error message Summary
 *  
 */


package testcases;

import com.sun.javafx.PlatformUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();
    
    @BeforeTest
    
    public void setup(){
    setDriverPath();    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    
    
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {       

        driver.get("https://www.cleartrip.com/");              
        
        //waitFor(2000);
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        
        driver.switchTo().frame("modal_window"); // hide frame is present 

        driver.findElement(By.id("signInButton")).click();
       // driver.findElement(By.xpath("//*[@id='signInButton']"));
        //waitFor(2000);

        String errors1 = driver.findElement(By.id("errors1")).getText();
        System.out.println(errors1);
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
    }
         
    @AfterTest
    public void tearDown(){
    	driver.quit();
    }
    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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


}