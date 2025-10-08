package app.jsonUtils;

import org.json.JSONArray;
import org.json.JSONTokener;

public class JSONPersonas extends JSONUtiles {

    public static void grabarPersonas(JSONArray array){
        grabar(array, "Personas");
    }

    public static JSONTokener leerPersonas(){
        return leer("Personas");
    }


}
