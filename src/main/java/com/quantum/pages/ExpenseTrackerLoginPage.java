package com.quantum.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.steps.PerfectoApplicationSteps;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;

public class ExpenseTrackerLoginPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@FindBy(locator = "login.email.textfield")
	private QAFWebElement logintextfiled;
	
	@FindBy(locator = "login.emailNative.textfield")
	private QAFWebElement emailNativeTextfield;
	
	@FindBy(locator = "login.headerTextNative.label")
	private QAFWebElement headerTextNative;
	
	@FindBy(locator = "login.passwordNative.textfield")
	private QAFWebElement passwordlNativeTextfield;
	
	@FindBy(locator = "login.loginNative.btn")
	private QAFWebElement loginlNativeButton;
	
	@FindBy(locator = "login.email.textFieldValue")
	private QAFWebElement loginlEmailTextFieldValue;
	
	public void verifyExpenseTrackerLoginScreen() {
		Map<String, Object> params = new HashMap<>();
		params.put("content", "Email");
		params.put("timeout", "15");
		driver.executeScript("mobile:text:find", params);
	}
	
	public void verifyExpenseTrackerNativeLoginScreen() {
		if(DriverUtils.getDriver().getCapabilities().getCapability("platformName").toString().equalsIgnoreCase("ios")) {
			QAFExtendedWebElement ele = new QAFExtendedWebElement(By.xpath("//*[@label='OK']"));
			try {
				ele.click();
			} catch (Exception e) {
			}
		}
		//ReportUtils.logAssert("Verify Login screen title", headerTextNative.isDisplayed());
		//ReportUtils.logAssert("Verify Login screen Email", emailNativeTextfield.isDisplayed());
	}
	
	public void loginNative(String email, String password) throws InterruptedException {
		//new QAFExtendedWebElement("login.emailNative.textfield").isDisplayed();
	//	new QAFExtendedWebElement("login.emailNative.textfield").sendKeys(email);
		
		//DeviceUtils.uninstallApp("io.perfecto.expense.tracker", "identifier");
		
       // PerfectoApplicationSteps.installApp(ConfigurationManager.getBundle().getString("driver.capabilities.app"));
		
		//System.out.println(ConfigurationManager.getBundle().getString("perfectoTunnel"));
		
		emailNativeTextfield.sendKeys(email);
		//ReportUtils.logAssert("Email was entered as expected", loginlEmailTextFieldValue.getText().equalsIgnoreCase(email));
		passwordlNativeTextfield.sendKeys(password);
		if(DriverUtils.getDriver().getCapabilities().getCapability("platformName").toString().equalsIgnoreCase("ios")) {
			DriverUtils.getIOSDriver().hideKeyboard();
		}else {
			DriverUtils.getAndroidDriver().hideKeyboard();
		}
		
		
//		System.out.println(loginlNativeButton.getRect().getHeight());
//		
//		System.out.println(loginlNativeButton.getRect().getWidth());
		
		Thread.sleep(3000);
		
		DeviceUtils.getQAFDriver().findElementByXPath("//XCUIElementTypeSwitch").click();
		
		
		loginlNativeButton.click();
		
		DeviceUtils.setFingerprint("identifier","io.perfecto.expense.tracker", "success", "");
		
		//DeviceUtils.set
	}
}
