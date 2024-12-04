package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaRegisterPage {

	WebDriver driver;
	
	public ParaRegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[contains(@id,'firstName')]")
	WebElement firstNameTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'lastName')]")
	WebElement lastNameTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'address.street')]")
	WebElement addressStreetTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'address.city')]")
	WebElement addressCityTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'address.state')]")
	WebElement addressStateTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'address.zipCode')]")
	WebElement addressZipCodeTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'phoneNumber')]")
	WebElement phoneNumberTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'ssn')]")
	WebElement ssnTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'username')]")
	WebElement usernameTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'password')]")
	WebElement passwordTxtBox;
	
	@FindBy(xpath="//input[contains(@id,'repeatedPassword')]")
	WebElement repeatedPasswordTxtBox;
	
	@FindBy(xpath="//input[@type='submit' and @value='Register']")
	WebElement registerBtn;
	
	public void enterFirstName(String firstName)
	{
		firstNameTxtBox.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName)
	{
		lastNameTxtBox.sendKeys(lastName);
	}
	
	public void enterStreetAddress(String street)
	{
		addressStreetTxtBox.sendKeys(street);
	}
	
	public void enterCity(String city)
	{
		addressCityTxtBox.sendKeys(city);
	}
	
	public void enterState(String state)
	{
		addressStateTxtBox.sendKeys(state);
	}
	
	public void enterZipCode(String zipCode)
	{
		addressZipCodeTxtBox.sendKeys(zipCode);
	}
	
	public void enterPhoneNumber(String phoneNumber)
	{
		phoneNumberTxtBox.sendKeys(phoneNumber);
	}
	
	public void enterSSN(String ssn)
	{
		ssnTxtBox.sendKeys(ssn);
	}
	
	public void enterUserName(String userName)
	{
		usernameTxtBox.sendKeys(userName);
	}
	
	public void enterPassword(String password)
	{
		passwordTxtBox.sendKeys(password);
	}
	
	public void enterRepeatPassword(String password)
	{
		repeatedPasswordTxtBox.sendKeys(password);
	}
	
	public void clickOnRegister()
	{
		registerBtn.click();
	}
}
