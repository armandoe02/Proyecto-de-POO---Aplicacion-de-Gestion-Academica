package org.unisheduler.backend.infrastructure.out.mapper.academic_programming;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.model.academic_programming.entity.Group;
import org.unisheduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unisheduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.GroupEntity;

import java.util.List;

public class GroupMapper {

    public static GroupEntity toEntity(Group group) {
        GroupEntity entity = new GroupEntity();

        entity.setGroupId(
                group.getGroupId()
        );

        entity.setCourseId(
                group.getCourse().getCourseId()
        );

        entity.setGroupCode(
                group.getGroupCode()
        );

        entity.setTeacherId(
                group.getTeacher().getTeacherId()
        );

        entity.setCapacity(
                group.getCapacity()
        );

        return entity;
    }

    public static Group toDomain(
            GroupEntity entity,
            Course course,
            Teacher teacher,
            List<GroupSchedule> schedules
    ) {
        return new Group(
                entity.getGroupId(),
                course,
                entity.getGroupCode(),
                teacher,
                entity.getCapacity(),
                schedules
        );
    }
}