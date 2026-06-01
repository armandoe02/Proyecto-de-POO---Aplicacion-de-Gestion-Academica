package org.unisheduler.backend.domain.port.out.academic_programming;

import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;

import java.util.ArrayList;

public interface SemesterTemplateDetailRepository {
    ArrayList<SemesterTemplateDetail> findAllWhereTemplateId(String templateId);
}
