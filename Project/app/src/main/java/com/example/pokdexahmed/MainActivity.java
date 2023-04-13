package com.example.pokdexahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pokdexahmed.models.Pokemon;
import com.example.pokdexahmed.models.PokemonRequest;
import com.example.pokdexahmed.pokeapi.PokeApiServ;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private AdapterPokeList adapterPokeList;
    private int offset;
    private boolean ap;

    private static final String TAG = "POKEDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapterPokeList = new AdapterPokeList(this);
        recyclerView.setAdapter(adapterPokeList);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if(ap){
                        if (visibleItemCount + pastVisibleItems >= totalItemCount ){
                            Log.i(TAG,"SALINA");
                            ap = false;
                            offset+= 20;
                            bringData(offset);
                        }
                    }
                }

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(MainActivity.this, recyclerView, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Show progress dialog or loading indicator


// Make API request and retrieve data
// Once data is retrieved, dismiss progress dialog and start second activity

                //intent kaysiftna mn mainact 1 l mainact 2
                Intent intent2 = new Intent(MainActivity.this,MainActivity2.class);
                intent2.putExtra("index",String.valueOf(position+1));

               // img= findViewById(position+1);
                //img.setTransitionName("A"+String.valueOf(position+1));

               // GradientDrawable drawable2 = (GradientDrawable) img.getBackground();
                //Pair[] pairs = new Pair[1];
                //pairs[0] = new Pair<View,String>(img,"A"+String.valueOf(position+1));
                //intent2.putExtra("color",String.valueOf(drawable2.getColor().getDefaultColor()));
               // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

                //startActivity(intent2,options.toBundle());
                startActivity(intent2);

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ap = true;
        offset = 0;
        bringData(offset);
    }


    private void bringData(int offset) {
        PokeApiServ service = retrofit.create(PokeApiServ.class);
        Call<PokemonRequest> pokemonRequestCall = service.jibPokimounat(20, offset);

        pokemonRequestCall.enqueue(new Callback<PokemonRequest>() {
            @Override
            public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {
                ap = true;
                if (response.isSuccessful()) {
                    PokemonRequest pokemonRequest = response.body();
                    ArrayList<Pokemon> listPokemon = pokemonRequest.getResults();

                    adapterPokeList.addPokemonList(listPokemon);




                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<PokemonRequest> call, Throwable t) {
                ap = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}