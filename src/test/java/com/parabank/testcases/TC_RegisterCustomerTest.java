package com.parabank.testcases;

import static io.restassured.RestAssured.get;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.parabank.pageobject.ParaAccountServicePage;
import com.parabank.pageobject.ParaAccountsOverviewPage;
import com.parabank.pageobject.ParaBillPaymentServicePage;
import com.parabank.pageobject.ParaHomePage;
import com.parabank.pageobject.ParaLoginPage;
import com.parabank.pageobject.ParaOpenNewAccountPage;
import com.parabank.pageobject.ParaRegisterPage;
import com.parabank.pageobject.ParaTransferFundsPage;
import com.parabank.utilities.ReadExcelData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.parabank.utilities.ConvertCurrencyToDouble;

public class TC_RegisterCustomerTest extends BaseClass{

	ParaHomePage paraHomePage;
	ParaRegisterPage paraRegisterPage;
	ParaLoginPage paraLoginPage;
	ParaAccountServicePage paraAccountServicePage;
	ParaAccountsOverviewPage paraAccountOverviewPage;
	ParaOpenNewAccountPage paraOpenNewAccountPage;
	ParaTransferFundsPage paraTransferFundsPage;
	ParaBillPaymentServicePage paraBillPaymentServicePage;
	String newAccountNumber,initialAccountNumber,loginUserName,loginPassword;
	double initialAccountBalance,newAccountInitialBalance =100.00,initialAccountCurrentBalance,newAccountCurrentBalance;
	double amountToBeTransferred=50, billsToBePaid=30;
	DecimalFormat df = new DecimalFormat("0.00");
	
	@DataProvider(name = "customer-data")
	public Object[][] excelDP() throws IOException, InvalidFormatException
	{
		Object[][] customerData = ReadExcelData.readExcel();
    	return customerData;
	}
	
	@Test(enabled = true, priority = 1)
	public void verifyAppLaunch()
	{
		paraHomePage = new ParaHomePage(driver);
		driver.get(url);
		logger.info("App Launch SuccessFull");
	}
	
	@Test(enabled = true,dependsOnMethods = "verifyAppLaunch",dataProvider ="customer-data",priority = 2)
	public void verifyRegistration(String firstName,String lastName,String address1,String city,String state,String zipCode,
			String phoneNumber,String SSN,String userName,String password,String repeatPassword,String isRegistered) throws InterruptedException
	{
		
		paraHomePage.clickOnRegisterLink();
		paraRegisterPage = new ParaRegisterPage(driver);
		paraRegisterPage.enterFirstName(firstName);
		paraRegisterPage.enterLastName(lastName);
		paraRegisterPage.enterStreetAddress(address1);
		paraRegisterPage.enterCity(city);
		paraRegisterPage.enterState(state);
		paraRegisterPage.enterZipCode(zipCode);
		paraRegisterPage.enterPhoneNumber(phoneNumber);
		paraRegisterPage.enterSSN(SSN);
		paraRegisterPage.enterUserName(userName);
		paraRegisterPage.enterPassword(password);
		paraRegisterPage.enterRepeatPassword(repeatPassword);
		paraRegisterPage.clickOnRegister();
		paraAccountServicePage = new ParaAccountServicePage(driver);
		Assert.assertEquals(paraAccountServicePage.getRegistrationCompletionMessage(),"Welcome "+userName);
		logger.info("Registration SuccessFull");
		Thread.sleep(4000);
		loginUserName = userName;
		loginPassword = password;
	}
	
	@Test(dependsOnMethods = "verifyRegistration",priority = 3)
	public void verifyLogOut()
	{
		paraAccountServicePage.clickOnLogout();
		logger.info("Logout Successfull");
	}
	
	@Test(dependsOnMethods = "verifyRegistration",priority = 4)
	public void verifyLogin() throws InterruptedException
	{
		paraLoginPage = new ParaLoginPage(driver);
		paraLoginPage.enterUserName(loginUserName);
		paraLoginPage.enterPassword(loginPassword);
		paraLoginPage.clickOnLoginBtn();
		Thread.sleep(2000);
		Assert.assertEquals(paraAccountServicePage.showAccountsOverviewMessage(),"Accounts Overview");
		logger.info("LogIn Successfull");
	}
	
	@Test(priority = 5)
	public void verifyInitialAccountDetails() throws InterruptedException
	{
		paraAccountServicePage.clickOnAccountsOverviewLink();
		paraAccountOverviewPage = new ParaAccountsOverviewPage(driver);
		Thread.sleep(2000);
		initialAccountNumber = paraAccountOverviewPage.getInitialAccountNumber();	
		String initAccountBalance = paraAccountOverviewPage.getAccountBalance(initialAccountNumber);
		initialAccountBalance = ConvertCurrencyToDouble.getNumericValue(initAccountBalance);
		System.out.println("Account Number: "+initialAccountNumber+" Balance: "+initAccountBalance);
		Assert.assertEquals(initAccountBalance,"$515.50");
		logger.info("Initial Account Balance Details Successfull!!");
	}
	
