package app.models.usuarios;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {

    private final StringProperty emailProperty = new SimpleStringProperty("admin@gmail.com");
    private final StringProperty passwordProperty = new SimpleStringProperty("pass123");

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }
}
