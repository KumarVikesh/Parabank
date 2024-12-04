package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParaOpenNewAccountPage {

	WebDriver driver;
	public ParaOpenNewAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//select[@id='type']")
	WebElement accountTypedropDown;
	
	@FindBy(xpath="//input[@type='button' and @value='Open New Account']")
	WebElement openNewAccountBtn;
	
	@FindBy(xpath="//div[@id ='openAccountResult']//a[@id='newAccountId']")
	WebElement newAccountId;
	
	public void selectAccountType(String accountType)
	{
		Select select = new Select(accountTypedropDown);
		select.selectByVisibleText(accountType);
	}
	
	public void clickOnOpenNewAccountBtn()
	{
		openNewAccountBtn.click();
	}
	
	public String getNewAccountId()
	{
		return newAccountId.getText();
	}
}
