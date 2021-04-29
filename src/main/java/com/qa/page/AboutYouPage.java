package com.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.qa.base.Base;

public class AboutYouPage extends Base {

	SoftAssert sofassrt=new SoftAssert();
	
	// Find elements at run time

	@FindBy(xpath="//button[contains(text(),'Ok')]")
	WebElement cookiebtn;

	@FindBy(xpath="//li[@data-test-id='UserAccount']")
	WebElement userAccountBtn;

	@FindBy(xpath="//div[@class='sc-fzXfMB ccpTSg euli1-0 gFzyhh']//div[contains(text(),'Registrieren')]")
	WebElement registerBtn;

	@FindBy(xpath="//input[@name='firstName']")
	WebElement firstNameTextField;

	@FindBy(xpath="//input[@name='lastName']")
	WebElement lastNameTextField;

	@FindBy(xpath="//input[@name='email']")
	WebElement emailTextField;

	@FindBy(xpath="//input[@name='password']")
	WebElement passwordTextField;
	
	@FindBy(xpath="//input[@value='female']")
	WebElement salutationRadioBtn;

	@FindBy(xpath="//button[@data-testid='RegisterSubmitButton']")
	WebElement joinNowBtn;

	@FindBy(xpath="//button[contains(text(),'Abmelden')]")
	WebElement signOutBtn;

	@FindBy(xpath="//font[contains(text(),'//div[contains(text(),'Mit anderem Konto anmelden')]")
	WebElement differentAccSgninBtn;

	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;

	@FindBy(xpath="//a[@title='Kinder']")
	WebElement kinderTab;

	@FindBy(xpath="//section[@class='gi00w3-0 sc-19tq43e-2 kqfkWF sc-1qdczr2-0 kLBmzm']//span[contains(text(),'Babys')]")
	WebElement babysLink;

	@FindBy(xpath="//span[contains(text(),'Bekleidung')]")
	WebElement babysBekleidungLink;

	@FindBy(xpath="//span[contains(text(),'Jacken & Mäntel')]")
	WebElement JackenAndMantelLink;

	@FindBy(xpath="//button[contains(text(),'Größe')]")
	WebElement sizedrpdn;

	@FindBys(@FindBy(xpath="//div[@class='sc-1bc31mp-0 XPlmn']"))
	List<WebElement> sizetext;

	@FindBys(@FindBy(xpath="//div[@class='sc-1bc31mp-0 XPlmn']//label"))
	List<WebElement> sizecheckBox;


	@FindBy(xpath="//button[@class='sc-1wzrtji-0 hPfBHn']//div[contains(text(),'Sortierung')]")
	WebElement sortSelection;

	@FindBy(xpath="//div[contains(text(),'Niedrigster Preis')]")
	WebElement sortSelectionVal;

	@FindBy(xpath="//button[contains(text(),'Hinzufügen')]")
	WebElement addToCartBtn;

	@FindBy(xpath="//div[@class='sectionWrapper--3ioAR']//button[contains(text(),'Zur Kasse')]")
	WebElement chkOutBtn;

	@FindBy(xpath="//button//span[contains(text(),'Jetzt zahlungspflichtig bestellen')]")
	WebElement  orderNowBtn;


	@FindBy(xpath="//div[@class='sc-1mm0dd3-3 eyXQwH']//div[@class='sc-1oa7xla-2 sc-11k7naj-2 nAsZs']//preceding::input[@type='checkbox']")
	WebElement  chkbxbtn;


