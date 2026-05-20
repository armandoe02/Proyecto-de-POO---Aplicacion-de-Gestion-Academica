package seeders;

import module.academic_catalog.model.AcademicProgram;
import module.academic_catalog.model.Course;
import repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class CourseSeeder {

    public static void seed(
            CourseRepository courseRepository,
            AcademicProgram systemsEngineering
    ) {

        //================ SEMESTRE 1 ================//

        Course calculoI = new Course(
                1L,
                "Calculo I",
                "MAT101",
                4,
                systemsEngineering,
                new ArrayList<>()
        );

        Course comprensionI = new Course(
                2L,
                "Comprension y Produccion Textual I",
                "HUM101",
                2,
                systemsEngineering,
                new ArrayList<>()
        );

        Course introduccionIS = new Course(
                3L,
                "Introduccion a la Ingenieria de Sistemas",
                "SYS101",
                2,
                systemsEngineering,
                new ArrayList<>()
        );

        Course algoritmos = new Course(
                4L,
                "Algoritmos y Programacion",
                "SYS102",
                4,
                systemsEngineering,
                new ArrayList<>()
        );

        //================ GUARDAR SEMESTRE 1 ================//

        courseRepository.save(calculoI);
        courseRepository.save(comprensionI);
        courseRepository.save(introduccionIS);
        courseRepository.save(algoritmos);

        //================ SEMESTRE 2 ================//

        Course calculoII = new Course(
                5L,
                "Calculo II",
                "MAT201",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(calculoI))
        );

        Course comprensionII = new Course(
                6L,
                "Comprension y Produccion Textual II",
                "HUM201",
                2,
                systemsEngineering,
                new ArrayList<>(List.of(comprensionI))
        );

        Course programacionBasica = new Course(
                7L,
                "Programacion Basica",
                "SYS201",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(algoritmos))
        );

        Course fisicaI = new Course(
                8L,
                "Fisica I",
                "PHY201",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(calculoI))
        );

        //================ GUARDAR SEMESTRE 2 ================//

        courseRepository.save(calculoII);
        courseRepository.save(comprensionII);
        courseRepository.save(programacionBasica);
        courseRepository.save(fisicaI);

        //================ SEMESTRE 3 ================//

        Course calculoIII = new Course(
                9L,
                "Calculo III",
                "MAT301",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(calculoII))
        );

        Course poo = new Course(
                10L,
                "Programacion Orientada a Objetos",
                "SYS301",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(programacionBasica))
        );

        Course fisicaII = new Course(
                11L,
                "Fisica II",
                "PHY301",
                4,
                systemsEngineering,
                new ArrayList<>(List.of(fisicaI))
        );

        Course matematicasDiscretas = new Course(
                12L,
                "Matematicas Discretas",
                "MAT302",
                3,
                systemsEngineering,
                new ArrayList<>()
        );

        //================ GUARDAR SEMESTRE 3 ================//

        courseRepository.save(calculoIII);
        courseRepository.save(poo);
        courseRepository.save(fisicaII);
        courseRepository.save(matematicasDiscretas);

        System.out.println("Courses seeded successfully.");
    }
}
