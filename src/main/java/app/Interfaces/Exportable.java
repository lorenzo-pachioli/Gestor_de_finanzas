package app.Interfaces;

import org.json.JSONObject;

public interface Exportable {

    String toCSV();

    String getCSVHeaders();
}
