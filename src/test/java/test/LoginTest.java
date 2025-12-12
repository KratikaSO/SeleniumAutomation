package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test
	public void test() throws InterruptedException {
		
		HomePage HomeP=new HomePage(driver);
		LoginPage LoginP=new LoginPage(driver);
		
		
		HomeP.clickLogin();
		//Thread.sleep(10);
		LoginP.enterEmail("sonela.krati19@gmail.com");
		LoginP.enterPassword("Test@1234");
		LoginP.clickLoginbtn();
		
		String loggedinText=driver.findElement(By.xpath("//a[text()=' Logged in as ']")).getText();
		System.out.println(loggedinText);
		Assert.assertEquals(loggedinText, "Logged in as Kratika");
		
	
	}
	

}
