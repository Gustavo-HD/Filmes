package filmesdb.usjt.br.filmesdb.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilmeDAO {
    private static OkHttpClient client = new OkHttpClient();

    public FilmeDAO() {}

    public static ArrayList<Filme> getFilmes(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String arquivo = response.body().string();

        ArrayList<Filme> filmes = new ArrayList<>();

        try {
            JSONObject lista = new JSONObject(arquivo);
            JSONArray listaResults = lista.getJSONArray("results");

            for(int i = 0; i < listaResults.length(); i++) {
                JSONObject item = (JSONObject) listaResults.get(i);
                Filme filme = new Filme();
                filme.setId(item.getInt("id"));
                filme.setDataLancamento(item.getString("release_date"));
                filme.setTitulo(item.getString("title"));
                filme.setDiretor("Não informado");

                //Array de Generos do filme
                //JSONArray listaGenerosFilme = item.getJSONArray("genre_ids");
                //JSONArray generos = listaGenerosFilme.getJSONArray(0);
                //Genero generoFilme = GeneroDAO.buscaGenero(Integer.parseInt(generos.get(0)));
                filme.setGenero("GeneroTeste");

                filmes.add(filme);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return filmes;
    }

}
