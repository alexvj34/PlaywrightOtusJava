package pages;

import annotations.Path;
import com.microsoft.playwright.Page;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/")
public class TeacherPage extends AbsBasePage {

  public TeacherPage(Page page) {
    super(page);
  }

  public void checkHeaderPage(String expectedHeader) {
    assertThat(page.getByText(Pattern.compile(String.format("^%s$", expectedHeader)))).isVisible();
  }

  public void checkTeacherNamePresent(String name) {
    assertThat(page.getByText(Pattern.compile(String.format("^" + name + "$")))).isVisible();
  }
}