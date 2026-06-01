package org.unisheduler.backend.domain.port.out.academic_programming;

import org.unisheduler.backend.domain.model.academic_programming.entity.GroupSchedule;

import java.util.List;

public interface GroupScheduleRepository {
    List<GroupSchedule> findAllWhereGroupId(String groupId);
}
