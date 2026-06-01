package org.unisheduler.backend.domain.port.out.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.AcademicProgram;

import java.util.Optional;

public interface AcademicProgramRepository {
    Optional<AcademicProgram> findById(String id);
}
