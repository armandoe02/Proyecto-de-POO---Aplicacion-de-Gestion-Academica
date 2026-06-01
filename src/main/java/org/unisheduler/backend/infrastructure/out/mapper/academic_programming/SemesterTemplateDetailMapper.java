package org.unisheduler.backend.infrastructure.out.mapper.academic_programming;

import org.unisheduler.backend.domain.model.academic_programming.entity.Group;
import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateDetailEntity;

public class SemesterTemplateDetailMapper {

    public static SemesterTemplateDetailEntity toEntity(
            SemesterTemplateDetail detail,
            String templateId
    ) {
        SemesterTemplateDetailEntity entity =
                new SemesterTemplateDetailEntity();

        entity.setTemplateDetailId(
                detail.getTemplateDetailId()
        );

        entity.setTemplateId(templateId);

        entity.setGroupId(
                detail.getGroup().getGroupId()
        );

        return entity;
    }

    public static SemesterTemplateDetail toDomain(
            SemesterTemplateDetailEntity entity,
            Group group
    ) {
        return new SemesterTemplateDetail(
                entity.getTemplateDetailId(),
                group
        );
    }
}
