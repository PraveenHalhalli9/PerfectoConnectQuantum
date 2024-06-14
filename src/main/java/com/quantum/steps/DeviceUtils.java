package com.quantum.steps;

import com.perfectomobile.httpclient.device.DeviceParameter;
import com.perfectomobile.httpclient.device.DeviceResult;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Validator;
import com.quantum.axe.AxeHelper;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.hamcrest.Matchers;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebElement;

public class DeviceUtils {
	private static final String REPOSITORY_KEY = "perfecto.repository.folder";

	public static QAFExtendedWebDriver getQAFDriver() {
		return (new WebDriverTestBase()).getDriver();
	}

	public static boolean verifyVisualText(String text) {
		return Validator.verifyThat("Text: \"" + text + "\" should be present", isText(text, (Integer) null),
				Matchers.equalTo("true"));
	}

	public static void assertVisualText(String text) {
		Validator.assertThat("Text: \"" + text + "\" must be present", isText(text, Integer.valueOf(60)),
				Matchers.equalTo("true"));
	}

	public static void installApp(String filePath, boolean shouldInstrument) {
		HashMap params = new HashMap();
		params.put("file", filePath);
		if (shouldInstrument) {
			params.put("instrument", "instrument");
		}

		getQAFDriver().executeScript("mobile:application:install", new Object[]{params});
	}

	public static void installAppOnDevice(DeviceResult device) {
		ConfigurationManager.getBundle().setProperty("getQAFDriver().name", "appiumRemotegetQAFDriver()");
		ConfigurationManager.getBundle().setProperty("getQAFDriver().capabilities.deviceName",
				device.getResponseValue(DeviceParameter.DEVICE_ID));
		installApp("perfecto.repository.folder",
				ConfigurationManager.getBundle().getString("app.instrumentation", "noinstrument"));
		getQAFDriver().quit();
	}

	public static void installApp(String repoKey, String instrument) {
		HashMap params = new HashMap();
		params.put("file", ConfigurationManager.getBundle().getString(repoKey, repoKey));
		params.put("instrument", ConfigurationManager.getBundle().getString(instrument, instrument));
		String resultStr = (String) getQAFDriver().executeScript("mobile:application:install", new Object[]{params});
		System.out.println(resultStr);
	}

	public static void installApp(String repoKey, String instrument, String sensorInstrument, String resignEnable) {
		HashMap params = new HashMap();
		params.put("file", ConfigurationManager.getBundle().getString("repoKey", repoKey));
		if (!instrument.isEmpty()) {
			params.put("instrument", instrument);
		}

		if (!sensorInstrument.isEmpty()) {
			params.put("sensorInstrument", sensorInstrument);
		}

		if (!resignEnable.isEmpty()) {
			params.put("resign", resignEnable);
		}

		String resultStr = (String) getQAFDriver().executeScript("mobile:application:install", new Object[]{params});
		System.out.println(resultStr);
	}

	public static void installInstrumantedAppOnAndroid(String repoKey, String instrument, String sensorInstrument,
			String certificateFile, String certificateUser, String certificatePassword, String certificateParams) {
		HashMap params = new HashMap();
		params.put("file", ConfigurationManager.getBundle().getString("repoKey", repoKey));
		if (!ConfigurationManager.getBundle().getString(instrument, instrument).isEmpty()) {
			params.put("instrument", instrument);
		}

		if (!sensorInstrument.isEmpty()) {
			params.put("sensorInstrument", sensorInstrument);
		}

		if (ConfigurationManager.getBundle().getString(instrument, instrument).equals("instrument")) {
			params.put("certificate.file",
					ConfigurationManager.getBundle().getString("certificateFile", certificateFile));
			params.put("certificate.user",
					ConfigurationManager.getBundle().getString("certificateUser", certificateUser));
			params.put("certificate.password",
					ConfigurationManager.getBundle().getString("certificateFile", certificatePassword));
			params.put("certificate.params",
					ConfigurationManager.getBundle().getString("certificateFile", certificateParams));
		}

		String resultStr = (String) getQAFDriver().executeScript("mobile:application:install", new Object[]{params});
		System.out.println(resultStr);
	}

