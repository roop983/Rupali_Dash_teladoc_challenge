package com.teladoc.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.teladoc.qa.base.TestBase;

public class HomeAddPage extends TestBase{
	
	@FindBy(xpath="//button[@class='btn ng-scope ng-binding btn-primary']")
	WebElement okButton;
	
	@FindBy(css="tbody>tr>td:nth-of-type(3)")
	List<WebElement> list;
	
	@FindBy(css="button[type='add']")
	WebElement addUser;
	
	@FindBy(css="input[name='FirstName']")
	WebElement firstName;
	
	@FindBy(css="input[name='LastName']")
	WebElement lastName;
	
	@FindBy(css="input[name='UserName']")
	WebElement userName;
	
	@FindBy(css="input[name='Password']")
	WebElement passWord;
	
	@FindBy(css="input[value='16']")
	WebElement customer;
	
	@FindBy(css="select[name='RoleId']")
	WebElement roleID;
	
	
	@FindBy(css="input[name='Email']")
	WebElement email;
	
	@FindBy(css="input[name='Mobilephone']")
	WebElement cellPhone;
	
	@FindBy(css="button[ng-click='save(user)']")
	WebElement save;
	
	
	public HomeAddPage() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public void addUser(String firstname, String lastname, String username, String password, String role, String emailaddress, String phonenumber) {
		addUser.click();
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		userName.sendKeys(username);
		passWord.sendKeys(password);
		customer.click();
		Select roles=new Select(roleID);
		roles.selectByVisibleText(role);
		email.sendKeys(emailaddress);
		cellPhone.sendKeys(phonenumber);		
		save.click();
		
	}
	
	public String validateUser() {
		String actualUser="";
		for(WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase(prop.getProperty("userAdded"))) {
				actualUser=ele.getText();	
			}
		}
		return actualUser;
	}	
	public void deleteUser() {	
		list=driver.findElements(By.cssSelector("tbody>tr>td:nth-of-type(3)"));
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getText().equalsIgnoreCase(prop.getProperty("userDeleted"))) {
				int pos=i+1;
		    	 WebElement del=driver.findElement(By.cssSelector("tbody>tr:nth-of-type("+pos+")>td:nth-of-type(11)>button"));
		    	 del.click();
		    	 okButton.click();
		    	 break;
		      }
		}
	}
	public boolean checkIfUserDeleted() {
		list=driver.findElements(By.cssSelector("tbody>tr>td:nth-of-type(3)"));
		int count=0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().equals(prop.getProperty("userDeleted"))) {
				break;
			}
			else {
				count++;
			}
		}
		if(count == list.size()) {
			return true;
		}else {
			return false;
		}		
	}
	
}
