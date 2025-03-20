package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Home_Page;
import pageObjects.Login_Page;
import pageObjects.MyAccount_Page;
import uitilities.Data_Provider;

public class TC003_Login_DataDrivetnTestCase extends Base_Class 
{
	@Test(dataProvider="logindata", dataProviderClass=Data_Provider.class, groups={"Master","Sanity","Regresion"})
	public void loginDDT(String name, String pass, String exp) throws InterruptedException
	{
		try
		{
		Home_Page hp=new Home_Page(driver);
		hp.ClickMyAccount();
		hp.ClickLoginAccount();
		
		Login_Page lg=new Login_Page(driver);
		lg.SetUserName(name);
		lg.SetPassword(pass);
		lg.ClickLogin();
		
		MyAccount_Page MyAc=new MyAccount_Page(driver);
		boolean Status=MyAc.MyAccountPageExist();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(Status==true)
			{
				MyAc.ClickLogOut();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(Status==true)
			{
				MyAc.ClickLogOut();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
 	}
}
