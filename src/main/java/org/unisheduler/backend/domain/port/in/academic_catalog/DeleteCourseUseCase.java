package org.unisheduler.backend.domain.port.in.academic_catalog;

import org.unisheduler.backend.application.service.academic_catalog.in.DeleteCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.DeleteCourseResponse;

public interface DeleteCourseUseCase {
    DeleteCourseResponse execute(DeleteCourseCommand command);
}
