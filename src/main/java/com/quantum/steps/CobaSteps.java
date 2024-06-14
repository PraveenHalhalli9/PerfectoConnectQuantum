package com.quantum.steps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.pages.GooglePage;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class CobaSteps {

	

	@Given("^I enter the credentials for Coba App$")
	public void I_am_cobaApp() throws Throwable {
		new QAFExtendedWebElement("letsGo").click();
		try {
			new QAFExtendedWebElement("closeButton").click();
		} catch (Exception e) {
			;
		}
		new QAFExtendedWebElement("userName").click();
		new QAFExtendedWebElement("userNameField").sendKeys("4177854728");
		new QAFExtendedWebElement("password").click();
		new QAFExtendedWebElement("passwordField").sendKeys("2902290210");
		new QAFExtendedWebElement("loginButton").click();
	   CommonStep.click("menuBtn");

		
		//Swipe
		//acceptAll
		//profileButton
		//backButton
		//analyticsButton
		//startnowButton
		//swipe
		//skipButton
		//radioOption1
		//radioOption2
		//radioOption3
	
	}

	@When("^i verify the skip button$")
	public void I_skip_coba() throws Throwable {
		
//		DeviceUtils.swipe("50%,80%", "50%,20%");
//		new QAFExtendedWebElement("acceptAll").click();
//		Thread.sleep(6000);
//		new QAFExtendedWebElement("skipButton").click();
		//Thread.sleep(6000);
		//DeviceUtils.swipe("50%,60%", "50%,40%");
		
	//	DeviceUtils.swipe("50%,50%", "50%,45%");	
		Map<String, Object> params = new HashMap<>();
		params.put("start", "50%,50%");
		params.put("end", "50%,43%");
		params.put("duration", "1");
		DeviceUtils.getQAFDriver().executeScript("mobile:touch:swipe", params);
		
//		new QAFExtendedWebElement("profileButton").click();
//		new QAFExtendedWebElement("backButton").click();
//		new QAFExtendedWebElement("analyticsButton").click();
//		Thread.sleep(6000);
//		new QAFExtendedWebElement("startnowButton").click();
//		Thread.sleep(3000);
//		DeviceUtils.switchToContext("WEBVIEW_1");
//		
//	
//		
//		try {
//			new QAFExtendedWebElement("skipButton").isDisplayed();
//		} catch (Exception e) {
//			;
//		}
//		DeviceUtils.swipe("50%,80%", "50%,20%");
//		AppiumUtils.getAndroidDriver().getPageSource();
//		new QAFExtendedWebElement("skipButton").isDisplayed();
//		new QAFExtendedWebElement("radioOption1").click();
//		new QAFExtendedWebElement("radioOption2").click();
//		new QAFExtendedWebElement("radioOption3").click();
//		new QAFExtendedWebElement("skipButton").click();
		//new QAFExtendedWebElement("closeButton1").click();
		
		
		DeviceUtils.installApp("", "", "","iOSResign");
		
	
		
	}

	@Then("^i logout of the application$")
	public void i_logout_application() throws Throwable {
	
		new QAFExtendedWebElement("btn.log").click();
		new QAFExtendedWebElement("btn.logout").click();
	}
	
	@Then("^i verify content$")
	public void i_verify_application() throws Throwable {
	
		
		QAFExtendedWebElement rolexBtn = new QAFExtendedWebElement("rolexButton");
		
//		   DeviceUtils.closeApp("com.leumi.leumiwallet", "identifier");
//           DeviceUtils.startApp("com.leumi.leumiwallet", "identifier");
//           
//           QAFExtendedWebElement ele = DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"eula.join-message-text\"]");
//           
		rolexBtn.isDisplayed();
		rolexBtn.click();
		
		Thread.sleep(5000);
//          QAFExtendedWebElement ele2 = DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"Login.userName-TextInput.input-field\"]");
//           ele2.isDisplayed();
		
		//if(DriverUtils.isIOS()) {
			QAFExtendedWebElement contBtn = new QAFExtendedWebElement("continueButton");
			contBtn.click();
			
			System.out.println(DeviceUtils.getCurrentContext());
			
			System.out.println(AppiumUtils.getIOSDriver().getContextHandles());
			
			  //String[] contextList = DeviceUtils.getCurrentContextHandles().split(",");
			  
			// DeviceUtils.switchToContext(contextList[1]);
			  
			
			System.out.println(DeviceUtils.getCurrentContext());
			
			
			
	//	}
		
	}
	
	@Then("^i login to DB app and perform registeration process$")
	public void i_logout_application1() throws Throwable {
	
		DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Français\"]").click();
		
		//DeviceUtils.getQAFDriver().findElementByXPath("(//android.widget.Button)[4]").click();
		
		Map<String, Object> params = new HashMap<>();
		params.put("label", "Accepter tous les cookies");
		params.put("threshold", 80);
		params.put("duration", "20");
		params.put("language", "English");
		DeviceUtils.getQAFDriver().executeScript("mobile:button-text:click", params);
		
		try {
			DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Démarrez maintenant\"]").click();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Démarrez maintenant\"]").click();
		}
		
		try {
			DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Créez votre profil\"]").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Créez votre profil\"]").click();
		}
		
		DeviceUtils.getQAFDriver().findElementByXPath("//android.widget.EditText").click();
		
		DeviceUtils.getQAFDriver().findElementByXPath("//android.widget.EditText").sendKeys("testat07");
		
		AppiumUtils.getAndroidDriver().hideKeyboard();
		
		DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Continuez\"]").click();
		
		CommonStep.click("db.editField");
		
		CommonStep.sendKeys("124577", "db.editField");
	
		
	CommonStep.click("db.editField2");
		
		CommonStep.sendKeys("124577", "db.editField2");
		
