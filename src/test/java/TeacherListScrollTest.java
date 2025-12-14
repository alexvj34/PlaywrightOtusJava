import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ClickhousePage;

@ExtendWith(UIExtensions.class)
public class TeacherListScrollTest {

  @Inject
  private ClickhousePage clickhousePage;

  String teacherName = "Алексей Железной";

  @DisplayName("Сценарий 1")
  @Test
  public void scrollTeachersListTest() {
    clickhousePage.open();
    clickhousePage.scrollTeacherList(400);
    clickhousePage.checkTeachersListSlided();
    clickhousePage.scrollTeacherList(-400);
    clickhousePage.clickByNameOnSlideList(teacherName);
    clickhousePage.checkTeacherNameIsPresent(teacherName);
    clickhousePage.clickOnButtonScrollRight();
    clickhousePage.checkAnotherTeacherCardOpen(teacherName);
    clickhousePage.clickOnButtonScrollLeft();
    clickhousePage.checkTeacherNameIsPresent(teacherName);
  }
}