package com.cellfishpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecipeListActivity extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mhello =findViewById(R.id.hello);

        mhello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProgressBar.getVisibility()==View.VISIBLE){
                    showProgressBar(false);
                }
                else{
                    showProgressBar(true);
                }
            }
        });
    }
}
