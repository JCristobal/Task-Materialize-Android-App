package jcristobal.task_materialize_android_app;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.animation.AnimationUtils;

import java.util.List;


public class MainActivity extends ActionBarActivity

    implements GetMemesCallback {

        private ListView mMemesListView;
        //private RecyclerView mMemesListView;
        private ProgressBar mLoadingProgressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mMemesListView      = (ListView) findViewById(R.id.activity_main_memes_listview);
            //mMemesListView      = (RecyclerView) findViewById(R.id.activity_main_memes_reciclerView);
            mLoadingProgressBar = (ProgressBar) findViewById(R.id.activity_main_loading_indicator);

            // // Fab button
            int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
            //Outline fabOutline = new Outline();
            //fabOutline.setOval(0, 0, fabSize, fabSize);

            View fabView = findViewById(R.id.fab_add);
            // //fabView.setOutline(fabOutline);

            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    // Or read size directly from the view's width/height
                    int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                    outline.setOval(0, 0, size, size);
                }
            };
            fabView.setOutlineProvider(viewOutlineProvider);


            ImageButton btn_fab = (ImageButton) findViewById(R.id.fab_add);
            btn_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Visita JCristobal en GitHub", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            });

        }

    public void startGetMemesRequest(View requestButton) {

        requestButton.setVisibility(View.GONE);
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        GetMemesHelper.getInstance().loadMemesWithIon(this, this);
    }

    @Override
    public void onMemesResult(List<MemeEntity> memesList) {

        mLoadingProgressBar.setVisibility(View.GONE);

        String [] memesNames = new String[memesList.size()];

        for (int i = 0; i < memesList.size(); i++)
            memesNames[i] = memesList.get(i).getTitle();

        MemeAdapter memesAdapter = new MemeAdapter(this, memesList);

        mMemesListView.setAdapter(memesAdapter);      //   <-----
    }

    @Override
    public void onMemesError() {

    }
}
