package entrataTests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.entrata.com/b");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//div[@class='text-block-40']")).click();
		Thread.sleep(3000);
	}
	
	//To test the Title
	@Test(priority=1)
	public void title()
	{
		String act_title = driver.getTitle();
		String exp_title= "Property Management Software | Entrata";
		Assert.assertEquals(act_title, exp_title);
	}
	
	//To test products dropdown
	@Test(priority=4)
	public void products_dropdown_nav_test() throws InterruptedException
	{
		
		WebElement prod_drpdwm=driver.findElement(By.xpath("(//div[text()='Products'])[1]"));	
		
		Actions act=new Actions(driver);
		act.moveToElement(prod_drpdwm).perform();
		driver.findElement(By.xpath("(//a[text()='Rent Reporting'])[2]")).click();
		Thread.sleep(5000);
		//to check expected url
		String exp_url="https://www.entrata.com/products/rent-reporting";
		String act_url=driver.getCurrentUrl();
		Assert.assertEquals(act_url, exp_url);
		
		//to check expected Heading
		String exp_title1="Rent Reporting by Entrata";
		String act_title1=driver.getTitle();
		Assert.assertEquals(exp_title1, act_title1);
	}	
	
	//Schedule your demo functionality
	@Test(priority=2)
	public void schedule_demo_test() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[text()='Schedule Your Demo']")).click();
		Thread.sleep(4000);
		Set<String> tabs=driver.getWindowHandles();
		List<String> windowsList = new ArrayList<>(tabs);
		driver.switchTo().window(windowsList.get(1));
		driver.findElement(By.xpath("//input[@id='FirstName']")).isDisplayed();
		//to check expected url
		String exp_url="https://go.entrata.com/schedule-demo.html";
		String act_url=driver.getCurrentUrl();
		Assert.assertEquals(act_url, exp_url);
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Abc");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Xyz");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("abc.xyz@gmail.com");
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Entrata");
		driver.findElement(By.xpath("//input[@id='Phone']")).sendKeys("1234567890");
		WebElement unit_count_dropdown =driver.findElement(By.xpath("//select[@id='Unit_Count__c']"));
		Select drpdwn=new Select(unit_count_dropdown);
		drpdwn.selectByValue("11 - 100");
		driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("Testing");
		WebElement i_am_dropdown =driver.findElement(By.xpath("//select[@id='demoRequest']"));
		Select iamdrpdwn=new Select(i_am_dropdown);
		iamdrpdwn.selectByValue("a Resident");
		//driver.findElement(By.xpath("//button[@class='mktoButton']")).click();
		//Not clicking on report as per the requirement
		
		driver.switchTo().window(windowsList.get(0));
	}
	
	//Sign in button functionality(Manager login)
	@Test(priority=3)
	public void signin_as_manager_test() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		driver.findElement(By.xpath("//a[@class='button hover_black new-button w-inline-block']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("company_user[username]")).sendKeys("abc");
		driver.findElement(By.name("company_user[password]")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//To check error message
		String exp_error_message="\r\n"
				+ "		The username and password provided are not valid. Please try again.\r\n"
				+ "	";
		String act_error_message=driver.findElement(By.id("statusMessage")).getText();
		exp_error_message = exp_error_message.trim();
		act_error_message = act_error_message.trim();
		Assert.assertEquals(exp_error_message, act_error_message);		
	}
	
	//To close the browser
	@AfterMethod()
	public void teardown()
	{
		//System.out.println("Testing");
		driver.quit();
	}	
}
