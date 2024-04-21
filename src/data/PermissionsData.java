package data;

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

    public PermissionController getPermissionByUser(UserTypes user) {
        String[] useyArray = this.findItemByColumn(user.getUsername(), userColumn);

        // Caso nao encontre o usuario na tabela, retorna nulo
        if (useyArray == null)
            return null;

        String usernameData = useyArray[this.getIndexFromColumn(userColumn)];
        String fileData = useyArray[this.getIndexFromColumn(fileColumn)];

        Boolean readData = Boolean.parseBoolean(useyArray[this.getIndexFromColumn(readColumn)]);
        Boolean deleteData = Boolean.parseBoolean(useyArray[this.getIndexFromColumn(deleteColumn)]);
        Boolean executeData = Boolean.parseBoolean(useyArray[this.getIndexFromColumn(executeColumn)]);

        // PermissionController precisa de um UserController como parametro em sua
        // instancia
        UserTypes userController = usersData.getUserByUsername(usernameData);

        PermissionController permissionController = new PermissionController(
                userController,
                fileData,
                readData,
                deleteData,
                executeData);

        return permissionController;
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
}
