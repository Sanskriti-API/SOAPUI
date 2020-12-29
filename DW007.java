package appiumProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DW007 {
	AndroidDriver driver;

	@BeforeMethod
	public void desiredCap() throws InterruptedException, MalformedURLException {
		DesiredCapabilities capability= new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Sanskriti");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}

	@Test
	public void electronics() throws MalformedURLException, InterruptedException {
		driver.findElement(By.xpath("//div[@id=\"mob-menu-button\"]/a/span[2]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//ul[@class='mob-top-menu show']//a[contains(text(),'Electronics')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Show products in category Camera, photo']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[@id='products-pagesize']")).click();
		driver.findElement(By.xpath("//select/option[text()='12']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//select/option[text()='12']")).getText(), "12");
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}

