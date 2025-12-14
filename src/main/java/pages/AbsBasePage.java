package pages;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import exceptions.PathNotValidException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class AbsBasePage {

  protected Page page;

  public AbsBasePage(Page page) {
    this.page = page;
  }

  private String baseUrl = System.getProperty("base.url", "https://otus.ru");

  private String getPath() {
    Class<?> clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    return null;
  }

  public void open() {
    String path = getPath();
    if (path == null || path.isEmpty()) {
      throw new PathNotValidException("Path not set for class: " + this.getClass().getName());
    }
    String url;
    if (baseUrl.endsWith("/") && path.startsWith("/")) {
      url = baseUrl.substring(0, baseUrl.length() - 1) + path;
    } else {
      url = baseUrl + path;
    }
    page.navigate(url);
  }

  public void checkBoxChecked(String checkBoxName) {
    assertThat(page.getByRole(AriaRole.CHECKBOX,
        new Page.GetByRoleOptions().setName(checkBoxName)))
        .isChecked();
  }

  public void selectCheckBox(String name) {
    page.getByRole(AriaRole.CHECKBOX,
            new Page.GetByRoleOptions().setName(name))
        .check();
  }
}

