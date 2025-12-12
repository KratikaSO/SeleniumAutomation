package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
private WebDriver driver;
	
	@FindBy(xpath="//form[@action='/login']//input[@name='email']")
	private WebElement email;
	

	@FindBy(xpath="//form[@action='/login']//input[@type='password']")
	private WebElement password;
	
	@FindBy(xpath="//button[@data-qa='login-button']")
	private WebElement loginbtn;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterEmail(String Email) {
		email.clear();
		email.sendKeys(Email);
	}
	public void enterPassword(String Password) {
		password.clear();
		password.sendKeys(Password);
	}
	public void clickLoginbtn() {
		loginbtn.click();
	}
	
	

}
