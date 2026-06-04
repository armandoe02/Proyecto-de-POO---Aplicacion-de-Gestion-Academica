package org.unisheduler.backend.domain.port.in.academic_catalog.course;

import org.unisheduler.backend.application.service.academic_catalog.in.course.DeleteCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.course.dtos.DeleteCourseResponse;

public interface DeleteCourseUseCase {
    DeleteCourseResponse execute(DeleteCourseCommand command);
}
