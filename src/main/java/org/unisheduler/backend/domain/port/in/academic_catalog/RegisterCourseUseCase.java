package org.unisheduler.backend.domain.port.in.academic_catalog;

import org.unisheduler.backend.application.service.academic_catalog.in.RegisterCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.RegisterCourseResponse;

public interface RegisterCourseUseCase {
    RegisterCourseResponse execute(RegisterCourseCommand command);
}
