package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controllers.DataController;
import controllers.PermissionController;
import types.DataColumnTypes;
import types.UserTypes;

public class PermissionsData extends DataController {

    private DataColumnTypes userColumn = new DataColumnTypes("username");
    private DataColumnTypes fileColumn = new DataColumnTypes("file");
    private DataColumnTypes readColumn = new DataColumnTypes("read");
    private DataColumnTypes deleteColumn = new DataColumnTypes("delete");
    private DataColumnTypes executeColumn = new DataColumnTypes("execute");

    protected UsersData usersData = new UsersData();

    public PermissionsData() {
        super("PermissionsData.txt");
    }

    // public PermissionController getPermissionByUser(UserTypes user) {
    // String[] rowArray = this.findItemByColumn(user.getUsername(), userColumn);

    // // Caso nao encontre o usuario na tabela, retorna nulo
    // if (rowArray == null)
    // return null;

    // String usernameData = rowArray[this.getIndexFromColumn(userColumn)];
    // String fileData = rowArray[this.getIndexFromColumn(fileColumn)];

    // Boolean readData =
    // Boolean.parseBoolean(rowArray[this.getIndexFromColumn(readColumn)]);
    // Boolean deleteData =
    // Boolean.parseBoolean(rowArray[this.getIndexFromColumn(deleteColumn)]);
    // Boolean executeData =
    // Boolean.parseBoolean(rowArray[this.getIndexFromColumn(executeColumn)]);

    // // PermissionController precisa de um UserController como parametro em sua
    // // instancia
    // UserTypes userController = usersData.getUserByUsername(usernameData);

    // PermissionController permissionController = new PermissionController(
    // userController,
    // fileData,
    // readData,
    // deleteData,
    // executeData);

    // return permissionController;
    // }

    public List<PermissionController> getPermissionsFromFile(File file) {
        List<String[]> rowArray = this.findItemsByColumn(file.getName(), fileColumn);

        // Caso nao encontre o usuario na tabela, retorna nulo
        if (rowArray == null)
            return null;

        List<PermissionController> permissions = new ArrayList<PermissionController>();

        for (String[] row : rowArray) {

            String usernameData = row[this.getIndexFromColumn(userColumn)];
            String fileData = row[this.getIndexFromColumn(fileColumn)];

            Boolean readData = PermissionController.parsePermission(row[this.getIndexFromColumn(readColumn)]);
            Boolean deleteData = PermissionController.parsePermission(row[this.getIndexFromColumn(deleteColumn)]);
            Boolean executeData = PermissionController.parsePermission(row[this.getIndexFromColumn(executeColumn)]);

            // PermissionController precisa de um UserController como parametro em sua
            // instancia
            UserTypes userController = usersData.getUserByUsername(usernameData);

            PermissionController permissionController = new PermissionController(
                    userController,
                    fileData,
                    readData,
                    deleteData,
                    executeData);

            permissions.add(permissionController);
        }

        return permissions;
    }

    public PermissionController getPermissionFromFileByUser(File files, UserTypes user) {
        List<PermissionController> permissionsFromFile = getPermissionsFromFile(files);
        PermissionController permission = null;

        for (PermissionController perm : permissionsFromFile) {
            if (perm.getUser().getUsername().equals(user.getUsername())) {
                permission = perm;
            }
        }
        return permission;
    }

    public void createPermission(PermissionController perm) {
        // Cria a linha da tabela
        String[] permRow = new String[] {
                perm.getUser().getUsername(),
                perm.getFilename(),
                perm.canReadStringfy(),
                perm.canDeleteStringfy(),
                perm.canExecuteStringfy()
        };
        this.appendRow(permRow);
    }

    public void deletePermission(PermissionController perm) {
        List<String[]> table = this.getTable();
        if (table == null)
            return;

        for (int i = 0; i < table.size(); i++) {
            String[] row = table.get(i);
            String userField = row[this.getIndexFromColumn(userColumn)];
            String fileField = row[this.getIndexFromColumn(fileColumn)];

            if (userField.equals(perm.getUser().getUsername()) && fileField.equals(perm.getFilename())) {
                this.removeRowByIndex(i);
            }
        }

    }
}
