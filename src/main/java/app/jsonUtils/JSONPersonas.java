package app.jsonUtils;

import app.enums.NivelAcceso;
import app.models.colecciones.ListaGenerica;
import app.models.colecciones.ListaPersonas;
import app.models.extras.PasswordAuth;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;
import app.models.usuarios.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;


public class JSONPersonas extends JSONUtiles {

    private static final String archivo = "Personas.json";

    public static void grabarPersonas(JSONArray array){
        grabar(array, archivo);
    }

    public static void grabarPersonas(ListaPersonas listaPersonas){
        JSONArray array = escribirPersonasArray(listaPersonas);
        grabar(array, archivo);
    }

    public static void grabarUnaPersona(Persona persona){
        JSONArray jsonLista = leerPersonas();
        for(int i=0 ; i<jsonLista.length() ; i++){
            JSONObject o = jsonLista.getJSONObject(i);
            if(o.getString("id").equals(String.valueOf(persona.getId()))){
                jsonLista.put(i, personaAJson(persona));
            }
        }
        grabar(jsonLista, archivo);
    }

    public static void borrarUnaPersona(Persona persona){
        JSONArray jsonLista = leerPersonas();
        JSONArray jsonListaNueva = new JSONArray();
        for(int i=0 ; i<jsonLista.length() ; i++){
            JSONObject o = jsonLista.getJSONObject(i);
            if(!o.getString("id").equals(String.valueOf(persona.getId()))){
                jsonListaNueva.put(o);
            }
        }
        grabar(jsonListaNueva, archivo);
    }

    public static JSONArray leerPersonas(){
        return new JSONArray(leer(archivo));
    }

    public static ListaGenerica<Persona> leerPersonasArray(){
        ListaGenerica<Persona> listaPersonas = new ListaGenerica<>();
        JSONArray JSONPersonas = leerPersonas();
        for(int i=0 ; i<JSONPersonas.length() ; i++){
            listaPersonas.agregar(jsonAPersona(JSONPersonas.getJSONObject(i)));
        }
        return listaPersonas;
    }

    public static Persona logInPersona(String email, String contrasenia) {
        try {
            JSONArray jsonPersonas = new JSONArray(JSONUtiles.leer(archivo));

            for(int i = 0 ; i<jsonPersonas.length() ; i++){
                JSONObject persona = jsonPersonas.getJSONObject(i);

                if(persona.getString("email").equals(email) && PasswordAuth.authenticate(contrasenia, persona.getString("contrasenia"))){

                    return jsonAPersona(persona);
                }
            }
            return new Usuario();
        } catch (JSONException e){
            System.out.println(e.getMessage());
            return new Usuario();
        }
    }

    public static void registrarPersona(Persona persona){
        JSONArray array = leerPersonas();
        JSONObject u = new JSONObject();
        u.put("id", persona.getId());
        u.put("nombre", persona.getNombre());
        u.put("apellido", persona.getApellido());
        u.put("dni", persona.getDni());
        u.put("telefono", persona.getTelefono());
        u.put("email", persona.getEmail());
        u.put("contrasenia", persona.getContrasenia());
        u.put("acceso", persona instanceof Usuario ? NivelAcceso.USUARIO:NivelAcceso.ADMINISTRADOR);
        u.put("bloqueado", persona.isBloqueado());
        array.put(u);
        grabarPersonas(array);
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

        String id = persona.getString("id");
        String nombre = persona.getString("nombre");
        String apellido = persona.getString("apellido");
        int dni = persona.getInt("dni");
        int telefono = persona.getInt("telefono");
        String email = persona.getString("email");
        String contrasenia = persona.getString("contrasenia");
        String nivel = persona.getString("acceso");
        boolean bloqueado = persona.getBoolean("bloqueado");
        if(nivel.equalsIgnoreCase("USUARIO")){
            return new Usuario(UUID.fromString(id), nombre, apellido, dni, email, telefono, contrasenia, bloqueado);
        }
        return new Administrador(UUID.fromString(id), nombre, apellido, dni, email, telefono, contrasenia, bloqueado);
    }


    public static JSONArray escribirPersonasArray(ListaPersonas listaPersonas){
        JSONArray jsonListaPersonas = new JSONArray();
        for(Persona persona : listaPersonas.getListaPersonas()){
            jsonListaPersonas.put(personaAJson(persona));
        }
        return jsonListaPersonas;
    }

    public static JSONObject personaAJson(Persona persona){
        JSONObject jsonPersona = new JSONObject();
        jsonPersona.put("id", persona.getId());
        jsonPersona.put("nombre", persona.getNombre());
        jsonPersona.put("apellido", persona.getApellido());
        jsonPersona.put("dni", persona.getDni());
        jsonPersona.put("telefono", persona.getTelefono());
        jsonPersona.put("email", persona.getEmail());
        jsonPersona.put("contrasenia", persona.getContrasenia());
        jsonPersona.put("acceso", persona instanceof Usuario ? NivelAcceso.USUARIO:NivelAcceso.ADMINISTRADOR);
        jsonPersona.put("bloqueado", persona.isBloqueado());

        return jsonPersona;
    }
}
