import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class MyFirstTest {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://magento.softwaretestingboard.com/";

	String logoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";

	Random rand = new Random();

	String Password = "Mahdi@2001";

	String emailAdressToLogin = "";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1, enabled = false)
	public void CreateAnAccount() {

//		WebElement CreateAccountPage = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));

//		WebElement CreateAccountPage = driver.findElement(By.partialLinkText("Account"));

//		WebElement CreateAccountPage = driver.findElement(By.linkText("Create an Account"));

		WebElement CreateAccountPage = driver
				.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));
		CreateAccountPage.click();

		String[] firstNames = { "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael" };

		String[] lastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones" };

		int randomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int randomIndexForTheLastName = rand.nextInt(lastNames.length);

		System.out.println(randomIndexForTheFirstName);
		System.out.println(randomIndexForTheLastName);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAddressInput = driver.findElement(By.id("email_address"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));
		WebElement createAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		String firstname = firstNames[randomIndexForTheFirstName];
		String lastname = lastNames[randomIndexForTheLastName];
		int randomnumber = rand.nextInt(9876);
		String domainname = "@gmail.com";

		firstNameInput.sendKeys(firstname);
		lastNameInput.sendKeys(lastname);
		emailAddressInput.sendKeys(firstname + lastname + randomnumber + domainname);
		password.sendKeys(Password);
		confirmPassword.sendKeys(Password);
		createAccountButton.click();

		emailAdressToLogin = firstname + lastname + randomnumber + domainname;
	}

	@Test(priority = 2, enabled = false)
	public void Logout() {
		driver.get(logoutPage);

	}

	@Test(priority = 3, enabled = false)
	public void LoginTest() {
		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement EmailLoginInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		EmailLoginInput.sendKeys(emailAdressToLogin);
		passwordInput.sendKeys(Password);
		LoginButton.click();

	}

	@Test(priority = 4)
	public void Men() throws InterruptedException {

		WebElement MenSection = driver.findElement(By.id("ui-id-5"));
		MenSection.click();

		WebElement ProductItemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> AllItems = ProductItemsContainer.findElements(By.tagName("li"));

		int totalNumberOfItems = AllItems.size();
		int randomItem = rand.nextInt(totalNumberOfItems);

		AllItems.get(randomItem).click();

		WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));

		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
		int numberOfSizes = ListOfSizes.size();
		int randomSize = rand.nextInt(numberOfSizes);
		ListOfSizes.get(randomSize).click();

		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> ListOfColors = ColorContainer.findElements(By.tagName("div"));
		int numberOfColors = ListOfColors.size();
		int randomColor = rand.nextInt(numberOfColors);
		ListOfColors.get(randomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		AddToCartButton.click();

		WebElement MessageAdded = driver.findElement(By.className("message-success"));
		Assert.assertEquals(MessageAdded.getText().contains("You added"), true);

	}
}
