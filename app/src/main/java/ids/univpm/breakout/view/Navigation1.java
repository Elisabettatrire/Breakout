package ids.univpm.breakout.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ids.univpm.breakout.R;

class Navigation1 extends AppCompatActivity {


    public TextView non_connesso_txt;
    public ImageView non_connesso;
    public TextView connesso_txt;
    public ImageView connesso;

    // inserire check per la connessione al server --> sotto icona rossa oppure verde x connesso
    // far si che appaia la mappa del piano in cui mi trovo, in base al beacon a cui sono connesso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation1);

        /*
        per vedere le icone x connesso, commentare la riga precente e mettere le seguenti righe:

        setContentView(R.layout.connection_bar);
        non_connesso_txt=(TextView) findViewById(R.id.non_connesso_txt);
        non_connesso= findViewById(R.id.non_connesso);
        connesso_txt=(TextView) findViewById(R.id.connesso_txt);
        connesso= findViewById(R.id.connesso);

        //Check connessione
        //se non connesso: Server connection: disconnected, simbolo X
        //non_connesso.setVisibility(View.VISIBLE);
        //non_connesso_txt.setVisibility(View.VISIBLE);

        // se connesso: connected: simbolo verde
        connesso.setVisibility(View.VISIBLE);
        connesso_txt.setVisibility(View.VISIBLE); */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigation_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.SelPiano:

                //Apertura nuova activity per scelta del piano
                startActivity(new Intent(Navigation1.this, SelPiano.class));

                break;

            case R.id.pdi:
                startActivity(new Intent(Navigation1.this, RicercaPDI.class));
                break;

            case R.id.Logout:

                //Inserire codice per LogOut
                break;

            case R.id.ricerca:
               // menu vuoto per ora
                //   break;
        }
        return false;
        //return super.onOptionsItemSelected(item);
    }
}

