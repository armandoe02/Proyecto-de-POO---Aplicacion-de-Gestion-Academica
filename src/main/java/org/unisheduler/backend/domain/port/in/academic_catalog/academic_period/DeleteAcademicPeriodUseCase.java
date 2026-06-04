package org.unisheduler.backend.domain.port.in.academic_catalog.academic_period;

import org.unisheduler.backend.application.service.academic_catalog.in.academic_period.DeleteAcademicPeriodCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.academic_period.dtos.DeleteAcademicPeriodResponse;

public interface DeleteAcademicPeriodUseCase {
    DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command);
}
