package org.unisheduler.backend.infrastructure.out.repository.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;
import org.unisheduler.backend.infrastructure.out.mapper.academic_catalog.CourseMapper;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;

import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {
    private final ExcelCourseRepository courseRepository;

    public CourseRepositoryImpl(ExcelCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> findById(String id) {
        Optional<CourseEntity> entityOptional = courseRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        CourseEntity entity = entityOptional.get();
        return Optional.of(CourseMapper.toDomain(entity));
    }
}
