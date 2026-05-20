package seeders;

import module.academic_catalog.model.Course;
import module.academic_history.model.AcademicHistory;
import module.academic_history.model.CompletedCourse;
import module.enrollment.model.Student;
import repository.AcademicHistoryRepository;
import repository.CourseRepository;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class AcademicHistorySeeder {

    public static void seed(
            AcademicHistoryRepository historyRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {

        //================ COURSES ================//

        Course calculoI =
                courseRepository.findByCode("MAT101");

        Course comprensionI =
                courseRepository.findByCode("HUM101");

        Course algoritmos =
                courseRepository.findByCode("SYS102");

        Course calculoII =
                courseRepository.findByCode("MAT201");

        Course programacionBasica =
                courseRepository.findByCode("SYS201");

        Course fisicaI =
                courseRepository.findByCode("PHY201");

        //================ STUDENTS ================//

        Student student1 =
                studentRepository.findByCode("202311001");

        Student student2 =
                studentRepository.findByCode("202311002");

        Student student3 =
                studentRepository.findByCode("202311003");

        Student student4 =
                studentRepository.findByCode("202311004");

        //================================================//
        // STUDENT 1 -> Excelente rendimiento
        //================================================//

        ArrayList<CompletedCourse> completedCourses1 =
                new ArrayList<>();

        completedCourses1.add(new CompletedCourse(
                calculoI,
                4.8,
                true
        ));

        completedCourses1.add(new CompletedCourse(
                comprensionI,
                4.5,
                true
        ));

        completedCourses1.add(new CompletedCourse(
                algoritmos,
                4.9,
                true
        ));

        AcademicHistory history1 = new AcademicHistory(
                1L,
                student1,
                completedCourses1
        );

        //================================================//
        // STUDENT 2 -> Rendimiento promedio
        //================================================//

        ArrayList<CompletedCourse> completedCourses2 =
                new ArrayList<>();

        completedCourses2.add(new CompletedCourse(
                calculoI,
                3.4,
                true
        ));

        completedCourses2.add(new CompletedCourse(
                comprensionI,
                3.8,
                true
        ));

        completedCourses2.add(new CompletedCourse(
                algoritmos,
                3.1,
                true
        ));

        AcademicHistory history2 = new AcademicHistory(
                2L,
                student2,
                completedCourses2
        );

        //================================================//
        // STUDENT 3 -> Tiene materias perdidas
        //================================================//

        ArrayList<CompletedCourse> completedCourses3 =
                new ArrayList<>();

        completedCourses3.add(new CompletedCourse(
                calculoI,
                2.3,
                false
        ));

        completedCourses3.add(new CompletedCourse(
                comprensionI,
                3.6,
                true
        ));

        completedCourses3.add(new CompletedCourse(
                algoritmos,
                2.8,
                false
        ));

        AcademicHistory history3 = new AcademicHistory(
                3L,
                student3,
                completedCourses3
        );

        //================================================//
        // STUDENT 4 -> Ya cursó segundo semestre
        //================================================//

        ArrayList<CompletedCourse> completedCourses4 =
                new ArrayList<>();

        completedCourses4.add(new CompletedCourse(
                calculoI,
                4.0,
                true
        ));

        completedCourses4.add(new CompletedCourse(
                calculoII,
                3.7,
                true
        ));

        completedCourses4.add(new CompletedCourse(
                programacionBasica,
                4.3,
                true
        ));

        completedCourses4.add(new CompletedCourse(
                fisicaI,
                3.5,
                true
        ));

        AcademicHistory history4 = new AcademicHistory(
                4L,
                student4,
                completedCourses4
        );

        //================ SAVE HISTORIES ================//

        historyRepository.save(history1);
        historyRepository.save(history2);
        historyRepository.save(history3);
        historyRepository.save(history4);

        System.out.println("Academic histories seeded successfully.");
    }
}