	@FindBys(@FindBy(xpath="//div[@class='sc-1mm0dd3-3 eyXQwH']//div[@class='sc-1oa7xla-2 sc-11k7naj-2 nAsZs']"))
	List<WebElement>  clicksiz;


	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}

	//Method to accept cookie pop up and login into application
	public String login(String firstName,String emailId,String password){
		try
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (cookiebtn.isDisplayed())
			{
				cookiebtn.click();
			}

			userAccountBtn.click();
			emailTextField.sendKeys(emailId);
			passwordTextField.sendKeys(password);
			submitBtn.click();

		}catch(Exception e)
		{
			System.out.println(e);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement verifyFirstNameOnPage=driver.findElement(By.xpath("//span[contains(text(),'"+firstName+"')]"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return verifyFirstNameOnPage.getText();
	}

	//Method to select category as Children and their clothing type
	public String selectCategory(){

		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Kinder']")));
		try
		{
			kinderTab.click();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		babysLink.click();
		babysBekleidungLink.click();
		JackenAndMantelLink.click();	

		WebElement clothType=driver.findElement(By.xpath("//span[contains(text(),'Babyjacken')]"));
		return clothType.getText();
	}

	//Method to set filter on clothing size

	public Boolean setFilterOnSize(String sizeVal)
	{

		sizedrpdn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			for (int i=0;i<clicksiz.size();i++)
			{

				String actualSize=clicksiz.get(i).getText();
				if (sizeVal.equals(actualSize))
				{
					driver.findElement(By.xpath("//div[@class='sc-1mm0dd3-3 eyXQwH']//div[@class='sc-1oa7xla-2 sc-11k7naj-2 nAsZs']//preceding::input[@type='checkbox']")).click();
				
					break;
				}
			}

		}catch(Exception e)
		{
			System.out.println(e);
		}
		return sortSelection.isDisplayed();

	}

// Method to select respective clothing from the list
	public Boolean selectProduct(String expectedProductId)
	{
		sizedrpdn.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			List<WebElement>lst1=driver.findElements(By.xpath("//div[@class='sc-19tq43e-1 iRbhuv']//div[5]//div[@class='sc-1n50fuf-0 jLIxQJ']//a"));
			String actualProductId = null;
			for (int j=0;j<lst1.size();j++)
			{
				actualProductId=lst1.get(j).getAttribute("id");


				if (expectedProductId.equals(actualProductId))
				{
					driver.findElement(By.xpath("//div[@class='sc-19tq43e-1 iRbhuv']//div[5]//div[@class='sc-1n50fuf-0 jLIxQJ']//a//img['"+j+"']")).click();
					break;
				}
			}

		}catch(Exception e)
		{
			System.out.println(e);
		}
		return addToCartBtn.isEnabled();

	}
	
	//Method to select size and add product to the cart

	public Boolean addProductToCart(String expectedClothSize)
	{
		try
		{
			List<WebElement>selectSizeOfCloth=driver.findElements(By.xpath("//div[@class='sc-1wjc9re-1 kpeKIf']//button//div"));

			for (int i=0;i<selectSizeOfCloth.size();i++)
			{

				String actualClothSize = selectSizeOfCloth.get(i).getText();

				if (expectedClothSize.equals(actualClothSize))
				{
					driver.findElement(By.xpath("//div[@class='sc-1wjc9re-1 kpeKIf']//button['"+i+"']")).click();
     			}
			}

			addToCartBtn.click();

		}catch(Exception e)
		{
			System.out.println(e);
		}
		return chkOutBtn.isEnabled();
	}

	//Method to checkOut the product
	public String clickCheckOut()
	{
		Boolean checkOutBtnStatus=chkOutBtn.isEnabled();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		if (checkOutBtnStatus)
		{
			js.executeScript("arguments[0].click();", chkOutBtn);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver.getTitle();
	}

	//Method to select card type
	public String selectCardForPayment(String expectedCardType)
	{
		//	String val1="computop_creditcard_mastercard";
		String actualCardType = null;

		List<WebElement>ls=driver.findElements(By.xpath("//div[@class='payment-selection-list compact']//ul//input"));

		for (int i=0;i<ls.size();i++)
		{

			actualCardType=ls.get(i).getAttribute("value");
			JavascriptExecutor js1=(JavascriptExecutor)driver;

			if (expectedCardType.equals(actualCardType))
			{

				js1.executeScript("arguments[0].click();", ls.get(i));
				break;
			}
		}

		return actualCardType;
	}

	//Method to Order product and verify Payment card details pop up  appear
	public Boolean orderNow()
	{
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("window.scrollBy(0,1000)");
		boolean orderNowBtnStatus=orderNowBtn.isEnabled();
		WebDriverWait waitForCardDetailspopupWindow = new WebDriverWait(driver, 20);
		waitForCardDetailspopupWindow.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[contains(text(),'Jetzt zahlungspflichtig bestellen')]")));
		if (orderNowBtnStatus)
		{
			js2.executeScript("arguments[0].click();", orderNowBtn);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver.findElement(By.xpath("//div[@class='modal-container']")).isDisplayed();
	
	}

}
