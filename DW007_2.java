package appiumProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
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
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DW007_2 {
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
	public void electronics() throws InterruptedException, IOException, JXLException {
		driver.findElement(By.xpath("//div[@id=\"mob-menu-button\"]/a/span[2]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//ul[@class='mob-top-menu show']//a[contains(text(),'Electronics')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Show products in category Camera, photo']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		File myFile=new File("C:\\Users\\SanskritiPrahladka\\eclipse-workspace\\Data.xlsx");
		Workbook wb= Workbook.getWorkbook(myFile);
		Sheet sh=wb.getSheet(0);
		for(int i=1;i<=3;i++) {
			driver.findElement(By.xpath("//select[@id='products-pagesize']")).click();	
			String view=sh.getCell(0, i).getContents().trim();
			String xpath="//select/option[text()='"+view+"']";
			driver.findElement(By.xpath(xpath)).click();
			Assert.assertEquals(driver.findElement(By.xpath("//select[@id='products-pagesize']")).getText().trim(), view);			
		}
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}

