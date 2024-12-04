package com.parabank.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParaCustomerLoginPage {

	WebDriver driver;
	
	public ParaCustomerLoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
