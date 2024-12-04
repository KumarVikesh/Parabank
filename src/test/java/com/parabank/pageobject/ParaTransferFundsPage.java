package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParaTransferFundsPage {

WebDriver driver;
	
	public ParaTransferFundsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='amount']")
	WebElement amountTxtBox;
	
	@FindBy(xpath="//select[@id='fromAccountId']")
	WebElement fromAccountDropDown;
	
	@FindBy(xpath="//select[@id='toAccountId']")
	WebElement toAccountDropDown;
	
	@FindBy(xpath="//input[@type='submit' and @value='Transfer']")
	WebElement transferBtn;
	
	public void enterAmountToBeTransferred(double amount)
	{
		amountTxtBox.sendKeys(String.valueOf(amount));
	}
	
	public void selectFromAccountId(String accountNumber)
	{
		Select select = new Select(fromAccountDropDown);
		select.selectByVisibleText(accountNumber);
	}
	
	public void clickOnTransfer()
	{
		transferBtn.click();
	}
}
