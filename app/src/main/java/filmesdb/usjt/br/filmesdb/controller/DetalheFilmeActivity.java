package filmesdb.usjt.br.filmesdb.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import filmesdb.usjt.br.filmesdb.R;
import filmesdb.usjt.br.filmesdb.model.Filme;
import filmesdb.usjt.br.filmesdb.model.Util;

public class DetalheFilmeActivity extends Activity {
    private TextView tituloFilme, sinopseFilme, diretorFilme, lancamentoFilme, popularidadeFilme;
    private ImageView fotoFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        //"Binda" as propriedades da tela
        tituloFilme = (TextView) findViewById(R.id.titulo_detalhe_filme);
        diretorFilme = (TextView) findViewById(R.id.diretor_detalhe_filme);
        lancamentoFilme = (TextView) findViewById(R.id.data_lancamento_detalhe_filme);
        popularidadeFilme = (TextView) findViewById(R.id.popularidade_detalhe_filme);
        fotoFilme = (ImageView) findViewById(R.id.foto_detalhe_filme);

        //Adiciona o Scroll no TextView
        sinopseFilme = (TextView) findViewById(R.id.sinopse_detalhe_filme);
        //sinopseFilme.setMovementMethod(new ScrollingMovementMethod());

        //Recebe o context
        Intent intent = getIntent();
        Filme filme = (Filme) intent.getSerializableExtra(ListaFilmesActivity.FILME);

        //Adiciona os valores nos inputs da tela
        tituloFilme.setText(filme.getTitulo().toString());
        diretorFilme.setText(filme.getDiretor().toString());
        lancamentoFilme.setText(filme.getDataLancamento().toString());
        popularidadeFilme.setText(Double.toString(filme.getPopularidade()));
        sinopseFilme.setText(filme.getSinopse().toString());

        Drawable drawable = Util.getDrawable(this, filme.getId());
        fotoFilme.setImageDrawable(drawable);
    }
}
