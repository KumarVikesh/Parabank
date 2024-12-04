package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParaBillPaymentServicePage {

	WebDriver driver;
	
	public ParaBillPaymentServicePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='payee.name']")
	WebElement payeeNameTxtBox;
	
	@FindBy(xpath="//input[@name='payee.address.street']")
	WebElement payeeSteetAddressTxtBox;
	
	@FindBy(xpath="//input[@name='payee.address.city']")
	WebElement payeeCityAddressTxtBox;
	
	@FindBy(xpath="//input[@name='payee.address.state']")
	WebElement payeeStateAddressTxtBox;
	
	@FindBy(xpath="//input[@name='payee.address.zipCode']")
	WebElement payeeZipCodeTxtBox;
	
	@FindBy(xpath="//input[@name='payee.phoneNumber']")
	WebElement payeePhoneNumberTxtBox;
	
	@FindBy(xpath="//input[@name='payee.accountNumber']")
	WebElement payeeAccountNumberTxtBox;
	
	@FindBy(xpath="//input[@name='verifyAccount']")
	WebElement payeeVerifyAccountTxtBox;
	
	@FindBy(xpath="//input[@name='amount']")
	WebElement amount;
	
	@FindBy(xpath="//select[@name='fromAccountId']")
	WebElement fromAccountIdDropDown;
	
	@FindBy(xpath="//input[@type='button' and @value='Send Payment']")
	WebElement sendPaymentBtn;
	
	public void enterPayeeName(String payeeName)
	{
		payeeNameTxtBox.sendKeys(payeeName);
	}
	
	public void enterPayeeStreetAddress(String payeeStreetAddress)
	{
		payeeSteetAddressTxtBox.sendKeys(payeeStreetAddress);
	}
	
	public void enterPayeeCityAddress(String payeeCityAddress)
	{
		payeeCityAddressTxtBox.sendKeys(payeeCityAddress);
	}
	
	public void enterPayeeStateAddress(String payeeStateAddress)
	{
		payeeStateAddressTxtBox.sendKeys(payeeStateAddress);
	}
	
	public void enterPayeeZipCode(String payeeZipCode)
	{
		payeeZipCodeTxtBox.sendKeys(payeeZipCode);
	}
	
	public void enterPayeePhoneNumber(String payeePhoneNumber)
	{
		payeePhoneNumberTxtBox.sendKeys(payeePhoneNumber);
	}
	
	public void enterPayeeAccountNumber(String payeeAccountNumber)
	{
		payeeAccountNumberTxtBox.sendKeys(payeeAccountNumber);
	}
	
	public void enterPayeeAccountNumberConfirmation(String payeeAccountNumber)
	{
		payeeVerifyAccountTxtBox.sendKeys(payeeAccountNumber);
	}
	
	public void enterAmount(double amountToBeSent)
	{
		amount.sendKeys(String.valueOf(amountToBeSent));
	}
	
	public void selectFromAccount(String accountNumber)
	{
		Select select = new Select(fromAccountIdDropDown);
		select.selectByVisibleText(accountNumber);
	}
	
	public void clickOnSendPaymentBtn()
	{
		sendPaymentBtn.click();
	}
}
