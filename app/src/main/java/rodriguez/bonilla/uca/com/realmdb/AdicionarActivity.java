package rodriguez.bonilla.uca.com.realmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import io.realm.Realm;

public class AdicionarActivity extends AppCompatActivity {

    public Realm realm;

    private EditText tituloEditText;
    private EditText autorEditText;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        realm = Realm.getDefaultInstance();

        tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        autorEditText = (EditText) findViewById(R.id.autorEditText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void guardarLibro(View view) {

        String titulo = tituloEditText.getText().toString();
        String autor = autorEditText.getText().toString();
        float puntaje = ratingBar.getRating();

        realm.beginTransaction();
        Libro li = realm.createObject(Libro.class);
        li.setTitulo(titulo);
        li.setAutor(autor);
        li.setPuntaje(puntaje);
        realm.commitTransaction();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

}
