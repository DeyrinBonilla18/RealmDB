package rodriguez.bonilla.uca.com.realmdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ItemClickOyente{

    public Realm realm;

    private RecyclerView librosRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        librosRecyclerView = (RecyclerView) findViewById(R.id.librosRecyclerView);
        librosRecyclerView.setHasFixedSize(true);
        librosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        actualizarDatos();
    }

    public void adicionar(View view) {

        Intent intent = new Intent(this, AdicionarActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    public void actualizarDatos() {
        RealmQuery<Libro> query = realm.where(Libro.class);
        RealmResults<Libro> results = query.findAll();
        results = results.sort("puntaje", Sort.DESCENDING);

        librosRecyclerView.setAdapter(new LibrosAdapter(results, this));
    }

    @Override
    public void onItemClick(Libro libro) {
//        Toast.makeText(getApplicationContext(), libro.getTitulo(), Toast.LENGTH_SHORT).show();

//        realm.beginTransaction();
//        libro.setAutor("Maria Loza");
//        realm.commitTransaction();

        realm.beginTransaction();
        libro.deleteFromRealm();
        realm.commitTransaction();

        actualizarDatos();
    }
}