//        DeviceUtils.getQAFDriver().findElementByXPath("(//*[@class=\"android.widget.EditText\"])[1]").click();
//       		
//		DeviceUtils.getQAFDriver().findElementByXPath("(//*[@class=\"android.widget.EditText\"])[1]").sendKeys("124577");
//		
//	     DeviceUtils.getQAFDriver().findElementByXPath("(//*[@class=\"android.widget.EditText\"])[2]").click();
//			
//			DeviceUtils.getQAFDriver().findElementByXPath("(//*[@class=\"android.widget.EditText\"])[2]").sendKeys("124577");
			
			AppiumUtils.getAndroidDriver().hideKeyboard();
			
			 DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Continuez\"]").click();
			 
//			   DeviceUtils.getQAFDriver().findElementByXPath("(//android.widget.EditText)[1]").click();
//				
//				DeviceUtils.getQAFDriver().findElementByXPath("(//android.widget.EditText)[1]").sendKeys("12345678");
				
			 try {
				CommonStep.click("db.editField3");
			 }catch(Exception e) {
				
					CommonStep.click("db.editField3");
			 }
			 
				try {
					CommonStep.sendKeys("12345678", "db.editField3");
				} catch (Exception e) {
					;
				}
				
				 //DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Signez\"]").click();
				 
				AppiumUtils.getAndroidDriver().hideKeyboard();
				
				 CommonStep.click("db.signez");
				 
				 try {
					DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]").click();
				} catch (Exception e) {
					;
				}
					Thread.sleep(10000);
					
					try {
						DeviceUtils.getQAFDriver().findElementByXPath("//android.widget.Button[@text=\"PARAMÈTRES\"]").click();
						DeviceUtils.startApp("com.db.pbc.mybank.be.beta", "identifier");
						 CommonStep.click("db.signez");
					} catch (Exception e) {
						;
					}
					
					// DeviceUtils.startApp("com.db.pbc.mybank.be.beta", "identifier");
					
					Thread.sleep(10000);
					 
				 DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"undefined\"]").click();
		
				 DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Finalisez\"]").click();
				 
				 Thread.sleep(10000);
				 
				 DeviceUtils.getQAFDriver().findElementByXPath("//*[@text=\"Ok\"]").click();
				 
				 DeviceUtils.closeApp("com.db.pbc.mybank.be.beta", "identifier");
				 
				 DeviceUtils.startApp("com.db.pbc.mybank.be.beta", "identifier");
				 
				 Thread.sleep(5000);
				 
				 DeviceUtils.getQAFDriver().findElementByXPath("(//android.widget.Button)[1]").click();
				 
				 try {
				 DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"settings-home\" or ((@class=\"android.widget.Button\") and @index=0 and  @instance=0)]").click();
				 }catch(Exception e) {
					 
					 DeviceUtils.getQAFDriver().findElementByXPath("//*[@resource-id=\"settings-home\"]").click();
				 }
				 
				 Thread.sleep(5000);
				 
				 DeviceUtils.getQAFDriver().findElementByXPath("//android.view.View[2]/android.widget.Button[1]").click();
				 
				 
	
	}

	
}