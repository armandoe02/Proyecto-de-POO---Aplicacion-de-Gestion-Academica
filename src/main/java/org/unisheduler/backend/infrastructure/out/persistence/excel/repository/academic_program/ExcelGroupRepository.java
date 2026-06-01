package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.GroupEntity;

import java.io.File;
import java.util.Optional;

public class ExcelGroupRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<GroupEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            for (int i = 0; i < groupTable.getRowCount(); i++) {

                String groupId = groupTable.getCellByPosition(0, i).getStringValue();

                if (groupId.equals(id)) {

                    GroupEntity group = new GroupEntity();
                    group.setGroupId(groupId);
                    group.setCourseId(groupTable.getCellByPosition(1, i).getStringValue());
                    group.setTeacherId(groupTable.getCellByPosition(2, i).getStringValue());
                    group.setGroupCode(groupTable.getCellByPosition(3, i).getStringValue());
                    group.setCapacity(Integer.parseInt(groupTable.getCellByPosition(4, i).getStringValue()));

                    return Optional.of(group);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
