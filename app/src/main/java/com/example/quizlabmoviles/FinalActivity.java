package com.example.quizlabmoviles;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        int puntuacion = getIntent().getIntExtra("puntuacion", 0);
        TextView text = findViewById(R.id.puntuacionInt);
        text.setText(String.valueOf(puntuacion));

        ImageView backgroundImageView = findViewById(R.id.gifFinal);
        String gifUrl = "https://i.pinimg.com/originals/e5/83/3e/e5833e1bea7d379f0f4e4ae250b7cf81.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(new DrawableImageViewTarget(backgroundImageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        GifDrawable gifDrawable = (GifDrawable) resource;
                        gifDrawable.start();
                    }
                }.getView());
    }

    public void reiniciar(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio, options.toBundle());
    }
}
