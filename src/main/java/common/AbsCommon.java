package common;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class AbsCommon {

  protected Page page;

  public AbsCommon(Page page) {
    this.page = page;
  }

  public void implicitWait() {
    page.waitForTimeout(5000);

  }

  public String getText(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.textContent();
  }

  public void click(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    locator.click();
  }

  public boolean isChecked(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.isChecked();
  }

  public void moveSlider(Locator locator, int moveByX) {
    waitForVisibility(locator);
    locator.scrollIntoViewIfNeeded();
    BoundingBox box = locator.boundingBox();
    page.mouse().move(box.x + box.width / 2, box.y + box.height / 2);
    page.mouse().down();
    page.mouse().move(box.x + box.width / 2 + moveByX, box.y + box.height / 2, new Mouse.MoveOptions().setSteps(5));
    page.mouse().up();
  }

  public boolean isVisible(Locator locator) {
    waitForVisibility(locator);
    locator.scrollIntoViewIfNeeded();
    return locator.isVisible();
  }

  public void waitForVisibility(Locator locator) {
    locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
  }
}
