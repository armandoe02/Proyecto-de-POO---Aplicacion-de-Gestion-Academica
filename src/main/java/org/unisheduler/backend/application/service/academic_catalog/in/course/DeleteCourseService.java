package org.unisheduler.backend.application.service.academic_catalog.in.course;

import org.unisheduler.backend.application.service.academic_catalog.in.course.dtos.DeleteCourseResponse;
import org.unisheduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

public class DeleteCourseService implements DeleteCourseUseCase {
    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public DeleteCourseService(CourseRepository courseRepository, PrerequisiteRepository prerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public DeleteCourseResponse execute(DeleteCourseCommand command) {
        if(!courseRepository.deleteById(command.getId())) {
            return new DeleteCourseResponse(
                    false,
                    "No existe una asignatura con id " + command.getId()
            );
        }

        prerequisiteRepository.deleteWhereCourseId(command.getId());

        return new DeleteCourseResponse(
                true,
                "Se elimino la asignatura con exito"
        );
    }
}
