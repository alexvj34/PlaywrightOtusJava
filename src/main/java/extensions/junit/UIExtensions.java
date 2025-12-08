package extensions.junit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.*;
import extensions.GuicePageModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Paths;
import java.util.List;


public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

  private Playwright playwright;
  private Browser browser;
  private BrowserContext browserContext;

  private Injector injector;

  @Override
  public void beforeEach(ExtensionContext context) {
    this.playwright = Playwright.create();
    this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized")));

    this.injector = Guice.createInjector(new GuicePageModule());
    injector.injectMembers(context.getTestInstance().get());

    this.browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    this.browserContext.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSources(true).setScreenshots(true));
  }

  @Override
  public void afterEach(ExtensionContext context) {
    injector.getProvider(BrowserContext.class).get().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
    this.browserContext.close();
  }
}