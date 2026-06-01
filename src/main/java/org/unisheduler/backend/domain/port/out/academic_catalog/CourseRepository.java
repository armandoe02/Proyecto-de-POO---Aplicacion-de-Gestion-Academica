package org.unisheduler.backend.domain.port.out.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;

import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findById(String id);
}
