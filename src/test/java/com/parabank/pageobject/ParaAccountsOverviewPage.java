package com.parabank.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaAccountsOverviewPage {

WebDriver driver;
	
	public ParaAccountsOverviewPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[@id='accountTable']//tbody//tr[1]//td[1]")
	WebElement initialAccountNumber;
	
	@FindBy(xpath="//table[@id='accountTable']//tbody//tr[1]//td[2]")
	WebElement initialAccountBalance;
	
	WebElement accountBalance;
	
	public String getAccountBalance(String accountNumber)
	{
		accountBalance = driver.findElement(By.xpath("//table[@id='accountTable']//tbody//a[contains(text(),'"+accountNumber+"')]//following::td[1]"));
		return accountBalance.getText();
	}
	
	public String getInitialAccountNumber()
	{
		return initialAccountNumber.getText();
	}
	
	public String getInitialAccountBalance()
	{
		return initialAccountBalance.getText();
	}
	
}
