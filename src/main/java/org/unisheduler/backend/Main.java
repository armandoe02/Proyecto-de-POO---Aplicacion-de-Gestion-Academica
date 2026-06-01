package org.unisheduler.backend;

import org.unisheduler.backend.application.service.auth.login.LoginUserCommand;
import org.unisheduler.backend.application.service.auth.login.LoginUserService;
import org.unisheduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unisheduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unisheduler.backend.application.service.enrollment.register.RegisterStudentService;
import org.unisheduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unisheduler.backend.domain.model.enrollment.entity.Student;
import org.unisheduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unisheduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unisheduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.domain.port.out.academic_programming.*;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unisheduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unisheduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unisheduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicProgramRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program.*;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.auth.ExcelUserRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentDetailRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelStudentRepository;
import org.unisheduler.backend.infrastructure.out.repository.academic_catalog.AcademicProgramRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.academic_catalog.CourseRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.academic_programming.*;
import org.unisheduler.backend.infrastructure.out.repository.auth.UserRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.EnrollmentDetailImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.EnrollmentRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.StudentRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.security.PasswordEncoderAdapter;
import org.unisheduler.backend.infrastructure.out.security.PasswordGeneratorAdapter;
import org.unisheduler.backend.infrastructure.out.security.StudentCodeGeneratorAdapter;

import java.time.LocalDate;

public class Main {

  // Metodo para crear un student command para testear el correcto funcionamiento de RegisterStudentService
  public static RegisterStudentCommand createStudentCommand() {
    return new RegisterStudentCommand(
            "Ana",
            "Gomez",
            "CC",
            "123456786",
            LocalDate.of(2002, 5, 15),
            "MALE",

            "3001234567",
            "Calle 123 #45-67",
            "ana.gomez@email.com",

            "1",
            1,
            LocalDate.now(),
            "ACTIVE"
    );
  }

  public static void dependencyInjection() {
    //================// Repositories (Infraestructura) //================//
    UserRepository userRepository = new UserRepositoryImpl(new ExcelUserRepository());

    StudentRepository studentRepository = new StudentRepositoryImpl(new ExcelStudentRepository(), userRepository);

    AcademicProgramRepository academicProgramRepository = new AcademicProgramRepositoryImpl(new ExcelAcademicProgramRepository());

    CourseRepository courseRepository = new CourseRepositoryImpl(new ExcelCourseRepository());

    TeacherRepository teacherRepository = new TeacherRepositoryImpl(new ExcelTeacherRepository());

    GroupScheduleRepository groupScheduleRepository = new GroupScheduleRepositoryImpl(new ExcelGroupScheduleRepository());

    GroupRepository groupRepository = new GroupRepositoryImpl(new ExcelGroupRepository(), courseRepository, teacherRepository, groupScheduleRepository);

    EnrollmentDetailRepository enrollmentDetailRepository = new EnrollmentDetailImpl(new ExcelEnrollmentDetailRepository(), groupRepository);

    EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl(new ExcelEnrollmentRepository(), academicProgramRepository, studentRepository, enrollmentDetailRepository);

    SemesterTemplateDetailRepository semesterTemplateDetailRepository = new SemesterTemplateDetailRepositoryImpl(new ExcelSemesterTemplateDetailRepository(), groupRepository);

    SemesterTemplateRepository semesterTemplateRepository = new SemesterTemplateRepositoryImpl(new ExcelSemesterTemplateRepository(), semesterTemplateDetailRepository);

    //================// Ports (Servicios auxiliares) //================//
    PasswordGeneratorPort passwordGenerator = new PasswordGeneratorAdapter();
    PasswordEncoderPort passwordEncoderPort = new PasswordEncoderAdapter();
    StudentCodeGeneratorPort studentCodeGenerator = new StudentCodeGeneratorAdapter();

    //================// Use Case (Aplicación) //================//
    RegisterStudentUseCase registerStudentService =
            new RegisterStudentService(
                    userRepository,
                    studentRepository,
                    enrollmentRepository,
                    academicProgramRepository,
                    passwordGenerator,
                    passwordEncoderPort,
                    studentCodeGenerator,
                    semesterTemplateRepository,
                    enrollmentDetailRepository
            );

    LoginUserUseCase loginUserService = new LoginUserService(
            userRepository,
            passwordEncoderPort
    );

    //================// Ejecutar caso de uso (ejemplo) //================//
    //RegisterStudentResponse response = registerStudentService.execute(createStudentCommand());
    //System.out.println("message: " + response.getMessage());
    //System.out.println("Codigo de estudiante: " + response.getStudent().getStudentCode());
    //System.out.println("Contrasenia: " + response.getStudent().getStudentPassword());

    LoginUserCommand loginCommand = new LoginUserCommand(
            "ana.gomez@email.com",
                  "SuwJivk@"
            );

    LoginUserResponse successLoginResponse = loginUserService.execute(loginCommand);

    System.out.println("-----------------------------------------------");
    System.out.println("Message: " + successLoginResponse.getMessage());

    if(!successLoginResponse.isSuccessfully()) {
      return;
    }

    System.out.println("UserId: " + successLoginResponse.getUser().getUserId());
    System.out.println("Email: " + successLoginResponse.getUser().getEmail());
    System.out.println("Full Name: " + successLoginResponse.getUser().getFullName());
    System.out.println("Role: " + successLoginResponse.getUser().getRole());
  }

  public static void main(String[] args) {

    dependencyInjection();
  }
}
