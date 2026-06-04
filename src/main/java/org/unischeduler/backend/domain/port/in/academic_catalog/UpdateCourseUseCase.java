package org.unischeduler.backend.domain.port.in.academic_catalog;

import org.unischeduler.backend.application.service.academic_catalog.in.UpdateCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.dtos.UpdateCourseResponse;

public interface UpdateCourseUseCase {
    UpdateCourseResponse execute(UpdateCourseCommand command);
}
