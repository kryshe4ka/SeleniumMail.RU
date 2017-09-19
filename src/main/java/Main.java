import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static void main(String[] args) {

		WebDriver driver = null;
		driver = createChromeDriver();
		getPage(driver);
		findElement(driver);
		sleep(10000);
		sendLetter(driver);
		sleep(3000);
		logOut(driver);
		sleep(2000);
		quitDriver(driver);

	}

	public static WebDriver createSafariDriver() {
		WebDriver driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver createChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver createFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	public static void quitDriver(WebDriver driver) {
		driver.quit();
	}

	public static void sleep(int msek) {
		try {
			Thread.sleep(msek);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void getPage(WebDriver driver) {
		driver.get("https://mail.ru");
	}

	public static void findElement(WebDriver driver) {
		WebElement eLoginField = driver.findElement(By.id("mailbox__login"));
		eLoginField.sendKeys("tathtp");
		WebElement ePasswordField = driver.findElement(By.id("mailbox__password"));
		ePasswordField.sendKeys("Klopik123");
		ePasswordField.submit();
	}

	public static void sendLetter(WebDriver driver) {

		WebElement eWriteLetter = driver.findElement(By.xpath("//*[@id=\"b-toolbar__left\"]/div/div/div[2]/div/a"));
		eWriteLetter.click();
		sleep(2000);
		WebElement eToField = driver.findElement(By.xpath(
				"//*[contains(@id,'composeForm')]/div[1]/div/div[3]/div[1]/div/div/div[2]/div/div/div/textarea[2]"));
		eToField.sendKeys("sviatlana.zakharenka@gmail.com");
		WebElement eSubjectField = driver.findElement(By.name("Subject"));
		eSubjectField.sendKeys("Homework from Liza Kryshkovskaya");
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'composeEditor_ifr')]"));
		driver.switchTo().frame(frame);
		WebElement eMessageBody = driver.findElement(By.id("tinymce"));
		eMessageBody.sendKeys("Hi! Thi is homework from Kryshkovskaya Liza. ");
		driver.switchTo().defaultContent();
		WebElement eSendButton = driver
				.findElement(By.xpath("//*[@id=\"b-toolbar__right\"]/div[3]/div/div[2]/div[1]/div"));
		eSendButton.click();

	}

	public static void logOut(WebDriver driver) {
		driver.findElement(By.id("PH_logoutLink")).click();
	}
}