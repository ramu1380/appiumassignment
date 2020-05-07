package com.ebay.app.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class LoginPage {
	String packageId = "com.ebay.mobile";
	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	public LoginPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 60);
	}

	public By signIn = By.id(packageId + ":id/button_sign_in");
	public By searchField = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Search for anything\")");
	public By text = By.id(packageId + ":id/text");
	public By item = By.id(packageId + ":id/textview_item_title");
	public By alert = By.id(packageId + ":id/text_slot_1");

	public void clickOnSignIn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(signIn));
		driver.findElement(signIn).click();
	}

	public void fillSearchField(String searchingText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
		driver.findElement(searchField).click();
		driver.findElement(searchField).sendKeys(new CharSequence[] { searchingText });
		if (driver.isKeyboardShown()) {
			driver.hideKeyboard();
		}
	}

	public List<MobileElement> dropDownItems() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(text));
		return driver.findElements(text);
	}

	public void selectAnyItemFromDropDownList(String requiredItemText) {
		List<MobileElement> list = dropDownItems();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equalsIgnoreCase(requiredItemText)) {
				list.get(i).click();
				break;
			}
		}
	}

	public List<MobileElement> items() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(item));
		return driver.findElements(item);
	}

	public int clickOnRequredItem(String selectYourFavourateTVItem) {
		List<MobileElement> list = items();
		int flag = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equalsIgnoreCase(selectYourFavourateTVItem)) {
				list.get(i).click();
				flag = 1;
				break;
			}

		}
		return flag;
	}

	public void searchingForItem(String selectYourFavourateTVItem) {
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		int startx = width / 2;
		int starty = (int) (0.8 * height);
		while (clickOnRequredItem(selectYourFavourateTVItem) == 0) {
			TouchAction ta = new TouchAction(driver);
			ta.press(ElementOption.point(startx, starty))
					.waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
					.moveTo(ElementOption.point(startx, starty - 200)).release().perform();
		}
	}

	public void clickOnAlert() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(alert));
		driver.findElement(alert).click();
	}
}
