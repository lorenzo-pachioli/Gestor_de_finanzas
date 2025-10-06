package app.models.usuarios;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class RegistroModel {

    private final StringProperty nombreProperty = new SimpleStringProperty("admin");
    private final StringProperty apellidoProperty = new SimpleStringProperty("admin");
    private final StringProperty dniProperty = new SimpleStringProperty("admin");
    private final StringProperty emailProperty = new SimpleStringProperty("admin");
    private final StringProperty telefonoProperty = new SimpleStringProperty("admin");
    private final StringProperty contraseniaProperty = new SimpleStringProperty("pass123");
    private final StringProperty repetirContraseniaProperty = new SimpleStringProperty("pass123");

    public StringProperty nombrePropertyProperty() {
        return nombreProperty;
    }

    public StringProperty apellidoPropertyProperty() {
        return apellidoProperty;
    }

    public StringProperty dniPropertyProperty() {
        return dniProperty;
    }

    public StringProperty emailPropertyProperty() {
        return emailProperty;
    }

    public StringProperty telefonoPropertyProperty() {
        return telefonoProperty;
    }


    public StringProperty contraseniaPropertyProperty() {
        return contraseniaProperty;
    }

    public StringProperty repetirContraseniaPropertyProperty() {
        return repetirContraseniaProperty;
    }
}
