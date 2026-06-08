package org.unischeduler.ui.app;


import org.unischeduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.UpdateGroupUseCase;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

public class AppContext {

    public static ExcelDataStore DATA_STORE;
    public static String FILE_PATH;
    private static LoginUserUseCase loginUserService;
    private static ListAllCoursesUseCase listAllCoursesService;
    private static RegisterCourseUseCase registerCourseService;
    private static UpdateCourseUseCase updateCourseService;
    private static DeleteCourseUseCase deleteCourseService;
    private static ListAllGroupsUseCase listAllGroupsService;
    private static RegisterGroupUseCase registerGroupService;
    private static UpdateGroupUseCase updateGroupService;
    private static DeleteGroupUseCase deleteGroupService;

    private AppContext() {}

    // --------------------------- Authentication ------------------------------------
    public static LoginUserUseCase getLoginUserService() {
        return loginUserService;
    }

    public static void setLoginUserService(LoginUserUseCase service) {
        loginUserService = service;
    }

    // --------------------------- Courses ------------------------------------
    public static ListAllCoursesUseCase getListAllCoursesService() {
        return listAllCoursesService;
    }

    public static void setListAllCoursesService(ListAllCoursesUseCase listAllCoursesService) {
        AppContext.listAllCoursesService = listAllCoursesService;
    }

    public static RegisterCourseUseCase getRegisterCourseService() {
        return registerCourseService;
    }

    public static void setRegisterCourseService(RegisterCourseUseCase registerCourseService) {
        AppContext.registerCourseService = registerCourseService;
    }

    public static UpdateCourseUseCase getUpdateCourseService() {
        return updateCourseService;
    }

    public static void setUpdateCourseService(UpdateCourseUseCase updateCourseService) {
        AppContext.updateCourseService = updateCourseService;
    }

    public static DeleteCourseUseCase getDeleteCourseService() {
        return deleteCourseService;
    }

    public static void setDeleteCourseService(DeleteCourseUseCase deleteCourseService) {
        AppContext.deleteCourseService = deleteCourseService;
    }

    // --------------------------- Groups ------------------------------------

    public static ListAllGroupsUseCase getListAllGroupsService() {
        return listAllGroupsService;
    }

    public static void setListAllGroupsService(ListAllGroupsUseCase listAllGroupsService) {
        AppContext.listAllGroupsService = listAllGroupsService;
    }

    public static RegisterGroupUseCase getRegisterGroupService() {
        return registerGroupService;
    }

    public static void setRegisterGroupService(RegisterGroupUseCase registerGroupService) {
        AppContext.registerGroupService = registerGroupService;
    }

    public static UpdateGroupUseCase getUpdateGroupService() {
        return updateGroupService;
    }

    public static void setUpdateGroupService(UpdateGroupUseCase updateGroupService) {
        AppContext.updateGroupService = updateGroupService;
    }

    public static DeleteGroupUseCase getDeleteGroupService() {
        return deleteGroupService;
    }

    public static void setDeleteGroupService(DeleteGroupUseCase deleteGroupService) {
        AppContext.deleteGroupService = deleteGroupService;
    }
}
