package popups;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import common.AbsCommon;

public class TeacherPopup extends AbsCommon {

  Locator leftIcon = page.locator("//*[@id=\"__PORTAL__\"]//div[2]/button[1]");
  Locator rightIcon = page.locator("//*[@id=\"__PORTAL__\"]//div[2]/button[2]");
  Locator teachersNames = page.locator("//*[@id='__PORTAL__']//div[1]//h3");

  public TeacherPopup(Page page) {
    super(page);
  }

  public TeacherPopup clickLeftIcon() {
    click(leftIcon);
    return this;
  }

  public TeacherPopup clickRightIcon() {
    click(rightIcon);
    return this;
  }

  public String getTeacherName(int index) {
    waitForVisibility(teachersNames.nth(index));
    return teachersNames.nth(index).textContent();
  }
}