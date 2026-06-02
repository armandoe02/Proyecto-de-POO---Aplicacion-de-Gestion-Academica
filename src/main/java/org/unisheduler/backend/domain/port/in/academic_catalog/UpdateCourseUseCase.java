package org.unisheduler.backend.domain.port.in.academic_catalog;

import org.unisheduler.backend.application.service.academic_catalog.in.UpdateCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.UpdateCourseResponse;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;

public interface UpdateCourseUseCase {
    UpdateCourseResponse execute(UpdateCourseCommand command);
}
