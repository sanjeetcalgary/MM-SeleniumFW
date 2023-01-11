package StepDefinitions;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import mm.FrameworkMethods.Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {
	
	public WebDriver driver;
	Utility utility = new Utility();
	Set<String> tabs;
	String parentWindow;
	String ChildWindow;
	Iterator<String> iterator;
	
	@Given("^user launch the ([^\"]*) in chrome browser$")
	public void user_launch_the_Browser(String url) {
	    ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Then("^user is on home page$")
	public void user_is_on_home_page() {
	    driver.get("https://phptravels.com/demo/");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    utility.saveScreenAs(driver, "HomePage");
	}

	@When("^user clicks on Sign-up option")
	public void user_clicks_on_option() throws Exception {
		WebElement signup = driver.findElement(By.xpath("//li//a[text()='Sign Up']"));
		signup.click();
		Thread.sleep(1000);
		parentWindow = driver.getWindowHandle();
		tabs = driver.getWindowHandles();
		iterator= tabs.iterator();
		while (iterator.hasNext()) {
			ChildWindow = iterator.next();
                if (!parentWindow.equalsIgnoreCase(ChildWindow)) {
                	driver.switchTo().window(ChildWindow);
            }
        }
		utility.saveScreenAs(driver, "Signup");			
		
	}

	@When("user input ([^\"]*)$")
	public void user_info(String info) {
		String[] personalDetail = info.split(";");
		driver.findElement(By.id("inputFirstName")).sendKeys(personalDetail[0]);
		driver.findElement(By.id("inputLastName")).sendKeys(personalDetail[1]);
		driver.findElement(By.id("inputEmail")).sendKeys(personalDetail[2]);
		driver.findElement(By.id("inputPhone")).sendKeys(personalDetail[3]);
		utility.saveScreenAs(driver, "PersonalInfo");
	}

	@And("^user gives ([^\\\"]*) and ([^\\\"]*)$")
	public void user_address(String address, long number) {
		String[] addr = address.split(";");
		driver.findElement(By.id("inputAddress1")).sendKeys(addr[0]);
		driver.findElement(By.id("inputAddress2")).sendKeys(addr[1]);
		driver.findElement(By.id("inputCity")).sendKeys(addr[2]);
		driver.findElement(By.id("stateinput")).sendKeys(addr[3]);
		driver.findElement(By.id("inputPostcode")).sendKeys(addr[4]);
		driver.findElement(By.id("customfield2")).sendKeys(String.valueOf(number));
		utility.saveScreenAs(driver, "AddressDetails");
	}
	
	@When("user clicks on Generate password")
	public void user_clicks_on_generate_password() throws Exception {
	    WebElement generatePassword = driver.findElement(By.xpath("//div[@id='containerPassword']//button"));
	    generatePassword.click();
	    Thread.sleep(3000);
	}
	
	
	@Then("user clicks on Register")
	public void user_clicks_on_register() {
	   driver.findElement(By.xpath("//input[@value='Register']")).click();
	   utility.saveScreenAs(driver, "Register");
		
	}


	@Then("^verify Generate password UI$")
	public void verify_generate_password_ui() throws Exception {
		utility.saveScreenAs(driver, "PasswordWindow");		
	}

	@When("^user insert passwqord length and click on Generate password$")
	public void user_gives_passwqord_length_and_click_on_generate_password() throws Exception {
		WebElement length = driver.findElement(By.id("inputGeneratePasswordLength"));
		length.clear();
		length.sendKeys(String.valueOf(15));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//form[@id='frmGeneratePassword']//button[@type='submit']")).click();
		utility.saveScreenAs(driver, "GeneratePassword");
		driver.findElement(By.id("btnGeneratePasswordInsert")).click();
	}

	@Then("^verify generated password$")
	public void verify_generated_password() {
		
		String msg = driver.findElement(By.id("passwordStrengthTextLabel")).getText();
		if(msg.equalsIgnoreCase("Password Strength: 100% Strong"))
		{
			System.out.println("Password generated successfully");
		}else
		{
			System.out.println("Password not generated");
		}
		utility.saveScreenAs(driver, "PasswordStrength");	
		
	}


	@Then("^user opts for mailing list and clicks on robot$")
	public void user_opts_for_no_list_and_clicks_on_robot() throws Exception {
		Thread.sleep(10000);		
		utility.saveScreenAs(driver, "RobotCheckbox");
	}


	@Then("^close all$")
	public void verify_user_is_logged_out() {
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.quit();
	}

}
