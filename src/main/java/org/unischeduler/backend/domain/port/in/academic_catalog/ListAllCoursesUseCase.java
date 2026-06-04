package org.unischeduler.backend.domain.port.in.academic_catalog;

import org.unischeduler.backend.application.service.academic_catalog.out.dtos.ListAllCoursesResponse;

public interface ListAllCoursesUseCase {
    ListAllCoursesResponse execute();
}
