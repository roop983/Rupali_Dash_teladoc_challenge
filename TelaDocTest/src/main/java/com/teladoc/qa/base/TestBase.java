package com.teladoc.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.teladoc.qa.util.TestUtil;

public class TestBase {	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/teladoc/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@SuppressWarnings("deprecation")
	public  static void initialization() {
		
		String browser=prop.getProperty("browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver=new ChromeDriver();
		}	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);	
		driver.get(prop.getProperty("url"));
	}
}
