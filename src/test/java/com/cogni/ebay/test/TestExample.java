package com.cogni.ebay.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.ebay.app.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class TestExample {
	@Test
	public void appLaunch() throws IOException {
		InputStream is = this.getClass().getResourceAsStream("/com/cogni/resources/testData.properties");
		Properties pr = new Properties();
		pr.load(is);
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("appPackage", "com.ebay.mobile");
		dc.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		dc.setCapability("platformName", pr.getProperty("android"));
		dc.setCapability("automationName", "uiautomator2");
		dc.setCapability("deviceName", pr.getProperty("deviceName"));
		DesiredCapabilities dc1 = new DesiredCapabilities();

		// Desired Capabilities for ios device
		dc1.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		dc1.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.1.3");
		dc1.setCapability(MobileCapabilityType.DEVICE_NAME, "sixt iPhone");
		dc1.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
		dc1.setCapability(MobileCapabilityType.NO_RESET, true);
		dc1.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		dc1.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		dc1.setCapability(MobileCapabilityType.UDID, "00008020-000129191A43002E");
		dc1.setCapability("bundleId", "com.sixt.one-alpha");

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		File f = new File("/usr/local/lib/node_modules/appium/build/lib/main.js");
		builder.withAppiumJS(f);
		builder.withIPAddress("127.0.0.1");
		builder.usingAnyFreePort();
		AppiumDriverLocalService app = AppiumDriverLocalService.buildService(builder);
		app.start();
		AndroidDriver<MobileElement> driver;
		while (true) {
			try {
				driver = new AndroidDriver(app, dc);
				break;
			} catch (Exception ex) {
			}
		}
		driver.launchApp();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.fillSearchField(pr.getProperty("searchItem"));
		loginPage.selectAnyItemFromDropDownList(pr.getProperty("requiredItemText"));
		loginPage.clickOnAlert();
		loginPage.searchingForItem(pr.getProperty("selectYourFavourateTVItem"));
	}
}