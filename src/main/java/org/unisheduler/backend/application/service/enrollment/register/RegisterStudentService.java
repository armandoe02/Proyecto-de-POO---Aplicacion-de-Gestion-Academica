package org.unisheduler.backend.application.service.enrollment.register;

import org.unisheduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unisheduler.backend.application.service.enrollment.register.dtos.StudentInfo;
import org.unisheduler.backend.domain.exceptions.auth.DocumentAlreadyExistsException;
import org.unisheduler.backend.domain.exceptions.auth.EmailAlreadyExistsException;
import org.unisheduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unisheduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplate;
import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.model.auth.enums.DocumentType;
import org.unisheduler.backend.domain.model.auth.enums.Gender;
import org.unisheduler.backend.domain.model.auth.enums.Status;
import org.unisheduler.backend.domain.model.auth.vo.Email;
import org.unisheduler.backend.domain.model.auth.vo.EncodedPassword;
import org.unisheduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unisheduler.backend.domain.model.enrollment.entity.Student;
import org.unisheduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unisheduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unisheduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unisheduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unisheduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unisheduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unisheduler.backend.domain.port.out.academic_programming.SemesterTemplateRepository;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class RegisterStudentService implements RegisterStudentUseCase {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AcademicProgramRepository academicProgramRepository;
    private final PasswordGeneratorPort passwordGenerator;
    private final PasswordEncoderPort passwordEncoderPort;
    private final StudentCodeGeneratorPort studentCodeGenerator;
    private final SemesterTemplateRepository semesterTemplateRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;

    public RegisterStudentService(UserRepository userRepository, StudentRepository studentRepository,
                                  EnrollmentRepository enrollmentRepository, AcademicProgramRepository academicProgramRepository,
                                  PasswordGeneratorPort passwordGenerator, PasswordEncoderPort passwordEncoderPort,
                                  StudentCodeGeneratorPort studentCodeGenerator, SemesterTemplateRepository semesterTemplateRepository,
                                  EnrollmentDetailRepository enrollmentDetailRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.academicProgramRepository = academicProgramRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoderPort = passwordEncoderPort;
        this.studentCodeGenerator = studentCodeGenerator;
        this.semesterTemplateRepository = semesterTemplateRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
    }

    @Override
    public RegisterStudentResponse execute(RegisterStudentCommand command) {

        if(userRepository.existsByDocumentNumber(command.getDocumentNumber())) {
            throw new DocumentAlreadyExistsException("El numero de documento ya se encuentra registrado");
        }

        if(userRepository.existsByEmail(command.getEmail())) {
            throw  new EmailAlreadyExistsException("El correo electronico ingresado ya existe");
        }

        Optional<AcademicProgram> academicProgramOptional = academicProgramRepository.findById(command.getAcademicProgramId());
        if(academicProgramOptional.isEmpty()) {
            throw new EntityNotFoundException("No existe un programa academico con id: " + command.getAcademicProgramId());
        }

        AcademicProgram academicProgram = academicProgramOptional.get();

        // Random Password Generation
        String rawPassword = passwordGenerator.generatePassword();
        String hashedPassword = passwordEncoderPort.encode(rawPassword);
        EncodedPassword encodedPassword = new EncodedPassword(hashedPassword);

        Email email = new Email(command.getEmail());
        DocumentType documentType = DocumentType.valueOf(command.getDocumentType());
        Gender gender = Gender.valueOf(command.getGender());

        User user = new User(
                null,
                command.getFirstName(),
                command.getLastName(),
                documentType,
                command.getDocumentNumber(),
                command.getBirthDate(),
                gender,
                command.getPhoneNumber(),
                command.getAddress(),
                email,
                encodedPassword,
                Status.valueOf(command.getStatus()),
                null,
                null
        );

        User userSaved = userRepository.save(user);

        String studentCode;
        int attempts = 0;
        int maxAttempts = 5;

        do {
            // Random StudentCode Generation
            studentCode = studentCodeGenerator.execute();
            attempts++;

            if(attempts > maxAttempts) {
                throw new IllegalStateException("No se pudo generar un código único para el estudiante");
            }

        } while(studentRepository.existsByStudentCode(studentCode));

        Student student = new Student(
                null,
                studentCode,
                userSaved
        );

        Student studentSaved = studentRepository.save(student);

        SemesterTemplate template = semesterTemplateRepository.findByProgramAndSemester(academicProgram, command.getInitialSemester());

        Enrollment enrollment = new Enrollment(
                null,
                studentSaved,
                academicProgram,
                LocalDate.now(),
                null
        );
        Enrollment enrollmentSaved = enrollmentRepository.save(enrollment);

        for(SemesterTemplateDetail detail : template.getDetails()) {
            EnrollmentDetail enrollmentDetail = new EnrollmentDetail(
                    null,
                    detail.getGroup(),
                    EnrollmentStatus.ENROLLED,
                    0.0
            );
            enrollmentDetailRepository.save(enrollmentDetail, enrollmentSaved.getEnrollmentId());
        }

        return new RegisterStudentResponse(
                true,
                "El estudiante fue registrado con exito",
                new StudentInfo(
                        userSaved.getUserId(),
                        studentSaved.getStudentId(),
                        studentSaved.getStudentCode(),
                        rawPassword
                )
        );
    }
}
