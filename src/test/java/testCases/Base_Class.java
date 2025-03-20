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

public class Base_Class 
{
	public static WebDriver driver;
	public Properties p;
	String path;

	@BeforeClass(groups = { "Sanity", "Master", "Regresion" })
	@Parameters({ "browser", "OS" })
	public void SetUp(String brow, String os) throws IOException 
	{
		path = System.getProperty("user.dir") + "//src//test//resources//Config.properties";
		FileReader file = new FileReader(path);
		p = new Properties();
		p.load(file);

		if (p.getProperty("Exicution_Env").equalsIgnoreCase("Remote")) 
		{
			DesiredCapabilities cap = new DesiredCapabilities();

			switch (os.toLowerCase()) 
			{
			case "window"  : cap.setPlatform(Platform.WIN11); break;
			case "Mac"     : cap.setPlatform(Platform.MAC); break;
			case "Linux"   : cap.setPlatform(Platform.LINUX); break;
			default        : System.out.println("No Operating Syatem Match......"); return;
			}

			switch (brow.toLowerCase()) 
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "Edge"   : cap.setBrowserName("MicrosoftEdge"); break;
			default       : System.out.println("No Browser Match...."); return;
			}
			
			String AppURL="http://localhost:4444/wd/hub";
			driver= new RemoteWebDriver(new URL("AppURL"),cap);
		}

		if (p.getProperty("Exicution_Env").equalsIgnoreCase("Local")) 
		{
			switch (brow.toLowerCase()) 
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge"   : driver = new EdgeDriver(); break;
			default       : System.out.println("No Browser Match...."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("AppURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups = { "Sanity", "Master", "Regresion" })
	public void TearDown() 
	{
		driver.quit();
	}

	public String GenrateRandomString() 
	{
		String Name = RandomStringUtils.randomAlphabetic(5).toUpperCase();
		return Name;
	}

	public String GenrateRandomNumber() 
	{
		String Number = RandomStringUtils.randomNumeric(10);
		return Number;
	}

	public String GenrateRandomEmail()
	{
		String part1 = RandomStringUtils.randomAlphabetic(5);
		String Email = part1 +"gmail.com";
		return Email;
	}
	public String GenerateStrongRandomPassword() 
	{
		String upperCase = RandomStringUtils.randomAlphabetic(1).toUpperCase();
		String lowerCase = RandomStringUtils.randomAlphabetic(1).toLowerCase();
		String number = RandomStringUtils.randomNumeric(1);
		String specialChar = RandomStringUtils.random(1, "!@#$%^&*()-_=+[]{}|;:'\",.<>?/");
		
		String pass = upperCase + lowerCase + number + specialChar;
		return pass + RandomStringUtils.randomNumeric(4);
	}
	
	public String CaptureScreen(String name) 
	{
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        String TargetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "-" + timeStamp + ".png" ;
        File Targetfile = new File(TargetFilePath);
        
        sourceFile.renameTo(Targetfile);
        return TargetFilePath;
    }
}
