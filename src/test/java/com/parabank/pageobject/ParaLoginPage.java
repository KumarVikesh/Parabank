package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaLoginPage {
	
	WebDriver driver;
	public ParaLoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='username']")
	WebElement userNameTxtBox;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordTxtBox;
	
	@FindBy(xpath="//input[@type='submit' and @value='Log In']")
	WebElement logInBtn;
	
	public void enterUserName(String userName)
	{
		userNameTxtBox.sendKeys(userName);
	}
	
	public void enterPassword(String password)
	{
		passwordTxtBox.sendKeys(password);
	}
	public void clickOnLoginBtn()
	{
		logInBtn.click();
	}
}
