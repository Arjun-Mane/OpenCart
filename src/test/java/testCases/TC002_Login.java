package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Home_Page;
import pageObjects.Login_Page;
import pageObjects.MyAccount_Page;

public class TC002_Login extends Base_Class
{
	@Test(groups={"Sanity","Regresion"})
	void VerifyLogin() throws InterruptedException 
	{
		try 
		{
			Home_Page hp = new Home_Page(driver);

			hp.ClickMyAccount();
			hp.ClickLoginAccount();

			Login_Page lg = new Login_Page(driver);

			lg.SetUserName(p.getProperty("UserName"));
			Thread.sleep(3000);
			lg.SetPassword(p.getProperty("Password"));
			Thread.sleep(3000);
			lg.ClickLogin();

			MyAccount_Page MyAcc = new MyAccount_Page(driver);
			Thread.sleep(3000);

			Assert.assertEquals(MyAcc.MyAccountPageExist(), true, "Test Failed");
		}
		catch (Exception e) 
		{
			Assert.fail();
		}
	}
}
