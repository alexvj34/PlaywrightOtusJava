package extensions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import popups.TeacherPopup;

public class GuicePopupModule extends AbstractModule {

  private final Page page;

  public GuicePopupModule(Page page) {
    this.page = page;
  }

  @Provides
  @Singleton
  public TeacherPopup getTeacherPopup() {
    return new TeacherPopup(page);
  }

}
