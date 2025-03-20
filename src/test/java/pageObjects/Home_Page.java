package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page extends Base_Page
{
	public Home_Page(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement link_MyAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement link_RegAccount;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement link_LoginAccount;
	
	public void ClickMyAccount()
	{
		link_MyAccount.click();
	}
	
	public void ClickRegAccount()
	{
		link_RegAccount.click();
	}
	
	public void ClickLoginAccount()
	{
		link_LoginAccount.click();
	}
}
