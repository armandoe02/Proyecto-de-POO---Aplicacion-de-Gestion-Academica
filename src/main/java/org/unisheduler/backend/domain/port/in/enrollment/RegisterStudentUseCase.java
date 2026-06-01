package org.unisheduler.backend.domain.port.in.enrollment;

import org.unisheduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unisheduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;

public interface RegisterStudentUseCase {
    RegisterStudentResponse execute(RegisterStudentCommand command);
}
