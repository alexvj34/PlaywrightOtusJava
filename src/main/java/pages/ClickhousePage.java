package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/lessons/clickhouse/")
public class ClickhousePage extends AbsBasePage {
  Locator firstSlide = page.locator("//section//div[contains(@class, 'swiper-slide')]").first();
  Locator slide = page.locator("//section//div[contains(@class, 'swiper-slide')]");

  public ClickhousePage(Page page) {
    super(page);
  }

  @Step("Эмитация drag and drop (перетаскивания мышки)")
  public void scrollTeacherList(double v) {
    BoundingBox firstSlideBox = firstSlide.boundingBox();
    page.mouse().move(firstSlideBox.x, firstSlideBox.width / 2);
    page.mouse().down();  //удерживание лкм на мышке
    page.mouse().move(firstSlideBox.x - v, firstSlideBox.width / 2, new Mouse.MoveOptions().setSteps(3));
    page.mouse().up(); //отпускаю лкм на мышке
  }

  @Step("Проверяю, что список преподавателей прокрутился и первый слайд с другим преподавателем")
  public void checkTeachersListSlided() {
    assertThat(firstSlide)
        .not()
        .isInViewport();
  }

  @Step("Кликаю на плитку преподавателя")
  public void clickByNameOnSlideList(String name) {
    BoundingBox firstSlideBox = firstSlide.boundingBox();
    page.mouse().wheel(firstSlideBox.x, firstSlideBox.y);
    slide.getByText(name).click();
  }

  @Step("Проверяю, что открыт popup того преподавателя, на котором был произведен клик")
  public void checkTeacherNameIsPresent(String teacherName) {
    assertThat(page.locator("//h3")
        .getByText(teacherName))
        .isVisible();
  }

  @Step("Нажимаю на кнопку >")
  public void clickOnButtonScrollRight() {
    page.locator("//div[contains(@class, 'full-phone')]//button[contains(@class, 'iPzpLW')]").click();
  }

  @Step("Нажимаю на кнопку <")
  public void clickOnButtonScrollLeft() {
    page.locator("//div[contains(@class, 'full-phone')]//button[contains(@class, 'biZjNh')]").click();
  }

  @Step("Проверяю, что открыт popup другого преподавателя")
  public void checkAnotherTeacherCardOpen(String name) {
    Locator header = page.getByRole(AriaRole.HEADING)
        .filter(new Locator.FilterOptions().setHasText(name));

    assertThat(header).not().isInViewport();
  }
}