package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount_Page extends Base_Page
{
	public MyAccount_Page(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement Visibolity;
	
	@FindBy(xpath="//a [@class='list-group-item' and text()='Logout']")
	WebElement link_Logout;
	
	public boolean MyAccountPageExist()
	{
		try
		{
		    boolean st=Visibolity.isDisplayed();
		    return st;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void ClickLogOut()
	{
		link_Logout.click();
	}
}
