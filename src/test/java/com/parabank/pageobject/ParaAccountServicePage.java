package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaAccountServicePage {

	WebDriver driver;
	
	public ParaAccountServicePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Log Out')]")
	WebElement logOutLink;
	
	@FindBy(xpath="//a[contains(text(),'Open New Account')]")
	WebElement openNewAccountLink;
	
	@FindBy(xpath="//a[contains(text(),'Accounts Overview')]")
	WebElement accountsOverviewLink;
	
	@FindBy(xpath="//a[contains(text(),'Transfer Funds')]")
	WebElement transferFundsLink;
	
	@FindBy(xpath="//a[contains(text(),'Bill Pay')]")
	WebElement payBillLink;
	
	@FindBy(xpath="//div[@id='rightPanel']//h1")
	WebElement registrationCompletionMessage;
	
	@FindBy(xpath="//div[@id='openAccountResult']//h1")
	WebElement openAccountResult;
	
	@FindBy(xpath="//div[@id='showResult']//h1")
	WebElement transferComplete;
	
	@FindBy(xpath="//div[@id='showOverview']//h1")
	WebElement showOverViewMessage;
	
	@FindBy(xpath="//div[@id='billpayResult']//h1")
	WebElement billPaymentResultMessage;
	
	
	
	public void clickOnLogout()
	{
		logOutLink.click();
	}
	
	public void clickOnOpenNewAccountLink()
	{
		openNewAccountLink.click();
	}
	
	public void clickOnAccountsOverviewLink()
	{
		accountsOverviewLink.click();
	}
	
	public void clickOnTransferFundsLink()
	{
		transferFundsLink.click();
	}
	
	public void clickOnPayBillLink()
	{
		payBillLink.click();
	}
	
	public String  getRegistrationCompletionMessage()
	{
		return registrationCompletionMessage.getText();
	}
	
	public String getOpenAccountResult()
	{
		return openAccountResult.getText();
	}
	
	public String getTransferCompleteMessage()
	{
		return transferComplete.getText();
	}
	
	public String showAccountsOverviewMessage()
	{
		return showOverViewMessage.getText();
	}
	
	public String getBillPaymentResultMessage()
	{
		return billPaymentResultMessage.getText();
	}
}
