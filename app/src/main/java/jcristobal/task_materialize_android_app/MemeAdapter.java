package jcristobal.task_materialize_android_app;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class MemeAdapter extends ArrayAdapter<MemeEntity> {

    private Context mContext;
    private List<MemeEntity> mMemeEntitiesList;

    public MemeAdapter(Context context, List<MemeEntity> objects) {

        super(context, R.layout.item_meme, objects);

        mContext = context;
        mMemeEntitiesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_meme, parent, false);

        MemeEntity currentMeme = mMemeEntitiesList.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.item_meme_title);
        TextView emotionTextView = (TextView) convertView.findViewById(R.id.item_meme_emotion);
        ImageView memeImageView = (ImageView) convertView.findViewById(R.id.item_meme_image);

        titleTextView.setText(currentMeme.getTitle());
        emotionTextView.setText(currentMeme.getEmotion());

        // Transformación para redondear la imagen
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(1)
                .cornerRadiusDp(50)
                .oval(false)
                .build();
        //Aplicar la transformación con Picasso
        Picasso.with(getContext())
                .load(currentMeme.getPng())
                .fit()
                .transform(transformation)
                .into(memeImageView);

        // Load the meme image with Ion
        Ion.with(mContext)
            .load(currentMeme.getPng())
            .intoImageView(memeImageView);

        return convertView;
    }

    @Override
    public int getCount() {
        return mMemeEntitiesList.size();
    }



}
