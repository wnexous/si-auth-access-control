package pages;

import controllers.PageController;

public class RegistrationPages extends PageController {

    public RegistrationPages() {

        this.registerPage(new HomePages());
        this.registerPage(new LoginPages());

    }

}