/**
 * 
 */
package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.ClipBoardUtil;
import com.quantum.pages.CalcPage;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.DeviceUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

@QAFTestStepProvider
public class CalcStepsDefs {

	CalcPage calcPage = new CalcPage();

	@When("clear Calculator")
	public void clearCalculator() {
		//calcPage.clear();
	//	AppiumUtils.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.DEL));
		
		

		QAFExtendedWebElement editBox = DeviceUtils.getQAFDriver().findElement(By.xpath("//*[@name=\"Login\"]"));
		
		int scaleFactor = DeviceUtils.getScale();
		Rectangle rect = editBox.getRect();
		
		System.out.println(rect.getX());
		
		System.out.println(rect.getHeight());
		
		int x = (rect.getX() + rect.getWidth() / 2) * scaleFactor;
		int y = (rect.getY() + rect.getHeight() / 2 ) * scaleFactor;
		
		System.out.println(x + ","+ y);
		
	
	}

	@When("add \"(.+)\" to \"(.+)\"")
	public void addInto(long l1, long l2) {
		calcPage.add(l1, l2);
	}

	@Then("result should be \"(.+)\"")
	public void resultShouldBe(long l1) {
		calcPage.verifyResult(l1);
	
	}

	public static int getScale() {
		String deviceRes = getDeviceProperty("resolution");
		int appWidth = (new QAFExtendedWebElement("xpath=/*/*")).getSize().getWidth();
		return Math.round((float) (Integer.parseInt(deviceRes.split("\\*")[0]) / appWidth));
	}
	
	public static String getDeviceProperty(String property) {
		HashMap params = new HashMap();
		params.put("property", property);
		return (String) DeviceUtils.getQAFDriver().executeScript("mobile:handset:info", new Object[]{params});
	}
	
	public static void touch(String point) {
		HashMap params = new HashMap();
		params.put("location", point);
		DeviceUtils.getQAFDriver().executeScript("mobile:touch:tap", new Object[]{params});
	}

	public static void selectText(String Text) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("content", Text);
		params.put("threshold", 80);
		params.put("timeout", 20);
		params.put("match", "contain");
		DeviceUtils.getQAFDriver().executeScript("mobile:text:select", params);	
	}
	
}
