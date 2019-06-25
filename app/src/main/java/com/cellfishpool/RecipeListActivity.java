package com.cellfishpool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cellfishpool.adapters.OnRecipeListener;
import com.cellfishpool.adapters.RecipeRecyclerAdapter;
import com.cellfishpool.adapters.RecipeViewHolder;
import com.cellfishpool.models.Recipe;
import com.cellfishpool.util.VerticalSpacingItemDecor;
import com.cellfishpool.viewmodel.RecipeListViewModel;
import java.util.List;


public class RecipeListActivity extends Base_Activity implements OnRecipeListener {

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView recipe_list;
    private RecipeRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);


        recipe_list=findViewById(R.id.recipe_list);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mRecipeListViewModel.ismIsViewingRecipes()){
            //Toast.makeText(getApplicationContext(),"Mil Gya",Toast.LENGTH_LONG).show();
            //display category
            displaySearchCategories();
        }

    }

    private void subscribeObservers(){

        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes!=null){
                    if(mRecipeListViewModel.ismIsViewingRecipes())
                        mAdapter.setRecipes(recipes);
                }

            }
        });
    }
    
    private void initRecyclerView(){
        VerticalSpacingItemDecor itemDecorator = new VerticalSpacingItemDecor(30);
        recipe_list.addItemDecoration(itemDecorator);
        mAdapter = new RecipeRecyclerAdapter(this);
        recipe_list.setAdapter(mAdapter);
        recipe_list.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public void onRecipeClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(category,1);

    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi(query,1);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                return false;
            }
        });
    }

    private void displaySearchCategories(){
        mRecipeListViewModel.setmIsViewingRecipes(false);
        mAdapter.displaySearchCategories();
    }

    @Override
    public void onBackPressed() {
        if(mRecipeListViewModel.onBackPressed()){
            super.onBackPressed();
        }
        else{
            displaySearchCategories();
        }
    }
}
