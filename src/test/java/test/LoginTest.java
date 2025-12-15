package test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.log;

public class LoginTest extends BaseTest {
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filePath=System.getProperty("user.dir")+"/TestData/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount=ExcelUtils.getRowCount();
		Object[][] data=new Object[rowCount-1][2];
		for(int i=1;i<rowCount;i++) {
			
			data[i-1][0]=ExcelUtils.getCellData(i, 0);//username
			data[i-1][1]=ExcelUtils.getCellData(i, 1);//password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	@Test (dataProvider="LoginData")
	public void testLoginWithValidCredential (String email, String password) {
		
		HomePage HomeP=new HomePage(driver);
		LoginPage LoginP=new LoginPage(driver);
		
		test=ExtentReportManager.createTest("LoginTest-"+ email);
		
		log.info("Starting loginTest...");
		HomeP.clickLogin();
		log.info("Adding credential...");
		
		LoginP.enterEmail(email);
		LoginP.enterPassword(password);
		
		
//		LoginP.enterEmail("sonela.krati19@gmail.com");
//		LoginP.enterPassword("Test@1234");
		test.info("Clicking loginbutton..");
		LoginP.clickLoginbtn();
		
		String loggedinText=driver.findElement(By.xpath("//a[text()=' Logged in as Kratika']")).getText();
		System.out.println(loggedinText);
		log.info("Verifying logged in text...");
		Assert.assertEquals(loggedinText, "Logged in as Kratika");
		test.pass("test success");
	
	}
//	@Test
//	public void testLoginWithInvalidCredential() {
//		
//		HomePage HomeP=new HomePage(driver);
//		LoginPage LoginP=new LoginPage(driver);
//		
//		test=ExtentReportManager.createTest("LoginTest");
//		
//		log.info("Starting loginTest...");
//		HomeP.clickLogin();
//		log.info("Adding credential...");
//		LoginP.enterEmail("sonela.krati19@gmail.com");
//		LoginP.enterPassword("Test@1234");
//		test.info("Clicking loginbutton..");
//		LoginP.clickLoginbtn();
//		
//		String loggedinText=driver.findElement(By.xpath("//a[text()=' Logged in as ']")).getText();
//		System.out.println(loggedinText);
//		log.info("Verifying logged in text...");
//		Assert.assertEquals(loggedinText, "Logged in as Kr atika");
//		test.pass("test success");
//	
//	}
	
	

}
