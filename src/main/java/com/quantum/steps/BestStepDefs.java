package com.quantum.steps;
import java.util.List;
import java.util.Map;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.pages.GooglePage;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.ReportUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class BestStepDefs {

	

	@Given("^I enter the credentials$")
	public void I_am_BestApp() throws Throwable {
		
		new QAFExtendedWebElement("btn.username").sendKeys("38189");
		new QAFExtendedWebElement("btn.password").sendKeys("123456");
		new QAFExtendedWebElement("btn.login").click();
	
	}

	@When("^i view flight view$")
	public void I_search_for_flight() throws Throwable {
		
		
		try {
			new QAFExtendedWebElement("btn.ok").click();
		} catch (Exception e) {
			;
		}
		
		try {
			new QAFExtendedWebElement("btn.ok").click();
		} catch (Exception e) {
			;
		}
		
		new QAFExtendedWebElement("btn.flight").click();
		new QAFExtendedWebElement("btn.firstCell").click();
		
	}

	@Then("^i logout of the application$")
	public void i_logout_application() throws Throwable {
	
		new QAFExtendedWebElement("btn.log").click();
		new QAFExtendedWebElement("btn.logout").click();
	}

	
}