package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/uslugi-kompaniyam")
public class CustomCoursesPage extends AbsBasePage {
  private Page customCoursePage;

  public CustomCoursesPage(Page page) {
    super(page);
  }

  @Step("Проверка что открылась страница 'Разработка индивидуальных программ обучения для бизнеса'")
  public void clickMoreDetailsAndValidate(String buttonName, String expectedHeader) {
    customCoursePage = page.waitForPopup(() -> {
      page.getByRole(AriaRole.BUTTON,
          new Page.GetByRoleOptions().setName(buttonName)).click();
    });
    customCoursePage.waitForLoadState(LoadState.LOAD);
    assertThat(customCoursePage).hasTitle(expectedHeader);
    Locator categoryLinks = customCoursePage.locator(
        "a.tn-atom[href*='categories']"
    );
    assertThat(categoryLinks).hasCount(6);
  }

  @Step("Кликаю по категории")
  public void clickCategory(String categoryName) {
    customCoursePage.locator(String.format("//a[contains(@href, 'categories/%s')]", categoryName)).click();
  }

  @Step("Проверка, что чекбокс выбран")
  public void checkBoxIsChecked(String name) {
    assertThat(customCoursePage.getByRole(AriaRole.CHECKBOX,
        new Page.GetByRoleOptions().setName(name)))
        .isChecked();
  }

  public void scrollPageToBottom() {
    // Скроллим страницу до самого низа
    page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)");
  }
}