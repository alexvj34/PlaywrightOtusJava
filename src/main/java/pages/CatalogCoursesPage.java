package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import io.qameta.allure.Step;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Path("/catalog/courses")
public class CatalogCoursesPage extends AbsBasePage {

  public CatalogCoursesPage(Page page) {
    super(page);
  }

  Locator headerCourseName = page.locator("//h6");

  Locator leftSlider = page.locator("div[role='slider'][aria-valuenow='0']");

  Locator rightSlider = page.locator("div[role='slider'][aria-valuenow='15']");

  @Step("Объщий класс для слайдеров")
  public void leftSliderMove(Locator locator, double targetValue, double step) {
    targetValue++;
    BoundingBox elementToMove = locator.boundingBox();
    locator.click();
    locator.hover();
    page.mouse().down();
    page.mouse().move(elementToMove.x + step * targetValue, elementToMove.y + elementToMove.height / 2);
    page.mouse().up();

  }

  @Step("Двигаю слайдер влево")
  public void leftSliderMove(double targetValue) {
    leftSliderMove(leftSlider, targetValue, 14);
  }

  @Step("Двигаю слайдер вправо")
  public void rightSliderMove(double targetValue) {
    double value = targetValue - 15;
    leftSliderMove(rightSlider, value, 14);
  }

  @Step("Проверяем, что в каталоге курсов отображаются курсы с продолжительностью в выбранной продолжительности")
  public void checkShowsCoursesWithDurationSelectedDuration() {
    page.waitForTimeout(5000);
    Locator locator = page.locator("//div[contains(@class, 'sc-hrqzy3-0 cYNMRM sc-157icee-1 ieVVRJ')][.//div]");

    List<String> texts = locator.allTextContents();
    for (String itemText : texts) {
      validateItemText(itemText);
    }
  }

  @Step("Сплитом обрезаю и беру только дату и месяц")
  private void validateItemText(String itemText) {
    String[] textParts = itemText.split(" · ");
    if (textParts.length > 1) {
      String durationText = textParts[1].trim();
      int durationInMonths = parseDuration(durationText);
      assertTrue(durationInMonths >= 3 && durationInMonths <= 10);
    }
  }

  @Step("Сплитом обрезаю и беру только дату")
  private int parseDuration(String durationText) {
    String[] words = durationText.split(" ");
    return Integer.parseInt(words[0]);
  }

  @Step("Проверка, что список курсов изменился")
  public void checkCourseListNotEquals() {
    Locator locator = page.locator("//h6");
    List<String> allCoursesAfterFiltering = locator.allTextContents();
    assertNotEquals(headerCourseName, allCoursesAfterFiltering);
  }

  @Step("Сброс фильтров")
  public void resetFilters() {
    page.getByRole(AriaRole.BUTTON,
            new Page.GetByRoleOptions().setName("Очистить фильтры"))
        .click();
  }

  @Step("Проверка, что при сбросе фильтров отображаются дефолтные курсы")
  public void checkCoursesistIfResetFilter() {
    page.waitForTimeout(5000);
    Locator locator = page.locator("//h6");
    List<String> allCoursesListIfFilterReset = locator.allTextContents();
    assertNotEquals(headerCourseName, allCoursesListIfFilterReset);
  }
}
