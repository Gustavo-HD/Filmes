package filmesdb.usjt.br.filmesdb.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import filmesdb.usjt.br.filmesdb.R;
import filmesdb.usjt.br.filmesdb.model.Filme;
import filmesdb.usjt.br.filmesdb.model.FilmeDAO;

public class ListaFilmesActivity extends Activity {
    public static final String FILME = "filmesdb.usjt.br.filmesdb.controller.filme";
    public ArrayList<Filme> filmes;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);


        activity = this;

        Intent intent = getIntent();

        String generoSelecionado = intent.getStringExtra(MainActivity.GENERO);


        filmes = (ArrayList<Filme>) intent.getSerializableExtra(MainActivity.FILMES);


        ListView listView = (ListView) findViewById(R.id.lista_filmes);
        FilmeAdapter adapter = new FilmeAdapter(filmes, this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Filme filme = filmes.get(i);
                Intent intent = new Intent(activity, DetalheFilmeActivity.class);
                intent.putExtra(FILME, filme);
                startActivity(intent);
            }
        });

    }


    private ArrayList<Filme> listaAlfabeticaFilmes(ArrayList<Filme> filmes) {

        //Ordena o array em ordem alfabética
        Collections.sort(filmes, new Comparator<Filme>() {
            @Override
            public int compare(Filme filme1, Filme filme2) {
                return filme1.getTitulo().compareTo(filme2.getTitulo());
            }
        });

        return filmes;
    }
}
