package org.unisheduler.backend.domain.port.in.academic_catalog.course;

import org.unisheduler.backend.application.service.academic_catalog.in.course.RegisterCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.course.dtos.RegisterCourseResponse;

public interface RegisterCourseUseCase {
    RegisterCourseResponse execute(RegisterCourseCommand command);
}
