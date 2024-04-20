package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.DataController;
import controllers.UserController;
import interfaces.DataInterfaces;
import types.DataColumnTypes;

// public class UserData extends DataController implements DataInterfaces {
public class UsersData extends DataController {

    private DataColumnTypes userColumn = new DataColumnTypes("username");
    private DataColumnTypes passColumn = new DataColumnTypes("password");

    public UsersData() {
        super("UsersData.txt");
    }

    // @Override
    // public List<DataColumnTypes> getHeader() {
    // return new ArrayList<DataColumnTypes>(Arrays.asList(userColumn, passColumn));
    // }

    public UserController getUserByUsername(String username) {
        String[] useyArray = this.findItemByColumn(username, userColumn);

        // Caso nao encontre o usuario na tabela, retorna nulo
        if (useyArray == null)
            return null;

        String usernameData = useyArray[this.getIndexFromColumn(userColumn)];
        String passwordData = useyArray[this.getIndexFromColumn(passColumn)];
        
        UserController userController = new UserController(usernameData, passwordData);
        return userController;
    }

    public void createUser(UserController user) {

        String[] userRow = new String[] { user.getUsername(), user.getPassword() };
        this.appendRow(userRow);

    }
}
