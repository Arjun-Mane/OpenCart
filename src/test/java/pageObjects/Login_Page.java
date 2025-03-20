package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends Base_Page
{
	public Login_Page(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@name='email']")
	WebElement txt_UserName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_Login;
	
	public void SetUserName(String name)
	{
		txt_UserName.sendKeys(name);
	}
	
	public void SetPassword(String pass)
	{
		txt_Password.sendKeys(pass);
	}
	
	public void ClickLogin()
	{
		btn_Login.click();
	}
}
