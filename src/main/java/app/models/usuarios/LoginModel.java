package app.models.usuarios;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {

    private final StringProperty usernameProperty = new SimpleStringProperty("admin");
    private final StringProperty passwordProperty = new SimpleStringProperty("pass123");

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }


}
