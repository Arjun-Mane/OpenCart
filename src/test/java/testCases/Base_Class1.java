package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base_Class1
{
	public static WebDriver driver;
	public Properties p;
    String path;
    
	@BeforeClass(groups = { "Sanity", "Master", "Regresion" })
	@Parameters({ "browser","OS"})
	public void SetUp(String brow,String os) throws IOException
	{
		path=System.getProperty("user.dir")+"//src//test//resources//Config.properties";
		FileReader file = new FileReader(path);
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("Exicution_Env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap= new DesiredCapabilities();
			
			switch(os.toLowerCase())
			{
			case "window" : cap.setPlatform(Platform.WIN11);break;
			case "mac"    : cap.setPlatform(Platform.MAC);break;
			case "linux"  : cap.setPlatform(Platform.LINUX);break;
			default       : System.out.println("No Operaing System Match....."); return;
			}
			
			switch(brow.toLowerCase())
			{
			case "chrome": cap.setBrowserName("Chrome"); break;
			case "edge"  : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("Firefox"); break;
			default      : System.out.println("No Browser Match...."); return;
			}
			
			String LaunchURL="http://localhost:4444/wd/hub";
			
			driver=new RemoteWebDriver(new URL(LaunchURL),cap);
		}
		
		if(p.getProperty("Exicution_Env").equalsIgnoreCase("local"))
		{
			switch (brow.toLowerCase()) 
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge"  : driver = new EdgeDriver(); break;
			default      : System.out.println("No Browser Match...."); return;
			}
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("AppURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}

	@AfterClass(groups ={ "Sanity", "Master", "Regresion" })
	public void TearDown() 
	{
		driver.quit();
	}
	
	
	public String GenerateRandomString() 
	{
		String Name = RandomStringUtils.randomAlphabetic(5);
		return Name;
	}

	public String GenerateRandomNumber() 
	{
		String Number = RandomStringUtils.randomNumeric(10);
		return Number;
	}

	public String GenerateRandomPassword() 
	{
		String part1 = RandomStringUtils.randomAlphabetic(5);
		String Email = part1 + "12@gmail.com";
		return Email;
	}

	public String GenerateStrongRandomPassword() 
	{
		String upperCase = RandomStringUtils.randomAlphabetic(1).toUpperCase();
		String lowerCase = RandomStringUtils.randomAlphabetic(1).toLowerCase();
		String number = RandomStringUtils.randomNumeric(1);
		String specialChar = RandomStringUtils.random(1, "!@#$%^&*()-_=+[]{}|;:'\",.<>?/");

		String password = upperCase + lowerCase + number + specialChar;
		return  password + RandomStringUtils.randomAlphanumeric(4);
	}
	
	
	
	public String CaptureScreen(String name) 
	{
		String TimeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());

		TakesScreenshot Screenshot = (TakesScreenshot) driver;
		File sourceFile = Screenshot.getScreenshotAs(OutputType.FILE);

		String TargetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "-" + TimeStamp + ".png";
		File Targetfile = new File(TargetFilePath);

		sourceFile.renameTo(Targetfile);
		return TargetFilePath;
	}
}