	private static Map<String, String> getAppParams(String app, String by) {
		HashMap params = new HashMap();
		params.put(by, app);
		return params;
	}

	public static void startApp(String app, String by) {
		getQAFDriver().executeScript("mobile:application:open", new Object[]{getAppParams(app, by)});
	}

	public static void closeApp(String app, String by) {
		closeApp(app, by, false);
	}

	public static void closeApp(String app, String by, boolean ignoreExceptions) {
		try {
			getQAFDriver().executeScript("mobile:application:close", new Object[]{getAppParams(app, by)});
		} catch (Exception arg3) {
			if (!ignoreExceptions) {
				throw arg3;
			}
		}

	}

	public static void closeApp(String app, String by, boolean ignoreExceptions, QAFExtendedWebDriver driver) {
		try {
			driver.executeScript("mobile:application:close", new Object[]{getAppParams(app, by)});
		} catch (Exception arg4) {
			if (!ignoreExceptions) {
				throw arg4;
			}
		}

	}

	public static void cleanApp(String app, String by) {
		getQAFDriver().executeScript("mobile:application:clean", new Object[]{getAppParams(app, by)});
	}

	public static void uninstallApp(String app, String by) {
		getQAFDriver().executeScript("mobile:application:uninstall", new Object[]{getAppParams(app, by)});
	}

