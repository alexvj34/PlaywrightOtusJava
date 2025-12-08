package factories;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import exceptions.BrowserNotFoundException;

public class BrowserFactory {

  private final String browserName = System.getProperty("browser", "chromium");
  private final boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));


  private final Playwright playwright;


  public BrowserFactory(Playwright playwright) {
    this.playwright = playwright;
  }

  public Browser create() {
    switch (browserName.toLowerCase()) {
      case "chrome":
      case "chromium":
        return this.playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
      case "firefox":
        return this.playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
      case "webkit":
        return this.playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
      default:
        throw new BrowserNotFoundException(browserName);
    }
  }
}