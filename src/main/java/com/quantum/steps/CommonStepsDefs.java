/**
 * 
 */
package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.ConfigurationUtils;
import com.quantum.utils.ConsoleUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


@QAFTestStepProvider
public class CommonStepsDefs {

	@Then("I switch to frame \"(.*?)\"")
	public static void switchToFrame(String nameOrIndex) {
		if (StringUtil.isNumeric(nameOrIndex)) {
			int index = Integer.parseInt(nameOrIndex);
			new WebDriverTestBase().getDriver().switchTo().frame(index);
		} else {
			new WebDriverTestBase().getDriver().switchTo().frame(nameOrIndex);
		}
	}

	@Then("I switch to \"(.*?)\" frame by element")
	public static void switchToFrameByElement(String loc) {
		new WebDriverTestBase().getDriver().switchTo().frame(new QAFExtendedWebElement(loc));
	}

	@When("I am using an AppiumDriver")
	public void testForAppiumDriver() {
		if (ConfigurationUtils.getBaseBundle().getPropertyValue("driver.name").contains("Remote"))
			ConsoleUtils.logWarningBlocks("Driver is an instance of QAFExtendedWebDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of IOSDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof AndroidDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of AndroidDriver");
	}
	
	
	@Then("I clear Safari Cache")
	public static void clearSafariCache() {
		
		DeviceUtils.startApp("com.apple.Preferences", "identifier");
		DeviceUtils.closeApp("com.apple.Preferences", "identifier");
		DeviceUtils.startApp("com.apple.Preferences", "identifier");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", "50%,30%");
        params.put("end","50%,80%");
        params.put("duration", "2");
        DeviceUtils.getQAFDriver().executeScript("mobile:touch:swipe", params);
        
        
	    params.clear();
		params.put("start", "50%,30%");
        params.put("end","50%,80%");
        params.put("duration", "2");
        DeviceUtils.getQAFDriver().executeScript("mobile:touch:swipe", params);
        
        DeviceUtils.switchToContext("NATIVE_APP");
        
		DeviceUtils.getQAFDriver().findElementByXPath("//*[@label=\"Search\"]").click();
		DeviceUtils.getQAFDriver().findElementByXPath("//*[@label=\"Search\"]").sendKeys("Safari");
		DeviceUtils.getQAFDriver().findElementByXPath("(//XCUIElementTypeStaticText[@name='Safari'])[2]/..").click();
				
		  ScrolltoText("Clear History and Website Data");
		  
		  if(DeviceUtils.getDeviceProperty("osVersion").contains("17")) {
			  
			  DeviceUtils.getQAFDriver().findElementByXPath("//*[@label=\"All history\"]").click();
			  DeviceUtils.getQAFDriver().findElementByXPath("//*[@name=\"CloseAllTabsSwitch\"]").click();
			  DeviceUtils.getQAFDriver().findElementByXPath("//*[@name=\"ClearHistoryButton\"]").click();
			  
		  }else {
		  
		
		DeviceUtils.getQAFDriver().findElementByXPath("//*[@label=\"Clear History and Data\"]").click();
		DeviceUtils.getQAFDriver().findElementByXPath("//XCUIElementTypeButton[@label=\"Close Tabs\"]").click();
		  }
	}
	
	@And("I verify the app \"(.*?)\" is installed")
	public static void verifyApp(String appId) {
		
		
	if(AppiumUtils.getAppiumDriver().isAppInstalled(appId)) {
		
		System.out.println(appId+" was installed, hence uninstalling");
		
		DeviceUtils.uninstallApp(appId, "identifier");
		
		
	}else {
		
		System.out.println(appId+" was NOT installed");
		
	}
		
	}
	
	public static void ScrolltoText(String LabelText){
        
        Map<String, Object> params2 = new HashMap<String, Object>();
          
        params2.put("content", LabelText);
        params2.put("scrolling", "scroll");
        params2.put("maxscroll", "5");
        params2.put("next", "SWIPE_UP");
        params2.put("words", "words");
        DeviceUtils.getQAFDriver().executeScript("mobile:text:select", params2);
                  
    }
	
	
}