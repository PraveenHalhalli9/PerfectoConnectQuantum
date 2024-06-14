package com.quantum.steps;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.ExpenseTrackerHomePage;
import com.quantum.pages.ExpenseTrackerLoginPage;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.DeviceUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;

@QAFTestStepProvider
public class ExpenseTrackerSteps {

	@Then("I should see expense tracker login screen")
	public void verifyExpenseTrackerLogin() {
		new ExpenseTrackerLoginPage().verifyExpenseTrackerLoginScreen();
	System.out.println(ConfigurationManager.getBundle().getPropertyValue("JOB_NAME"));
	}
	
	@Then("I should see expense tracker Native login screen")
	public void verifyExpenseTrackerNativeLogin() {
		new ExpenseTrackerLoginPage().verifyExpenseTrackerNativeLoginScreen();
		
		
	}
	
	@When("I enter \"(.*?)\" and \"(.*?)\" in native login screens")
	public void iEnterLoginDetilsInNativeLoginScreen(String email, String password) throws InterruptedException {
		new ExpenseTrackerLoginPage().loginNative(email, password);
		
	}
	
	
	@Then("I should see expense tracker home screen")
	public void iShouldSeeExpenseTrackerHomeScreen() {
		//new ExpenseTrackerHomePage().verifyHomeScreen();
		
		//DeviceUtils.installApp("PUBLIC:Invoice App Xcode 12.5.ipa", "instrument", "true", "true");
	}
	
	@When("I enter expense details and save")
	public void iEnterExpenseDetailsAndSave() {
		//new ExpenseTrackerHomePage().enterExpenseDetails();
	}
	
	@Then("I should see error popup soon")
	public void iShouldSeeErrorPopup() throws InterruptedException {
		//new ExpenseTrackerHomePage().verifyPopupText(); 
		
		AppiumUtils.getAppiumDriver().terminateApp("io.perfecto.expense.tracker");

  
		//DeviceUtils.uninstallApp("io.expense.tracker", "identifier");
		Thread.sleep(3000);
		//DeviceUtils.startApp("io.expense.tracker", "identifier");
		System.out.println("App was uninstalled");
	}
}
