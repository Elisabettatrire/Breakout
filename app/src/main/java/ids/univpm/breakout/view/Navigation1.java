package ids.univpm.breakout.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import ids.univpm.breakout.R;
import ids.univpm.breakout.controller.Controller;
import ids.univpm.breakout.controller.MainApplication;

public class Navigation1 extends AppCompatActivity {

    private TextView connection_status;
    private ImageView non_connesso;
    private ImageView connesso;

    // inserire check per la connessione al server --> sotto icona rossa oppure verde x connesso
    // far si che appaia la mappa del piano in cui mi trovo, in base al beacon a cui sono connesso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation1);

        MainApplication.start(this);

        connection_status= findViewById(R.id.connection_status);
        non_connesso= findViewById(R.id.non_connesso);
        connesso= findViewById(R.id.connesso);

        if(Controller.checkConnection(Navigation1.this)) {
            non_connesso.setVisibility(View.INVISIBLE);
            connesso.setVisibility(View.VISIBLE);
            connection_status.setText("Connected");
        }else{
            connesso.setVisibility(View.INVISIBLE);
            non_connesso.setVisibility(View.VISIBLE);
            connection_status.setText("Disconnected");
        }

//TODO caricamento mappa
        if(getIntent() != null){
            //get id mappa from intent
        }else{

        }

      //TODO: da inserire TUTTO nella parte di caricamento mappa

        //Preparazione Bitmap

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;
        //TODO: deve prendere la mappa in base a che piano sta
        Bitmap workingbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.q150_color2, opt);

        //Bitmap workingBitmap = Bitmap.createBitmap(chosenFrame);
        Bitmap bitmap = workingbitmap.copy(Bitmap.Config.ARGB_8888, true);


        //istanzio un Canvas: lo uso per disegnare su una Bitmap
        Canvas canvas = new Canvas(bitmap);


        //Per disegnare il tratto con cui il Canvas disegnerà --> Paint
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //usando il Paint disegno un cerchio nella Drawable

        //cordinate iniziali 0 0


        //TODO: le coordinate dei beacon le devo prendere dal db e fare un ciclo for per mettere
        // tutti i puntini =beacon , nel disegno --> TODO prendere dati dal file excel?
        // METTO ANCHE I NODI

        //scale vicino g1 --> coordinate ora copiate a mano dal file excel, ma vanno prese dal db
        int server_b1x=218;
        int server_b1y=129;

        //conversione nei pixel dell'immagine: scale vicino g1
        int b1x= server_b1x;
        int b1y= server_b1y;

        //esempio beacon CORRIDOIO biblio
        int b2x= 678;
        int b2y= 456;


        //PROVO A DISEGNARE ALTRI PUNTI NELLA MAPPA
        //esempio 150/2
        int b3x= 489;
        int b3y= 160;

        //esempio corridoio atelier
        int b4x=633;
        int b4y=156;

        //g1 aula
        int b5x=280;
        int b5y=242;
        // uscita em
        int b6x=(int) 544;
        int b6y=(int) 11;




        //TODO anche qui va messa l'iterazione per disegnare puntini e le linee

        canvas.drawCircle(b1x, b1y, 8, paint);
        canvas.drawCircle(b2x, b2y, 8, paint);
        canvas.drawCircle(b3x, b3y, 8, paint);
        canvas.drawCircle(b4x, b4y, 8, paint);
        canvas.drawCircle(b5x, b5y, 8, paint);
        canvas.drawCircle(b6x, b6y, 8, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        //DISEGNO unA LINEA --> anche qui ci va un'ITERAZIONE PER DISEGNARE IL PERCORSO
        canvas.drawLine(b1x,b1y,b5x,b5y, paint);


        //TODO: in base a quale beacon sono collegato, faccio apparire il simbolo gps e ne disegno
        //        le coordinate con drawBitmap (sotto)


        Bitmap gps_pic= BitmapFactory.decodeResource(getResources(),R.drawable.gps,opt);
        Bitmap gps= Bitmap.createScaledBitmap(gps_pic,gps_pic.getWidth()/20,gps_pic.getHeight()/20,true);


        //metto il segnale gps in corrispondenza del primo beacon per ora
        int gps_x=b1x;
        int gps_y=b1y;

        canvas.drawBitmap(gps,gps_x -(gps.getWidth()/2),gps_y-(gps.getHeight()),null);

        //le coordinate da mettere nel drawbitmap (riga sopra) sono da prendere in riferimento
        // al beacon a cui sono collegato --> così ho il simbolo gps inserito nella mappa


        //la Bitmap disegnata con il Canvas diventa il contenuto della ImageView
        SubsamplingScaleImageView imageView = findViewById(R.id.immagine150);
        imageView.setImage(ImageSource.bitmap(bitmap));


    }



















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        //Se non loggato, cambia titolo in login nel menu(!)

        MenuItem item = menu.findItem(R.id.ricerca);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                startActivity(new Intent(Navigation1.this, RicercaPDI.class));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
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


            case R.id.log_status:

                //Inserire codice per LogOut se deve fare logout
                Intent returnBtn = new Intent(getApplicationContext(), Welcome.class);

                startActivity(returnBtn);
                break;

        }
        return false;
        //return super.onOptionsItemSelected(item);
    }
}

