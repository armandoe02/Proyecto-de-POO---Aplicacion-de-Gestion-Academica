package org.unisheduler.backend.domain.port.out.academic_programming;

import org.unisheduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplate;

public interface SemesterTemplateRepository {
    SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester);
}
