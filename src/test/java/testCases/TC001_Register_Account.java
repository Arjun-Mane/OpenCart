package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Home_Page;
import pageObjects.Register_Account_Page;

public class TC001_Register_Account extends Base_Class
{
	@Test(groups="Sanity")
	public void Registration()
	{
		Home_Page hp=new Home_Page(driver);

		hp.ClickMyAccount();
		hp.ClickRegAccount();
		
//		RegisterAccountPage Reg_Ac=new RegisterAccountPage(driver);
//		
//		Reg_Ac.SetFirstName("Arjun");
//		Reg_Ac.SetLastName("Mane");
//		Reg_Ac.SetEmail("arjun4444@gmail.com");
//		Reg_Ac.SetPhone("9130909106");
//		Reg_Ac.Setpassword("Arjun123");
//		Reg_Ac.SetCPassword("Arjun123");
//		Reg_Ac.SelectPrivacy();
//		Reg_Ac.ClickContinue();
//		
//		Assert.assertEquals(Reg_Ac.GetMassage(), "Your Account Has Been Created!");
		
		//Getrating the random data dynamic
		
		Register_Account_Page Reg_Ac=new Register_Account_Page(driver);
		
		Reg_Ac.SetFirstName(GenrateRandomString());
		Reg_Ac.SetLastName(GenrateRandomString());
		Reg_Ac.SetEmail(GenrateRandomEmail());
		Reg_Ac.SetPhone(GenrateRandomNumber());
		
		String pass=GenerateStrongRandomPassword();
		Reg_Ac.Setpassword(pass);
		Reg_Ac.SetCPassword(pass);
		
		Reg_Ac.SelectPrivacy();
		Reg_Ac.ClickContinue();
		
		String mag=Reg_Ac.GetMassage();
		String mm="Your Account Has Been Created!";
		
		if(mag.contentEquals(mm))
		{
			Assert.assertTrue(true);
		}  
		else
		{
			Assert.assertTrue(false);
		}
	}
}
