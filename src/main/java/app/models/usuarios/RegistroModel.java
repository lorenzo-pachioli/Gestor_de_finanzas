package app.models.usuarios;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class RegistroModel {

    private final StringProperty nombreProperty = new SimpleStringProperty("admin");
    private final StringProperty apellidoProperty = new SimpleStringProperty("algo");
    private final IntegerProperty dniProperty = new SimpleIntegerProperty(123456);
    private final StringProperty emailProperty = new SimpleStringProperty("admin@gmail.com");
    private final IntegerProperty telefonoProperty = new SimpleIntegerProperty(5555555);
    private final StringProperty contraseniaProperty = new SimpleStringProperty("pass123");
    private final StringProperty repetirContraseniaProperty = new SimpleStringProperty("pass123");

    public StringProperty nombreProperty() {
        return nombreProperty;
    }

    public StringProperty apellidoProperty() {
        return apellidoProperty;
    }

    public IntegerProperty dniProperty() {
        return dniProperty;
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public IntegerProperty telefonoProperty() {
        return telefonoProperty;
    }


    public StringProperty contraseniaProperty() {
        return contraseniaProperty;
    }

    public StringProperty repetirContraseniaProperty() {
        return repetirContraseniaProperty;
    }
}
