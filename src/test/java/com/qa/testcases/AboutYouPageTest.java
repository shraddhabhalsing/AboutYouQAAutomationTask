package com.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.base.Base;
import com.qa.page.AboutYouPage;
import com.qa.util.CommonUtil;

public class AboutYouPageTest extends Base {

	//Create Object of MainPage where OR and methods are maintained
	AboutYouPage mainPageObj=new AboutYouPage();


	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4,exLog5,exLog6,exLog7,exLog8,exLog9;



	//Prerequisite steps to be executed prior executing actual TestCases

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+prop.getProperty("ReportPath"));
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);

	}
	
	
	// Launch URL TestCase

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		mainPageObj.initializeWebElement();
		exLog=exReport.createTest("Verify user can launch URL", "Automation");
		if (pageTitle.equals("Mode online von mehr als 2.000 Top-Marken | ABOUT YOU"))
		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}


	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}



	// TestCase to verify user is able to Login in application

	@Test(priority=2,dataProvider="passSheet")
	//	@Parameters({"firstName","emailId","password"})
	public void clickFirstProductAndEnterEmail(String firstName,String emailId,String password){
		String verifyFirstNameOnPage=mainPageObj.login(firstName,emailId,password);
		exLog2=exReport.createTest("Verify user is able to Login in application","Automation");
		if (verifyFirstNameOnPage.equalsIgnoreCase(firstName))
		{
			exLog2.log(Status.PASS,"User is able to login into WebApp") ;
		}
		else

		{
			exLog2.log(Status.FAIL,"User failed to login into WebApp") ;
		}



	}

	//Testcase to verify user is able to select the Children category as Babies and cloth category as Jacket

	@Test(priority=3)
	public void selectBabysClothTypeCategory(){
		String verClothSelected= mainPageObj.selectCategory();
		exLog3=exReport.createTest("Verify user is able to select the Children category as Babies and cloth category as Jacket" , "Automation");
		if (verClothSelected.contains("Babyjacken"))
		{
			exLog3.log(Status.PASS,"User is able to select the Children category as Babies ,cloth category as Jacket successfully ") ;
		}

		else

		{
			exLog3.log(Status.FAIL," Failed to select the Children category as Babies ,cloth category as Jacket") ;
		}
	}

	//Testcase to verify user is able to apply filter on size

	@Test(priority=4)
	@Parameters({"sizeVal"})
	public void filterForSize(String sizeVal){


		Boolean verifySizeSelected=mainPageObj.setFilterOnSize(sizeVal);

		exLog4=exReport.createTest("Verify user is able to apply filter on size ", "Automation");
		if (verifySizeSelected)
		{
			exLog4.log(Status.PASS,"Filter applied on size") ;
		}
		else

		{
			exLog4.log(Status.FAIL," Failed to apply filter on size ") ;

		}

	}

    //Testcase to verify user is able to select product
	
	@Test(priority=5)
	@Parameters({"expectedProductId"})
	public void selectProduct(String expectedProductId )
	{

		Boolean verifyAddToCartbtnEnabled= mainPageObj.selectProduct(expectedProductId);


		exLog5=exReport.createTest("Verify user is able to select product", "Automation");
		if (verifyAddToCartbtnEnabled)
		{
			exLog5.log(Status.PASS,"User is able to select product ") ;
		}
		else

		{
			exLog5.log(Status.FAIL," User failed to select product ") ;

		}

	}

  //Testcase to verify user is able to select size for respective product

	@Test(priority=6)
	@Parameters({"expectedSize"})
	public void selectSizeAndaddProductToCart(String expectedSize)
	{

		Boolean verifyCheckOutbtnEnabled= mainPageObj.addProductToCart(expectedSize);


		exLog6=exReport.createTest("Verify user is able to select size for respective product", "Automation");
		if (verifyCheckOutbtnEnabled)
		{
			exLog6.log(Status.PASS,"User is able to select size for product ") ;
		}
		else

		{
			exLog6.log(Status.FAIL," User failed to select size for product ") ;

		}
	}

	//Testcase to verify user is able to click on Checkout button

	@Test(priority=7)
	public void checkOutProduct()
	{

		String verifyAfterCheckOutPagetitle= mainPageObj.clickCheckOut();


		exLog7=exReport.createTest("Verify user is able to click on Checkout button", "Automation");
		if (verifyAfterCheckOutPagetitle.equals("Sicher und einfach bei About You bestellen."))
		{
			exLog7.log(Status.PASS,"User is able to checkOut the product") ;
		}
		else

		{
			exLog7.log(Status.FAIL," User failed to checkOut the product") ;

		}

	}

	//Testcase to verify user is able to select card type
	@Test(priority=8)
	@Parameters({"expectedCardType"})
	public void cardTypeForPayment(String expectedCardType){
		String verifyactualCardType=mainPageObj.selectCardForPayment(expectedCardType);
		exLog8=exReport.createTest("Verify user is able to select card type","Automation");
		if (verifyactualCardType.equals(expectedCardType))
		{
			exLog8.log(Status.PASS,"User is able to select Card Type ") ;
		}
		else

		{
			exLog8.log(Status.FAIL,"User failed to select card type") ;
		}



	}

	//Testcase to verify card detail pop up appears

	@Test(priority=9)

	public void cardTypeForPayment(){
		Boolean verifyCardDetailsPopUp=mainPageObj.orderNow();
		exLog9=exReport.createTest("Verify card detail pop up appeared ","Automation");
		if (verifyCardDetailsPopUp)
		{
			exLog9.log(Status.PASS,"User is able to see the card detail pop up window ") ;
		}
		else

		{
			exLog9.log(Status.FAIL,"User failed to see the card detail pop up window  ") ;
		}



	}


	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
	    driver.close();



	}
}