	@Test(dependsOnMethods = "verifyLogin",priority = 6)
	public void verifyOpenNewAccount() throws InterruptedException
	{
		paraAccountServicePage.clickOnOpenNewAccountLink();
		paraOpenNewAccountPage = new ParaOpenNewAccountPage(driver);
		paraOpenNewAccountPage.selectAccountType("SAVINGS");
		Thread.sleep(3000);
		paraOpenNewAccountPage.clickOnOpenNewAccountBtn();
		Thread.sleep(3000);
		Assert.assertEquals(paraAccountServicePage.getOpenAccountResult(),"Account Opened!");
		initialAccountCurrentBalance = initialAccountBalance-newAccountInitialBalance;
		newAccountCurrentBalance = newAccountInitialBalance;
		newAccountNumber = paraOpenNewAccountPage.getNewAccountId();
		System.out.println(newAccountNumber);	
		paraAccountServicePage.clickOnAccountsOverviewLink();
		Thread.sleep(3000);
		Assert.assertEquals(paraAccountOverviewPage.getAccountBalance(newAccountNumber),"$"+df.format(newAccountCurrentBalance));
		Assert.assertEquals(paraAccountOverviewPage.getAccountBalance(initialAccountNumber),"$"+df.format(initialAccountCurrentBalance));
		logger.info("New Account Opening Successfull");
	}
	
	@Test(dependsOnMethods = "verifyOpenNewAccount",priority = 7)
	public void verifyFundsTransfer() throws InterruptedException
	{
		paraTransferFundsPage = new ParaTransferFundsPage(driver);
		paraAccountServicePage.clickOnTransferFundsLink();
		paraTransferFundsPage.enterAmountToBeTransferred(amountToBeTransferred);
		paraTransferFundsPage.selectFromAccountId(newAccountNumber);
		paraTransferFundsPage.clickOnTransfer();
		Thread.sleep(2000);
		Assert.assertEquals(paraAccountServicePage.getTransferCompleteMessage(),"Transfer Complete!");
		newAccountCurrentBalance = newAccountCurrentBalance-amountToBeTransferred;
		initialAccountCurrentBalance = initialAccountCurrentBalance+amountToBeTransferred;
		paraAccountServicePage.clickOnAccountsOverviewLink();
		Thread.sleep(3000);
		Assert.assertEquals(paraAccountOverviewPage.getAccountBalance(newAccountNumber),"$"+df.format(newAccountCurrentBalance));
		Assert.assertEquals(paraAccountOverviewPage.getAccountBalance(initialAccountNumber),"$"+df.format(initialAccountCurrentBalance));
		logger.info("Fund Transfer Successfull");
	}
	
	@Test(dependsOnMethods = "verifyLogin",priority = 8)
	public void verifyBillPayment() throws InterruptedException
	{
		paraAccountServicePage.clickOnPayBillLink();
		paraBillPaymentServicePage = new ParaBillPaymentServicePage(driver);
		paraBillPaymentServicePage.enterPayeeName("Vikesh");
		paraBillPaymentServicePage.enterPayeeStreetAddress("Birpur");
		paraBillPaymentServicePage.enterPayeeCityAddress("Begusarai");
		paraBillPaymentServicePage.enterPayeeStateAddress("Bihar");
		paraBillPaymentServicePage.enterPayeeZipCode("851101");
		paraBillPaymentServicePage.enterPayeePhoneNumber("1234567891");
		paraBillPaymentServicePage.enterPayeeAccountNumber(newAccountNumber);
		paraBillPaymentServicePage.enterPayeeAccountNumberConfirmation(newAccountNumber);
		paraBillPaymentServicePage.enterAmount(billsToBePaid);
		paraBillPaymentServicePage.selectFromAccount(newAccountNumber);
		paraBillPaymentServicePage.clickOnSendPaymentBtn();
		Thread.sleep(3000);
		Assert.assertEquals(paraAccountServicePage.getBillPaymentResultMessage(),"Bill Payment Complete");
		newAccountCurrentBalance = newAccountCurrentBalance-billsToBePaid;
		paraAccountServicePage.clickOnAccountsOverviewLink();
		Thread.sleep(3000);
		Assert.assertEquals(paraAccountOverviewPage.getAccountBalance(newAccountNumber),"$"+df.format(newAccountCurrentBalance));
		logger.info("Bill Payment Successfull");
	}
	
	@Test(priority = 9)
	public void verifyTransactionDetailsByAmountAPI()
	{
		RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/"+newAccountNumber+"/transactions/amount/"+billsToBePaid+"";
		RequestSpecification request = RestAssured.given().auth().basic(loginUserName, loginPassword);
		Response response = request.get();
		System.out.println(response.asString());
	}
}
