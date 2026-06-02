package org.unisheduler.backend.application.service.academic_catalog.in;

public class DeleteCourseCommand {
    private final String id;

    public DeleteCourseCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
