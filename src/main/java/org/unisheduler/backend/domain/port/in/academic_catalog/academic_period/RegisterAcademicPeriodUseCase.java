package org.unisheduler.backend.domain.port.in.academic_catalog.academic_period;

import org.unisheduler.backend.application.service.academic_catalog.in.academic_period.RegisterAcademicPeriodCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.academic_period.dtos.RegisterAcademicPeriodResponse;

public interface RegisterAcademicPeriodUseCase {
    RegisterAcademicPeriodResponse execute(RegisterAcademicPeriodCommand command);
}
