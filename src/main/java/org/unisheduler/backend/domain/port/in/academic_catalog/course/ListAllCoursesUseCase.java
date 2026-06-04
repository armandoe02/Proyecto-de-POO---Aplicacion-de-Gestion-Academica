package org.unisheduler.backend.domain.port.in.academic_catalog.course;

import org.unisheduler.backend.application.service.academic_catalog.out.course.dtos.ListAllCoursesResponse;

public interface ListAllCoursesUseCase {
    ListAllCoursesResponse execute();
}
