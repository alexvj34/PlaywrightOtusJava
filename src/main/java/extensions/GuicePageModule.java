package extensions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.*;
import pages.*;

public class GuicePageModule extends AbstractModule {

  private Page page;

  private BrowserContext context;

  public GuicePageModule() {
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    BrowserContext context = browser.newContext();

    context.tracing().start(new Tracing.StartOptions()
        .setSnapshots(true)
        .setScreenshots(true)
        .setSources(true));

    this.context = context;
    this.page = context.newPage();
  }

  @Singleton
  @Provides
  public Playwright providePlaywright() {
    return Playwright.create();
  }

  @Singleton
  @Provides
  public BrowserContext getContext() {
    return context;
  }

  @Singleton
  @Provides
  public Browser provideBrowser() {
    return context.browser();
  }

  @Singleton
  @Provides
  public ClickhousePage provideClickhousePage() {
    return new ClickhousePage(page);
  }

  @Singleton
  @Provides
  public CatalogCoursesPage provideCatalogCoursesPage() {
    return new CatalogCoursesPage(page);
  }

  @Singleton
  @Provides
  public MainPage provideMianPage() {
    return new MainPage(page);
  }

  @Singleton
  @Provides
  public TeacherPage provideTeacherPage() {
    return new TeacherPage(page);
  }

  @Singleton
  @Provides
  public CustomCoursesPage provideCustomCoursesPage() {
    return new CustomCoursesPage(page);
  }

  @Singleton
  @Provides
  public SubscriptionPage provideSubscriptionPage() {
    return new SubscriptionPage(page);
  }
}