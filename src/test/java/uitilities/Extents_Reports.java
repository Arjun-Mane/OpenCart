package uitilities;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import testCases.Base_Class;
import org.testng.ITestContext; 
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Extents_Reports implements ITestListener 
{
	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
    String RepName;
	String ReportPath;
	
	public void OnStart(ITestContext TestContext) 
	{
//      SimpleDateFormat df=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
//      Date dt=new Date();
//      String TimeStamp=df.format(dt);

		
		String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RepName = "Test-Reports-" + TimeStamp + ".html";
		ReportPath = System.getProperty("user.dir") + "/Reports/" + RepName;
		
		sparkreport = new ExtentSparkReporter(ReportPath);
		sparkreport.config().setDocumentTitle("Automation Testing");
		sparkreport.config().setReportName("Functional Testing");
		sparkreport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();

		extent.attachReporter(sparkreport);
		extent.setSystemInfo("Application Name", "OpenCart");
		extent.setSystemInfo("Module Name", "Admin");
		extent.setSystemInfo("Enviourment Name", "QA");
		extent.setSystemInfo("Tester Name", "Arjun Mane");

		String os = TestContext.getCurrentXmlTest().getParameter("os");
		if (os != null) extent.setSystemInfo("Operating System", os);

		String browser = TestContext.getCurrentXmlTest().getParameter("browser");
		if (browser != null) extent.setSystemInfo("Browser", browser);

		List<String> group = TestContext.getCurrentXmlTest().getIncludedGroups();
		if (!group.isEmpty()) 
		{
			extent.setSystemInfo("Groups", group.toString());
		}
	}

	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " Test Passed Successfully");
	}

	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		try 
		{
			String imgPath = new Base_Class().CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} 
		catch (Exception e) 
		{
			test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " Test Skipped");
		
		if (result.getThrowable() != null) 
		{
			test.log(Status.SKIP, result.getThrowable().getMessage());
		}
	}

	public void onFinish(ITestContext context)
	{
		  if (extent != null)
		    {
		        extent.flush();
		    }
		    else
		    {
		        System.out.println(" ExtentReports instance is null. Report not generated.");
		    }
	}
}
