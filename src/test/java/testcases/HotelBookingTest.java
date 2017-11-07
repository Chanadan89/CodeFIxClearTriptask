package testcases;

import com.sun.javafx.PlatformUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {
	
	WebDriver driver = new ChromeDriver();
	
	//Initializing the pagefactory inside constructor    
    private HotelBookingTest() {
		PageFactory.initElements(driver, this);
	}
	
    @BeforeTest
    public void setup(){
    setDriverPath();    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    driver.get("https://www.cleartrip.com/");
    } 
        

    //@FindBy(linkText = "Hotels")
    @FindBy(xpath = "//a[contains(text(),'Hotels')]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(id= "CheckInDate")
    private WebElement checkin;
    
    @FindBy(id= "CheckOutDate")
    private WebElement checkOut;
    
    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
        setDriverPath();
             

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");
        Thread.sleep(2000);
        localityTextBox.sendKeys((Keys.DOWN.ENTER));
        
        //Choose the Date for CheckIn
        checkin.click();        
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[3]/a")).click();
        
        //Choose the Date for CheckOut
        checkOut.click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[7]/a")).click();
        //Thread.sleep(3000);

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
       
        searchButton.click();
        Thread.sleep(5000);//optional sleep for to checking after submit button 

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
   	driver.quit();
   }

}