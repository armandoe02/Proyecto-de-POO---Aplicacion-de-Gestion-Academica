package org.unischeduler.backend.domain.port.in.academic_catalog;

import org.unischeduler.backend.application.service.academic_catalog.in.RegisterCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.dtos.RegisterCourseResponse;

public interface RegisterCourseUseCase {
    RegisterCourseResponse execute(RegisterCourseCommand command);
}
