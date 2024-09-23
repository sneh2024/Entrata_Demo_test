package first;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class FirstProgram {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.co.in/");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		
		if(title.equals("Google"))
		{
			System.out.println("Got required title");	
		}
		else
		{
			System.out.println("Test failed");
		}
		
		driver.close();
	}

}
