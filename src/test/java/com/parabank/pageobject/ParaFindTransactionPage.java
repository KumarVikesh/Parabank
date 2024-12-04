package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaFindTransactionPage {
	
	WebDriver driver;
	
	public ParaFindTransactionPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@type='submit' and @id='findByAmount']")
	WebElement findTransactionsByAmountBtn;
	
	@FindBy(xpath="//input[@id='amount']")
	WebElement amountTxtBox;
	
	public void enterAmount(float amount)
	{
		amountTxtBox.sendKeys(String.valueOf(amount));
	}
	
	public void clickOnFindTransactionsByAmountButton()
	{
		findTransactionsByAmountBtn.click();
	}
}
