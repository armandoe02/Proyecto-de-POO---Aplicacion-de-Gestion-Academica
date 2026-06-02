package org.unisheduler.backend.application.service.academic_catalog.out.dtos;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;

import java.util.List;

public class CourseInfo {
    private final String courseId;
    private final String name;
    private final String code;
    private final int credits;
    private final String description;
    private final List<PrerequisiteInfo> prerequisites;

    public CourseInfo(String courseId, String name, String code, int credits, String description, List<PrerequisiteInfo> prerequisites) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.description = description;
        this.prerequisites = prerequisites;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public List<PrerequisiteInfo> getPrerequisites() {
        return prerequisites;
    }
}
