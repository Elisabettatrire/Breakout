package ids.univpm.breakout.model.database.Utente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import ids.univpm.breakout.model.Mappa;
import ids.univpm.breakout.model.Utente;
import ids.univpm.breakout.model.database.DBHelper;
import ids.univpm.breakout.model.database.Mappa.MappaStrings;

public class UtenteManager {

    private Context context;
        private DBHelper dbHelper;

        public UtenteManager(Context ctx)
        {
            setContext(ctx);
            dbHelper =new DBHelper(ctx);
        }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void save(Long last_position, String email, String name, String password, String surname, String username, int is_logged)
        {
            SQLiteDatabase db= dbHelper.getWritableDatabase();
            dbHelper.getWritableDatabase();

            ContentValues cv=new ContentValues();
            cv.put(UtenteStrings.FIELD_EMAIL, email);
            cv.put(UtenteStrings.FIELD_NAME, name);
            cv.put(UtenteStrings.FIELD_PSW, password);
            cv.put(UtenteStrings.FIELD_SURNAME, surname);
            cv.put(UtenteStrings.FIELD_USER, username);
            cv.put(UtenteStrings.FIELD_IS_LOGGED, is_logged);
            cv.put(UtenteStrings.FIELD_LAST_POSITION, last_position);
            try
            {
                db.insert(UtenteStrings.TBL_NAME, null,cv);
            }
            catch (SQLiteException sqle)
            {
                // Gestione delle eccezioni
            }
        }

        public boolean deleteByID(long id)
        {
            SQLiteDatabase db= dbHelper.getWritableDatabase();
            try
            {
                if (db.delete(UtenteStrings.TBL_NAME, UtenteStrings.FIELD_ID+"=?", new String[]{Long.toString(id)})>0)
                    return true;
                return false;
            }
            catch (SQLiteException sqle)
            {
                return false;
            }

        }

        public Cursor query()
        {
            Cursor crs=null;
            try
            {
                SQLiteDatabase db= dbHelper.getReadableDatabase();
                crs=db.query(UtenteStrings.TBL_NAME, null, null, null, null, null, null, null);
            }
            catch(SQLiteException sqle)
            {
                return null;
            }
            return crs;
        }

    public ArrayList<Utente> findAll() {
        ArrayList<Utente> listaUtenti = new ArrayList<>();
        Cursor crs = query();
        for(crs.moveToFirst(); !crs.isAfterLast(); crs.moveToNext()) {
            Utente utente = findByID(crs.getLong(crs.getColumnIndex(UtenteStrings.FIELD_ID)));
            listaUtenti.add(utente);
        }
        return listaUtenti;
    }

    public Utente findByUser (String user){
        Cursor crs=null;
        Utente utente = new Utente();
        String[] args = new String[] {user};
        try
        {
            SQLiteDatabase db= dbHelper.getReadableDatabase();
            crs=db.query(UtenteStrings.TBL_NAME, null, UtenteStrings.FIELD_USER + " = ?", args, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }

        crs.moveToFirst();
        utente.setID_utente(crs.getLong(crs.getColumnIndex(UtenteStrings.FIELD_ID)));
        utente.setNome(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_NAME)));
        utente.setCognome(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_SURNAME)));
        utente.setEmail(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_EMAIL)));
        utente.setIs_logged(crs.getInt(crs.getColumnIndex(UtenteStrings.FIELD_IS_LOGGED)));
        utente.setPassword(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_PSW)));
        utente.setUltima_posizione(crs.getLong(crs.getColumnIndex(UtenteStrings.FIELD_LAST_POSITION)));
        utente.setUsername(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_USER)));

        return utente;
    }

    public Utente findByID (long id){
        Cursor crs=null;
        Utente utente = new Utente();
        String[] args = new String[] {Long.toString(id)};
        try
        {
            SQLiteDatabase db= dbHelper.getReadableDatabase();
            crs=db.query(UtenteStrings.TBL_NAME, null, "id_utente = ?", args, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }

        crs.moveToFirst();
        utente.setID_utente(id);
        utente.setNome(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_NAME)));
        utente.setCognome(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_SURNAME)));
        utente.setEmail(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_EMAIL)));
        utente.setIs_logged(crs.getInt(crs.getColumnIndex(UtenteStrings.FIELD_IS_LOGGED)));
        utente.setPassword(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_PSW)));
        utente.setUltima_posizione(crs.getLong(crs.getColumnIndex(UtenteStrings.FIELD_LAST_POSITION)));
        utente.setUsername(crs.getString(crs.getColumnIndex(UtenteStrings.FIELD_USER)));

        return utente;
    }


}
