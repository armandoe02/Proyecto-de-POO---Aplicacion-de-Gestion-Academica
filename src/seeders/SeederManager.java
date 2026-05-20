package seeders;

import module.academic_catalog.model.AcademicProgram;

import repository.AcademicHistoryRepository;
import repository.CourseRepository;
import repository.GroupRepository;
import repository.StudentRepository;

import java.util.List;

public class SeederManager {

    public static void seedAll(
            CourseRepository courseRepository,
            StudentRepository studentRepository,
            GroupRepository groupRepository,
            AcademicHistoryRepository historyRepository
    ) {

        AcademicProgram systemsEngineering =
                new AcademicProgram(
                        1L,
                        "Ingenieria de Sistemas"
                );

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

        System.out.println("Datos iniciales cargados.");
    }
}