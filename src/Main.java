import module.academic_catalog.model.AcademicProgram;
import repository.AcademicHistoryRepository;
import repository.CourseRepository;
import repository.GroupRepository;
import repository.StudentRepository;
import seeders.AcademicHistorySeeder;
import seeders.CourseSeeder;
import seeders.GroupSeeder;
import seeders.StudentSeeder;

public class Main {

  public static void main(String[] args) {

    //================ REPOSITORIES ================//
    CourseRepository courseRepository =
            new CourseRepository();

    StudentRepository studentRepository =
            new StudentRepository();

    GroupRepository groupRepository =
            new GroupRepository();

    AcademicHistoryRepository historyRepository =
            new AcademicHistoryRepository();

    //================ ACADEMIC PROGRAM ================//

    AcademicProgram systemsEngineering =
            new AcademicProgram(
                    1L,
                    "Ingenieria de Sistemas"
            );

    //================ SEEDERS ================//

    CourseSeeder.seed(
            courseRepository,
            systemsEngineering
    );

    StudentSeeder.seed(
            studentRepository,
            systemsEngineering
    );

    GroupSeeder.seed(
            groupRepository,
            courseRepository
    );

    AcademicHistorySeeder.seed(
            historyRepository,
            studentRepository,
            courseRepository
    );

    //================ TESTING ================//

    System.out.println(
            courseRepository.findByCode("MAT101")
    );

    System.out.println(
            studentRepository.findByCode("202311001")
    );

    System.out.println(
            groupRepository.findAll()
    );

    System.out.println(
            historyRepository.findAll()
    );

    System.out.println("\nSystem initialized successfully.");
  }
}
