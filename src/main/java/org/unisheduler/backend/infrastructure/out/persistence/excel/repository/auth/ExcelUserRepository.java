package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.auth;

import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;
import org.unisheduler.backend.infrastructure.out.entity.auth.UserEntity;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.mapper.auth.RoleMapper;

public class ExcelUserRepository{

    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public UserEntity save(UserEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table userTable = doc.getTableByName("Users");
            Table userRoleTable = doc.getTableByName("UserRole");

            String newUserId = ExcelIdGenerator.generateNextId(userTable, 0);
            entity.setUserId(newUserId);

            int userRow = userTable.getRowCount();
            userTable.appendRow();

            userTable.getCellByPosition(0, userRow).setStringValue(entity.getUserId());
            userTable.getCellByPosition(1, userRow).setStringValue(entity.getFirstName());
            userTable.getCellByPosition(2, userRow).setStringValue(entity.getLastName());
            userTable.getCellByPosition(3, userRow).setStringValue(entity.getDocumentType());
            userTable.getCellByPosition(4, userRow).setStringValue(entity.getDocumentNumber());
            userTable.getCellByPosition(5, userRow).setStringValue(String.valueOf(entity.getBirthDate()));
            userTable.getCellByPosition(6, userRow).setStringValue(entity.getGender());
            userTable.getCellByPosition(7, userRow).setStringValue(entity.getPhoneNumber());
            userTable.getCellByPosition(8, userRow).setStringValue(entity.getAddress());
            userTable.getCellByPosition(9, userRow).setStringValue(entity.getEmail());
            userTable.getCellByPosition(10, userRow).setStringValue(entity.getPassword());
            userTable.getCellByPosition(11, userRow).setStringValue(entity.getStatus());
            userTable.getCellByPosition(12, userRow).setStringValue(entity.getRole());
            userTable.getCellByPosition(13, userRow).setStringValue(String.valueOf(entity.getCreatedAt()));

            String roleId = RoleMapper.toId(entity.getRole());
            String userRoleId = ExcelIdGenerator.generateNextId(userRoleTable, 0);

            int roleRow = userRoleTable.getRowCount();
            userRoleTable.appendRow();

            userRoleTable.getCellByPosition(0, roleRow).setStringValue(userRoleId);
            userRoleTable.getCellByPosition(1, roleRow).setStringValue(newUserId);
            userRoleTable.getCellByPosition(2, roleRow).setStringValue(roleId);

            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserEntity> findByEmail(String email) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table userTable = doc.getTableByName("Users");

            for (int i = 0; i < userTable.getRowCount(); i++) {

                String currentEmail = userTable.getCellByPosition(9, i).getStringValue();

                if (email.equals(currentEmail)) {

                    return Optional.of(mapRow(userTable, i));
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByEmail(String email) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table userTable = doc.getTableByName("Users");

            for (int i = 0; i < userTable.getRowCount(); i++) {

                String currentEmail = userTable.getCellByPosition(9, i).getStringValue();

                if (email.equals(currentEmail)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByDocumentNumber(String documentNumber) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table userTable = doc.getTableByName("Users");

            for (int i = 0; i < userTable.getRowCount(); i++) {

                String currentDoc = userTable.getCellByPosition(4, i).getStringValue();

                if (documentNumber.equals(currentDoc)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserEntity> findById(String id) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table userTable = doc.getTableByName("Users");

            for (int i = 0; i < userTable.getRowCount(); i++) {

                String currentId = userTable.getCellByPosition(0, i).getStringValue();

                if (id.equals(currentId)) {

                    return Optional.of(mapRow(userTable, i));
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserEntity mapRow(Table table, int i) {

        UserEntity entity = new UserEntity();

        entity.setUserId(table.getCellByPosition(0, i).getStringValue());
        entity.setFirstName(table.getCellByPosition(1, i).getStringValue());
        entity.setLastName(table.getCellByPosition(2, i).getStringValue());
        entity.setDocumentType(table.getCellByPosition(3, i).getStringValue());
        entity.setDocumentNumber(table.getCellByPosition(4, i).getStringValue());

        entity.setBirthDate(LocalDate.parse(table.getCellByPosition(5, i).getStringValue()));
        entity.setGender(table.getCellByPosition(6, i).getStringValue());

        entity.setPhoneNumber(table.getCellByPosition(7, i).getStringValue());
        entity.setAddress(table.getCellByPosition(8, i).getStringValue());

        entity.setEmail(table.getCellByPosition(9, i).getStringValue());
        entity.setPassword(table.getCellByPosition(10, i).getStringValue());

        entity.setStatus(table.getCellByPosition(11, i).getStringValue());
        entity.setRole(table.getCellByPosition(12, i).getStringValue());

        entity.setCreatedAt(LocalDateTime.parse(table.getCellByPosition(13, i).getStringValue()));

        return entity;
    }
}