	public static void uninstallAllApps() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:application:reset", new Object[]{params});
	}

	public static String getAppInfo(String property) {
		HashMap params = new HashMap();
		params.put("property", property);
		return (String) getQAFDriver().executeScript("mobile:application:info", new Object[]{params});
	}

	public static boolean verifyAppInfo(String propertyName, String propertyValue) {
		return Validator.verifyThat(propertyName + " should be " + propertyValue, getAppInfo(propertyName),
				Matchers.equalTo(propertyValue));
	}

	public static void assertAppInfo(String propertyName, String propertyValue) {
		String appOrientation = getAppInfo(propertyName);
		Validator.assertThat(propertyName + " must be " + propertyValue, appOrientation,
				Matchers.equalTo(propertyValue));
	}

	public static void switchToContext(String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(getQAFDriver());
		HashMap params = new HashMap();
		params.put("name", context);
		executeMethod.execute("switchToContext", params);
	}

	public static void waitForPresentTextVisual(String text, int seconds) {
		Validator.verifyThat("Text: \"" + text + "\" should be present after " + seconds + "seconds",
				isText(text, Integer.valueOf(seconds)), Matchers.equalTo("true"));
	}

	public static void waitForPresentImageVisual(String image, int seconds) {
		Validator.verifyThat("Image: \"" + image + "\" should be visible after " + seconds + "seconds",
				isImg(image, Integer.valueOf(seconds)), Matchers.equalTo("true"));
	}

	public static String isImg(String img, Integer timeout) {
		String context = getCurrentContext();
		switchToContext("VISUAL");
		HashMap params = new HashMap();
		params.put("content", img);
		params.put("measurement", "accurate");
		params.put("source", "primary");
		params.put("threshold", "90");
		params.put("timeout", timeout);
		params.put("match", "bounded");
		params.put("imageBounds.needleBound", Integer.valueOf(25));
		Object result = getQAFDriver().executeScript("mobile:checkpoint:image", new Object[]{params});
		switchToContext(context);
		return result.toString();
	}

	public static void assertVisualImg(String img) {
		Validator.assertThat("Image " + img + " must be visible", isImg(img, Integer.valueOf(180)),
				Matchers.equalTo("true"));
	}

	public static boolean verifyVisualImg(String img) {
		return Validator.verifyThat("Image " + img + " should be visible", isImg(img, Integer.valueOf(180)),
				Matchers.equalTo("true"));
	}

	public static String isText(String text, Integer timeout) {
		HashMap params = new HashMap();
		params.put("content", text);
		if (timeout != null) {
			params.put("timeout", timeout);
		}

		params.put("threshold", "100");
		Object result = getQAFDriver().executeScript("mobile:checkpoint:text", new Object[]{params});
		return result.toString();
	}

	public static String isText(String text, Integer timeout, String threshold) {
		HashMap params = new HashMap();
		params.put("content", text);
		if (timeout != null) {
			params.put("timeout", timeout);
		}

		params.put("threshold", threshold);
		Object result = getQAFDriver().executeScript("mobile:checkpoint:text", new Object[]{params});
		return result.toString();
	}

	public static String getCurrentContext() {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(getQAFDriver());
		return (String) executeMethod.execute("getCurrentContextHandle", (Map) null);
	}

	public static void pressKey(String keySequence) {
		HashMap params = new HashMap();
		params.put("keySequence", keySequence);
		getQAFDriver().executeScript("mobile:presskey", new Object[]{params});
	}

	public static void swipe(String start, String end) {
		HashMap params = new HashMap();
		params.put("start", start);
		params.put("end", end);
		getQAFDriver().executeScript("mobile:touch:swipe", new Object[]{params});
	}

	public static void longTouch(String point, int seconds) {
		HashMap params = new HashMap();
		params.put("location", point);
		params.put("operation", "single");
		params.put("duration", Integer.valueOf(seconds));
		(new WebDriverTestBase()).getDriver().executeScript("mobile:touch:tap", new Object[]{params});
	}

	public static void touch(String point) {
		HashMap params = new HashMap();
		params.put("location", point);
		getQAFDriver().executeScript("mobile:touch:tap", new Object[]{params});
	}

	public static void doubleTouch(String point) {
		HashMap params = new HashMap();
		params.put("location", point);
		params.put("operation", "double");
		getQAFDriver().executeScript("mobile:touch:tap", new Object[]{params});
	}

	public static void longTouch(String point) {
		HashMap params = new HashMap();
		params.put("location", point);
		params.put("operation", "double");
		getQAFDriver().executeScript("mobile:touch:tap", new Object[]{params});
	}

	public static void hideKeyboard() {
		HashMap params = new HashMap();
		params.put("mode", "off");
		getQAFDriver().executeScript("mobile:keyboard:display", new Object[]{params});
	}

	public static void rotateDevice(String restValue, String by) {
		HashMap params = new HashMap();
		params.put(by, restValue);
		getQAFDriver().executeScript("mobile:handset:rotate", new Object[]{params});
	}

	public static void setLocation(String location, String by) {
		HashMap params = new HashMap();
		params.put(by, location);
		getQAFDriver().executeScript("mobile:location:set", new Object[]{params});
	}

	public static void assertLocation(String location) {
		String deviceLocation = getDeviceLocation();
		Validator.assertThat("The device location", deviceLocation, Matchers.equalTo(location));
	}

	public static boolean verifyLocation(String location) {
		String deviceLocation = getDeviceLocation();
		return Validator.verifyThat("The device location", deviceLocation, Matchers.equalTo(location));
	}

	public static String getDeviceLocation() {
		HashMap params = new HashMap();
		return (String) getQAFDriver().executeScript("mobile:location:get", new Object[]{params});
	}

	public static void resetLocation() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:location:reset", new Object[]{params});
	}

	public static void goToHomeScreen() {
		HashMap params = new HashMap();
		params.put("target", "All");
		getQAFDriver().executeScript("mobile:handset:ready", new Object[]{params});
	}

	public static void lockDevice(int sec) {
		HashMap params = new HashMap();
		params.put("timeout", Integer.valueOf(sec));
		getQAFDriver().executeScript("mobile:screen:lock", new Object[]{params});
	}

	public static void setTimezone(String timezone) {
		HashMap params = new HashMap();
		params.put("timezone", timezone);
		getQAFDriver().executeScript("mobile:timezone:set", new Object[]{params});
	}

	public static String getTimezone() {
		HashMap params = new HashMap();
		return (String) getQAFDriver().executeScript("mobile:timezone:get", new Object[]{params});
	}

	public static void assertTimezone(String timezone) {
		String deviceTimezone = getTimezone();
		Validator.assertThat("The device timezone", deviceTimezone, Matchers.equalTo(timezone));
	}

	public static boolean verifyTimezone(String timezone) {
		return Validator.verifyThat("The device timezone should be " + timezone, getTimezone(),
				Matchers.equalTo(timezone));
	}

	public static void resetTimezone() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:timezone:reset", new Object[]{params});
	}

	public static void takeScreenshot(String repositoryPath, boolean shouldSave) {
		HashMap params = new HashMap();
		if (shouldSave) {
			params.put("key", repositoryPath);
		}

		getQAFDriver().executeScript("mobile:screen:image", new Object[]{params});
	}

	public static void startImageInjection(String repositoryFile, String app, String by) {
		HashMap params = new HashMap();
		params.put("repositoryFile", repositoryFile);
		params.put(by, app);
		getQAFDriver().executeScript("mobile:image.injection:start", new Object[]{params});
	}

	public static void stopImageInjection() {
		HashMap params = new HashMap();
		(new WebDriverTestBase()).getDriver().executeScript("mobile:image.injection:stop", new Object[]{params});
	}

	public static void setFingerprint(String by, String identifier, String resultAuth, String errorType) {
		HashMap params = new HashMap();
		params.put(by, identifier);
		params.put("resultAuth", resultAuth);
		if (!errorType.isEmpty() && !errorType.equals("")) {
			params.put("errorType", errorType);
		}

		getQAFDriver().executeScript("mobile:fingerprint:set", new Object[]{params});
	}

	public static void setSensorAuthentication(String by, String identifier, String resultAuth, String errorType) {
		HashMap params = new HashMap();
		params.put(by, identifier);
		params.put("resultAuth", resultAuth);
		if (!errorType.isEmpty() && !errorType.equals("")) {
			params.put("errorType", errorType);
		}

		getQAFDriver().executeScript("mobile:sensorAuthentication:set", new Object[]{params});
	}

	public static void generateHAR() {
		HashMap params = new HashMap();
		params.put("generateHarFile", "true");
		getQAFDriver().executeScript("mobile:vnetwork:start", new Object[]{params});
	}

	public static void stopGenerateHAR() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:vnetwork:stop", new Object[]{params});
	}

	public static void audioInject(String file) {
		HashMap params = new HashMap();
		params.put("key", file);
		getQAFDriver().executeScript("mobile:audio:inject", new Object[]{params});
	}

	public static String getDeviceProperty(String property) {
		HashMap params = new HashMap();
		params.put("property", property);
		return (String) getQAFDriver().executeScript("mobile:handset:info", new Object[]{params});
	}

	public static void setPickerWheel(RemoteWebElement picker, String direction, String value) {
		value = value.replaceAll("[^\\x00-\\x7F]", "");

		for (String name = picker.getAttribute("value").replaceAll("[^\\x00-\\x7F]", ""); !name
				.equals(value); name = picker.getAttribute("value").replaceAll("[^\\x00-\\x7F]", "")) {
			System.out.println(name);
			pickerwheelStep(picker, direction);
		}

	}

	public static String pickerwheelGet(RemoteWebElement element) {
		return element.getAttribute("value");
	}

	public static void pickerwheelStep(RemoteWebElement element, String direction) {
		pickerwheelStep(element, direction, 0.15D);
	}

	public static void pickerwheelStep(RemoteWebElement element, String direction, double offset) {
		HashMap params = new HashMap();
		params.put("order", direction);
		params.put("offset", Double.valueOf(offset));
		params.put("element", element.getId());
		getQAFDriver().executeScript("mobile: selectPickerWheelValue", new Object[]{params});
	}

	public static void setPickerWheel(String locator, String direction, String value) {
		setPickerWheel((RemoteWebElement) getQAFDriver().findElement(locator), direction, value);
	}

	public static void touchObject(String loc, String addressBar) {
		int bannerY = getOffset(addressBar);
		int scaleFactor = getScale();
		Rectangle rect = (new QAFExtendedWebElement(loc)).getRect();
		int x = (rect.getX() + rect.getWidth() / 2) * scaleFactor;
		int y = (rect.getY() + rect.getHeight() / 2 + bannerY) * scaleFactor;
		touch(x + "," + y);
	}

	public static void slideObjectLeft(String loc) {
		float y = 0.5F;
		float startX = 0.6666667F;
		float endX = 0.33333334F;
		slideObject(loc, startX, endX, y);
	}

	public static void slideObject(String loc, float xStartMult, float xEndMult, float yMult) {
		slideObject(loc, xStartMult, xEndMult, yMult, yMult);
	}

	public static void slideObject(String loc, float xStartMult, float xEndMult, float yStartMult, float yEndMult) {
		int scaleFactor = getScale();
		Rectangle rect = (new QAFExtendedWebElement(loc)).getRect();
		int startY = Math.round(((float) rect.getY() + (float) rect.getHeight() * yStartMult) * (float) scaleFactor);
		int endY = Math.round(((float) rect.getY() + (float) rect.getHeight() * yEndMult) * (float) scaleFactor);
		int startX = Math.round(((float) rect.getX() + (float) rect.getWidth() * xStartMult) * (float) scaleFactor);
		int endX = Math.round(((float) rect.getX() + (float) rect.getWidth() * xEndMult) * (float) scaleFactor);
		swipe(startX + "," + startY, endX + "," + endY);
	}

	public static int getScale() {
		String deviceRes = getDeviceProperty("resolution");
		int appWidth = (new QAFExtendedWebElement("xpath=/*/*")).getSize().getWidth();
		return Math.round((float) (Integer.parseInt(deviceRes.split("\\*")[0]) / appWidth));
	}

	public static int getOffset(String addressBar) {
		return getOffset(addressBar, "NATIVE_APP");
	}

	public static int getOffset(String addressBar, String context) {
		String curContext = getCurrentContext();
		switchToContext(context);
		Rectangle view = (new QAFExtendedWebElement(addressBar)).getRect();
		switchToContext(curContext);
		return view.getY() + view.getHeight();
	}

	public static void checkAccessibility(String tagName) {
		String browserName = DriverUtils.getDriver().getCapabilities().getCapability("browserName").toString();
		String platformName = DriverUtils.getDriver().getCapabilities().getCapability("platformName").toString();
		if (platformName.equalsIgnoreCase("ios") && browserName.equalsIgnoreCase("safari")) {
			Log params1 = LogFactoryImpl.getLog(DeviceUtils.class);
			params1.error(
					"Accessibility testing is not supported for Safari browser on iPhone/iPad. Skipping Accessibility check.");
		} else if (DriverUtils.getDriver().getCapabilities().getCapability("driverClass") != null) {
			HashMap params = new HashMap();
			params.put("tag", tagName);
			getQAFDriver().executeScript("mobile:checkAccessibility:audit", new Object[]{params});
		} else {
			startAxe();
		}

	}

	public static void cloudCall(String toHandset, String toUser, String toLogical, String toNumber) throws Exception {
		if (toHandset.isEmpty() && toUser.isEmpty() && toLogical.isEmpty() && toNumber.isEmpty()) {
			throw new Exception("Please select at least one destination");
		} else {
			HashMap pars = new HashMap();
			if (!toHandset.isEmpty()) {
				pars.put("to.handset", toHandset);
			}

			if (!toUser.isEmpty()) {
				pars.put("to.user", toUser);
			}

			if (!toLogical.isEmpty()) {
				pars.put("to.logical", toLogical);
			}

			if (!toNumber.isEmpty()) {
				pars.put("to.number", toNumber);
			}

			getQAFDriver().executeScript("mobile:gateway:call", new Object[]{pars});
		}
	}

	public static void cloudEmail(String subject, String body, String toHandset, String toAddress, String toUser,
			String toLogical) throws Exception {
		if (toHandset.isEmpty() && toAddress.isEmpty() && toUser.isEmpty() && toLogical.isEmpty()) {
			throw new Exception("Please select at least one destination");
		} else {
			HashMap pars = new HashMap();
			if (!subject.isEmpty()) {
				pars.put("subject", subject);
			}

			if (!body.isEmpty()) {
				pars.put("body", body);
			}

			if (!toHandset.isEmpty()) {
				pars.put("to.handset", toHandset);
			}

			if (!toAddress.isEmpty()) {
				pars.put("to.address", toAddress);
			}

			if (!toUser.isEmpty()) {
				pars.put("to.user", toUser);
			}

			if (!toLogical.isEmpty()) {
				pars.put("to.logical", toLogical);
			}

			getQAFDriver().executeScript("mobile:gateway:email", new Object[]{pars});
		}
	}

	public static void cloudSMS(String body, String toHandset, String toUser, String toLogical, String toNumber)
			throws Exception {
		if (toHandset.isEmpty() && toUser.isEmpty() && toLogical.isEmpty() && toNumber.isEmpty()) {
			throw new Exception("Please select at least one destination");
		} else {
			HashMap pars = new HashMap();
			if (!body.isEmpty()) {
				pars.put("body", body);
			}

			if (!toHandset.isEmpty()) {
				pars.put("to.handset", toHandset);
			}

			if (!toUser.isEmpty()) {
				pars.put("to.user", toUser);
			}

			if (!toLogical.isEmpty()) {
				pars.put("to.logical", toLogical);
			}

			if (!toNumber.isEmpty()) {
				pars.put("to.number", toNumber);
			}

			getQAFDriver().executeScript("mobile:gateway:sms", new Object[]{pars});
		}
	}

	public static void startAxe() {
		AxeHelper axe = new AxeHelper(getQAFDriver());
		axe.runAxe();
		axe.startHighlighter("violations");
		StringBuilder errors = new StringBuilder();
		int errorCount = 0;

		while (true) {
			Map capabilities = axe.nextHighlight();
			System.out.println("violation: " + capabilities);
			String platform;
			String browserName;
			String browserVersion;
			String browserVersionFormatted;
			String message;
			if (capabilities == null) {
				if (errorCount > 0) {
					Capabilities arg10 = DriverUtils.getDriver().getCapabilities();
					platform = String.valueOf(arg10.getCapability("platformName"));
					String arg11 = String.valueOf(arg10.getCapability("platformVersion"));
					browserName = String.valueOf(arg10.getCapability("browserName"));
					browserVersion = String.valueOf(arg10.getCapability("browserVersion"));
					if ("null".equals(browserName)) {
						browserVersionFormatted = "default browser";
					} else {
						browserVersionFormatted = browserName + "-" + browserVersion;
					}

					message = String.format("%n%s-%s %s : %d violations on %s%nReport Link: %s%n",
							new Object[]{platform, arg11, browserVersionFormatted, Integer.valueOf(errorCount),
									"https://www.google.com/", ReportUtils.getReportClient().getReportUrl()});
					message = String.format("%s%n%s%n", new Object[]{message, errors});
				}

				return;
			}

			++errorCount;
			platform = (String) capabilities.get("issue");
			Map version = (Map) capabilities.get("node");
			browserName = (String) version.get("impact");
			browserVersion = (String) version.get("failureSummary");
			browserVersionFormatted = (String) version.get("html");
			message = (String) capabilities.get("target");
			String message1 = String.format("%s - %s%n %s%n Selector:\t%s%n HTML:\t\t%s%n%n",
					new Object[]{browserName, platform, browserVersion, message, browserVersionFormatted});
			DriverUtils.getDriver().getScreenshotAs(OutputType.BASE64);
			ReportUtils.getReportClient().reportiumAssert(message1, false);
			errors.append(message1);
		}
	}

	public static void startVitals() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:vitals:start", new Object[]{params});
	}

	public static void startVitals(List<String> vitals) {
		HashMap params = new HashMap();
		params.put("vitals", vitals);
		getQAFDriver().executeScript("mobile:vitals:start", new Object[]{params});
	}

	public static void startVitals(List<String> vitals, Integer interval) {
		HashMap params = new HashMap();
		params.put("vitals", vitals);
		params.put("interval", interval);
		getQAFDriver().executeScript("mobile:vitals:start", new Object[]{params});
	}

	public static void stopVitals() {
		HashMap params = new HashMap();
		getQAFDriver().executeScript("mobile:vitals:stop", new Object[]{params});
	}
}