package org.unisheduler.backend.infrastructure.out.repository.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.PrerequisiteEntity;
import org.unisheduler.backend.infrastructure.out.mapper.academic_catalog.PrerequisiteMapper;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelPrerequisiteRepository;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrerequisiteRepositoryImpl implements PrerequisiteRepository {
    private final ExcelPrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public PrerequisiteRepositoryImpl(ExcelPrerequisiteRepository prerequisiteRepository, CourseRepository courseRepository) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Prerequisite> findAllPrerequisitesWhereCourseId(String courseId) {
        List<PrerequisiteEntity> entities = prerequisiteRepository.findAllPrerequisitesWhereCourseId(courseId);

        List<Prerequisite> prerequisites = new ArrayList<>();
        for(PrerequisiteEntity entity : entities) {
            Optional<Course> courseRequiredOptional = courseRepository.findById(entity.getPrerequisiteCourseId());
            if(courseRequiredOptional.isEmpty()) {
                continue;
            }

            Course courseRequired = courseRequiredOptional.get();
            Prerequisite prerequisite = PrerequisiteMapper.toDomain(entity, courseRequired);
            prerequisites.add(prerequisite);
        }

        return prerequisites;
    }

    @Override
    public Prerequisite save(String courseId, String coursePrerequisiteId) {
        PrerequisiteEntity entity = prerequisiteRepository.save(courseId, coursePrerequisiteId);
        Optional<Course> course = courseRepository.findById(entity.getCourseId());

        return PrerequisiteMapper.toDomain(entity, course.get());
    }

    @Override
    public boolean deleteWhereCourseId(String id) {
        return prerequisiteRepository.deleteWhereCourseId(id);
    }
}
