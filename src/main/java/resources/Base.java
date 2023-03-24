package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	   public Properties prop;
WebDriver driver;
public WebDriver initializeBrowser() throws IOException, InterruptedException 
{

		WebDriver driver = null;
		
		prop = new Properties();
		String propertiesPath = System.getProperty("user.dir")+ "/src/main/java/resources/data.properties";
		
		FileInputStream fis = new FileInputStream(propertiesPath);
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			/*
			 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
			 */
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options);
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("ie")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		}
		
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		return driver;
		
	}

	}

