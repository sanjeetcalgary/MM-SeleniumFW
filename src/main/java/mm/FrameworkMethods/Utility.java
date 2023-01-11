package mm.FrameworkMethods;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import io.cucumber.java.Scenario;


public class Utility {

	WebDriver driver;
	
	public void saveScreenAs(WebDriver driver, String screenName)
	{
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File dest = new File(System.getProperty("user.dir") + "/target/screenshots/"+screenName+".png");
	    System.out.println(System.getProperty("user.dir"));
	    try 
	    {
	    	FileHandler.copy(source, dest);
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	     }
	}

}
