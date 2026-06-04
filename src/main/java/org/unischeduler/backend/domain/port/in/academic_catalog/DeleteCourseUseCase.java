package org.unischeduler.backend.domain.port.in.academic_catalog;

import org.unischeduler.backend.application.service.academic_catalog.in.DeleteCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.dtos.DeleteCourseResponse;

public interface DeleteCourseUseCase {
    DeleteCourseResponse execute(DeleteCourseCommand command);
}
