package com.cellfishpool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cellfishpool.adapters.OnRecipeListener;
import com.cellfishpool.adapters.RecipeRecyclerAdapter;
import com.cellfishpool.models.Recipe;
import com.cellfishpool.util.VerticalSpacingItemDecor;
import com.cellfishpool.viewmodel.RecipeListViewModel;
import java.util.List;


public class RecipeListActivity extends Base_Activity implements OnRecipeListener {

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView recipe_list;
    private RecipeRecyclerAdapter mAdapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        mSearchView=findViewById(R.id.search_view);
        recipe_list=findViewById(R.id.recipe_list);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mRecipeListViewModel.ismIsViewingRecipes()){
            //Toast.makeText(getApplicationContext(),"Mil Gya",Toast.LENGTH_LONG).show();
            //display category
            displaySearchCategories();
        }
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_categories){
            displaySearchCategories();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_recipe_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void subscribeObservers(){

        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes!=null){
                    if(mRecipeListViewModel.ismIsViewingRecipes()) {
                        mRecipeListViewModel.setmPerfomingQuery(false);
                        mAdapter.setRecipes(recipes);
                    }
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

        recipe_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(recipe_list.canScrollVertically(1)){
                    mRecipeListViewModel.search_more();
                }
            }
        });
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
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi(query,1);
                mSearchView.clearFocus();
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
