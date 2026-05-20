package repository;


import module.academic_programming.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private List<Group> groups = new ArrayList<>();

    public void save(Group student) {
        groups.add(student);
    }

    public List<Group> findAll() {
        return groups;
    }

    public Group findById(Long id) {
        return groups.stream()
                .filter(s -> s.getGroupId() == id)
                .findFirst()
                .orElse(null);
    }
}
