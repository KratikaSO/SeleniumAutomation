package base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.log;

public class BaseTest {
	
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
		extent=ExtentReportManager.getReportInstance();
	}
	
	
	@BeforeMethod
	@Parameters({"url"})
	public void setUp(String url) {
		log.info("Starting webdriver...");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		log.info("Navigating to URL...");
		driver.get(url);
		
	}
    @AfterMethod
	public void tearDown(ITestResult result) {
    	if(result.getStatus()==ITestResult.FAILURE) {
    		//String timestamp=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    		String screenshotpath=ExtentReportManager.captureScreenshot(driver, "LoginFailure");
    		test.fail("Test Failed..check screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
    		
    	}
    	
		if(driver!=null) {
			driver.quit();
		}
		
	}
    @AfterSuite
    public void tearDownReport() {
		extent.flush();
		String reportPath=ExtentReportManager.reportpath;
		EmailUtils.sendReport(reportPath);
	}
    
    
}
