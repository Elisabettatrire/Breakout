package ids.univpm.breakout.communication;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import ids.univpm.breakout.communication.http.PostRequest;
import ids.univpm.breakout.communication.message.MessageBuilder;
import ids.univpm.breakout.controller.MainApplication;
import ids.univpm.breakout.model.Utente;
import ids.univpm.breakout.model.database.DBHelper;
import ids.univpm.breakout.model.database.Utente.UtenteManager;

public class Server {

    private static String hostMaster; //= hostname;
    private static SQLiteDatabase db;

    private static final ArrayList<String> userProfileKeys = new ArrayList<String>(){{
        add("ID_utente");
        add("username");
        add("email");
        add("password");
        add("nome");
        add("cognome");
        add("ultima_pos");
    }};

    public static boolean autenticazioneUtente(String user, String pw) throws ExecutionException, InterruptedException {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        name.add("username");
        name.add("password");
        value.add(user);
        value.add(pw);
        String mex = MessageBuilder.builder(name,value,value.size(),0);//TODO:dopo la richiesta post io devo far processare il risultato ottenendo un json object e prendere il valore booleano (restituito dal db server)di tale object e metterlo nell if
        if (Boolean.parseBoolean(new PostRequest().execute(hostMaster,"user/login",mex).get()))//TODO:dipende dalla risposta del db server//

        {
            UtenteManager u_manager= new UtenteManager(MainApplication.getCurrentActivity().getApplicationContext());
            DBHelper dbhelper= new DBHelper(MainApplication.getCurrentActivity().getApplicationContext());
            try {
                db = dbhelper.getWritableDatabase();
                Utente utente_da_log=u_manager.findByUser(user);
                //TODO:is_loggato a true??
                dbhelper.close();
            }
            catch (Exception e)  {
                e.printStackTrace();
            }
            return true;
        }
        else return false;
    }

    void registrazioneUtente(){

    }

    void downloadMappe(){

    }

    void logoutUtente(){

    }

    void downloadModifiche(){

    }

    //TODO metodo invio posizione utente
    public static void sendPosition(String mex) throws ExecutionException, InterruptedException {

    }

    //TODO metodo invio valori beacon
    public static void sendValue(String mex) throws ExecutionException, InterruptedException {

    }
}
