package org.unisheduler.backend.domain.port.out.academic_programming;

import org.unisheduler.backend.domain.model.academic_programming.entity.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
}
