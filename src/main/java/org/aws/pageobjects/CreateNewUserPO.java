package org.aws.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewUserPO {
	WebDriver driver;

	public CreateNewUserPO(WebDriver driver) {
		this.driver = driver;
	}

	private By username = By.id("user_username");
	private By password = By.id("user_password");
	private By email = By.id("user_email");
	private By createUser = By.xpath("//input[@value='Create User']");
	private By clickUsers = By.linkText("Users");

	public WebElement getUserName() {
		return driver.findElement(username);
	}
	
	public WebElement clickUsers() {
		return driver.findElement(clickUsers);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}

	public WebElement clickCreateUserButton() {
		return driver.findElement(createUser);
	}
}
