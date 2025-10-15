package app.jsonUtils;

import app.enums.NivelAcceso;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;
import app.models.usuarios.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;


public class JSONPersonas extends JSONUtiles {

    private static final String archivo = "Personas.json";

    public static void grabarPersonas(JSONArray array){
        grabar(array, archivo);
    }

    public static JSONArray leerPersonas(){
        return new JSONArray(leer(archivo));
    }

    public static ArrayList<Persona> leerPersonasArray(){
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        JSONArray JSONPersonas = leerPersonas();
        for(int i=0 ; i<JSONPersonas.length() ; i++){
            listaPersonas.add(jsonAPersona(JSONPersonas.getJSONObject(i)));
        }
        return listaPersonas;
    }

    public static Persona logInPersona(String email, String contrasenia) {
        try {
            JSONArray jsonPersonas = new JSONArray(JSONUtiles.leer(archivo));

            for(int i = 0 ; i<jsonPersonas.length() ; i++){
                JSONObject persona = jsonPersonas.getJSONObject(i);

                if(persona.getString("email").equals(email) && persona.getString("contrasenia").equals(contrasenia)){

                    return jsonAPersona(persona);
                }
            }
            return new Usuario();
        } catch (JSONException e){
            System.out.println(e.getMessage());
            return new Usuario();
        }
    }

    public static boolean registrarUsuario(Usuario usuario){
        JSONArray array = new JSONArray();
        JSONObject u = new JSONObject();
        u.put("id", usuario.getId());
        u.put("nombre", usuario.getNombre());
        u.put("apellido", usuario.getApellido());
        u.put("dni", usuario.getDni());
        u.put("telefono", usuario.getTelefono());
        u.put("email", usuario.getEmail());
        u.put("contrasenia", usuario.getContrasenia());
        u.put("acceso", usuario.getAcceso());
        array.put(u);
        grabarPersonas(array);
        return true;
    }

    public static boolean existeusuario(String email){

        try {
            JSONArray jsonPersonas = new JSONArray(JSONUtiles.leer(archivo));

            for(int i = 0 ; i<jsonPersonas.length() ; i++){
                JSONObject persona = jsonPersonas.getJSONObject(i);
                if(persona.getString("email").equals(email)){
                    return true;
                }
            }
            return false;
        } catch (JSONException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Persona jsonAPersona(JSONObject persona){

        String nombre = persona.getString("nombre");
        String apellido = persona.getString("apellido");
        int dni = persona.getInt("dni");
        int telefono = persona.getInt("telefono");
        String email = persona.getString("email");
        String contrasenia = persona.getString("contrasenia");
        String nivel = persona.getString("acceso");
        if(nivel.equalsIgnoreCase("USUARIO")){
            return new Usuario(nombre, apellido, dni, email, telefono, contrasenia);
        }
        return new Administrador(nombre, apellido, dni, email, telefono, contrasenia);
    }
}
