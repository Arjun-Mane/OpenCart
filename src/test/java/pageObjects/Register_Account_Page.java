package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Register_Account_Page extends Base_Page
{
	public Register_Account_Page(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@name='firstname']")
	WebElement txt_FirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txt_LastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement txt_phone;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement txt_CPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement ch_Privacy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_Continue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement Msg;
	
	public void SetFirstName(String fName)
	{
		txt_FirstName.sendKeys(fName);
	}
	
	public void SetLastName(String LName)
	{
		txt_LastName.sendKeys(LName);
	}
	
	public void SetEmail(String email)
	{
		txt_Email.sendKeys(email);
	}
	
	public void SetPhone(String phone)
	{
		txt_phone.sendKeys(phone);
	}
	
	public void Setpassword(String pass)
	{
		txt_Password.sendKeys(pass);
	}
	
	public void SetCPassword(String pass)
	{
		txt_CPassword.sendKeys(pass);
	}
	
	public void SelectPrivacy()
	{
		ch_Privacy.click();
	}
	
	public void ClickContinue()
	{
		btn_Continue.click();
	}
	
	public String GetMassage()
	{
		String ma=Msg.getText();
		return ma;
	}
}
