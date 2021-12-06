package com.teladoc.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.teladoc.qa.util.TestUtil;
import com.teladoc.qa.base.TestBase;
import com.teladoc.qa.pages.HomeAddPage;

public class UserTest extends TestBase{
	HomeAddPage homeAddPage;
	TestUtil testUtil;
	String sheetName = "UserDetails";
	
	public UserTest(){
		super();
	}
	
	@BeforeClass
	public void setUp() {
		initialization();
		homeAddPage=new HomeAddPage();
		testUtil = new TestUtil();
	}
	
	@DataProvider
	public Object[][] getTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData")  // Add User Test
	public void addUserTest(String firstname, String lastname, String username, String password, String role, String emailaddress, String phonenumber) {
		homeAddPage.addUser(firstname,lastname,username,password, role, emailaddress, phonenumber);
	}
	
	@Test(priority=2)  // Verify the User added 
	public void verifyAddedUserTest() {
		
		
		String actualUser=homeAddPage.validateUser();
		Assert.assertEquals(actualUser, prop.getProperty("userAdded"));	
	}
	
	@Test(priority=3)  // Delete the User and verify if its removed from the list 
	public void verifyDeleteUserTest() {
	
		homeAddPage.deleteUser();
		Assert.assertTrue(homeAddPage.checkIfUserDeleted());	
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
}
