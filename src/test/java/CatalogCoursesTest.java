import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogCoursesPage;

@ExtendWith(UIExtensions.class)
public class CatalogCoursesTest {

  @Inject
  private CatalogCoursesPage catalogCoursesPage;

  @DisplayName("Сценарий 2")
  @Test
  public void coursesPageTest() {
    catalogCoursesPage.open();
    catalogCoursesPage.checkBoxChecked("Все направления");
    catalogCoursesPage.checkBoxChecked("Любой уровень");
    catalogCoursesPage.leftSliderMove(3);
    catalogCoursesPage.rightSliderMove(10);
    catalogCoursesPage.selectCheckBox("Архитектура");
    catalogCoursesPage.checkCourseListNotEquals();
    catalogCoursesPage.resetFilters();
    catalogCoursesPage.checkCoursesistIfResetFilter();
  }
}