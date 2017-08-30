package com.mdelira.monsterdex.presenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mdelira.monsterdex.R;
import com.mdelira.monsterdex.models.PokemonEntry;
import com.mdelira.monsterdex.models.PokemonResponse;
import com.mdelira.monsterdex.pokeapi.PokeAPIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListPokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Procurar ou só mostrar os com estrela, temos que ver ainda", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Navigation Bar (barra lateral)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ------ COMEÇA AQUI O QUE MODIFIQUEI. Quase tudo acima é default do padrão Navigation Drawer kkkk
/*
        // Task de puxar dados -> usando a PokeAPI (descomentar classe interna privada ao final)
        RetreiveDataTask puxarDadosTask = new RetreiveDataTask();
        puxarDadosTask.execute();
*/

        this.recyclerView = (RecyclerView) findViewById(R.id.rvMainPokemon);
        this.pokemonAdapter = new ListPokemonAdapter();
        this.recyclerView.setAdapter(this.pokemonAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setHasFixedSize(true);

        // RETROFIT -> substitui a task de puxar dados enviando uma requisição GET para a url base definida
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/") // URL base dos HTTP GET
                .addConverterFactory(GsonConverterFactory.create()) // a fábrica que irá receber as respostas do GET na URL acima
                .build();

        puxarPokemon();

    }

    private void puxarPokemon(){
        PokeAPIService service = retrofit.create(PokeAPIService.class);
        Call<PokemonResponse> pokemonResponseCall = service.obterListaPokemon();

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                // Quando obtiver resposta, faça...
                // deve checar primeiro se o código HTTP é de sucesso
                if(response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();

                    ArrayList<PokemonEntry> listaPokemon = pokemonResponse.getResults();

                    // TODO persiste no banco local caso não exista ainda

                    // adiciona a nova lista ao adapter do RecyclerView, lembrando de dar um notifyDataSetChanged
                    pokemonAdapter.addDados(listaPokemon);
                    pokemonAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, getString(R.string.conn_successful_fetch_pokemon), Toast.LENGTH_SHORT).show();
                }else{
                    Log.e("MONSTERDEX", "Main > puxarPokemon() > onResponse() | "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                // Se der erro de alguma forma, faça...
                // ex: falha na conexão, modo avião, timeout.
                Toast.makeText(MainActivity.this, getString(R.string.conn_error_fetch_pokemon), Toast.LENGTH_SHORT).show();
                Log.e("MONSTERDEX", "Main > puxarPokemon() > onFailure() |"+t.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_refresh) {
            puxarPokemon();
            Toast.makeText(this, getString(R.string.action_menu_refreshing), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    /*
    private class RetreiveDataTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // vai salvar no banco local
            try{
                PokeApi pokeApi = new PokeApiClient();

//                PokemonSpecies oito = pokeApi.getPokemonSpecies(8);
//                Log.i("POKEMON", "oito: "+oito.getFlavorTextEntries().get(1));

                List<PokemonEntry> nationalDexEntries;  // puxa da national
//              Log.i("POKEMON",String.format("Count nationalDex: %s", String.valueOf(nationalDexEntries.size())));



            }catch (Exception e){
                Log.e("POKEMON", e.getMessage());
                Log.e("POKEMON", e.getStackTrace().toString());
            }

            return null;
        }
    }
    */
}

