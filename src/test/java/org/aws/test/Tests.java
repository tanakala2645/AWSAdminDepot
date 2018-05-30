package org.aws.test;

import org.aws.browsers.BrowserInvoke;
import org.aws.constants.AwsConstants;
import org.aws.pageobjects.CreateNewUserPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Anil T
 * @desc: Utilized page objects for one of the test, constants for validation, test data for driving the test
 * No exhaustive tests have been considered, the intent was to make sure we have utilized the best practices for longer run
 */

public class Tests {

	// Reference instantiation
	private WebDriver driver;
	private CreateNewUserPO createNewUserPO;

	// test data declaration 
	// Note - Drive username&email creation dynamically by creating separate utility functions
	private String username = "test.username002";
	private String password = "password";
	private String email = username + "@example.com";

	//Tests
	@Test(priority = 1)
	public void activeAdminDepot() throws Exception {
		driver = BrowserInvoke.getDriver();
		driver.get(BrowserInvoke.getBaseAppURL());
		driver.manage().window().maximize();
		String pageTitle = driver.getTitle();
		Assert.assertEquals(AwsConstants.DASHBOARD_ADMIN_DEPOT_TITLE, pageTitle);
	}

	@Test(priority = 2)
	public void navigateToUsers() throws Exception {
		driver.findElement(By.linkText("Users")).click();
		String userPageTitle = driver.getTitle();
		Assert.assertEquals(AwsConstants.USERS_ADMIN_TITLE, userPageTitle);
	}

	//Utilized page object model for one of the test
	@Test(priority = 3)
	public void navigateToNewUser() throws Exception {
		createNewUserPO.clickUsers();
		String newUserPageTitle = driver.getTitle();
		Assert.assertEquals(AwsConstants.NEW_USERS_ADMIN_TITLE, newUserPageTitle);
		createNewUserPO = new CreateNewUserPO(driver);
		createNewUserPO.getUserName().sendKeys(username);
		createNewUserPO.getPassword().sendKeys(password);
		createNewUserPO.getEmail().sendKeys(email);
		createNewUserPO.clickCreateUserButton().click();
	}

	@Test(priority = 4)
	public void ValidateUserCreation() throws Exception {
		WebElement infoText = driver.findElement(By.className("flash_notice"));
		Assert.assertEquals(infoText.getText(), "User was successfully created.");
		driver.findElement(By.linkText("Users")).click();
	}

	@Test(priority = 5)
	public void navigateToFilters() throws Exception {
		driver.findElement(By.id("q_username")).sendKeys(username);
		driver.findElement(By.name("commit")).click();
		String actualUserName = driver.findElement(By.xpath("//td[@class=\"col col-username\"]")).getText();
		Assert.assertEquals(actualUserName, username);
	}
}
