package com.example.quizlabmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private FragmentPregunta1 fragmentPregunta1;
    private FragmentPregunta2 fragmentPregunta2;
    private FragmentPregunta3 fragmentPregunta3;
    private FragmentPregunta4 fragmentPregunta4;
    private FragmentPregunta5 fragmentPregunta5;
    private FragmentError fragmentError;
    private FragmentPreguntaExtra fragmentPreguntaExtra;
    private int puntuacion = 0;
    private int numberFragment = 1;

    SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.quiz_del_pokemon);
        toolbar.setLogo(R.drawable.ic_launcher_foreground);
        setSupportActionBar(toolbar);
        FragmentInicial fragmentInicial = new FragmentInicial();
        fragmentPregunta1 = new FragmentPregunta1();
        fragmentPregunta2 = new FragmentPregunta2();
        fragmentPregunta3 = new FragmentPregunta3();
        fragmentPregunta4 = new FragmentPregunta4();
        fragmentPregunta5 = new FragmentPregunta5();
        fragmentPreguntaExtra = new FragmentPreguntaExtra();
        fragmentError = new FragmentError();
        changeFragment(fragmentInicial);
        sp = new SoundPool.Builder().build();
        sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            if (status == 0)
                sp.play(sampleId, 1.0f, 1.0f, 0, 0, 1.0f);
            else
                Log.e("SoundPool", "Fallo al cargar SoundPool");
        });
        sp.load(this, R.raw.sound_start, 1);
    }

    @SuppressLint("SetTextI18n")
    public void preguntaNO(View view){
        TextView textView=findViewById(R.id.ayuda);
        textView.setText("¿Seguro?");
    }
    @SuppressLint("SetTextI18n")
    public void preguntaSI(View view){
        TextView textView=findViewById(R.id.ayuda);
        textView.setText("El juego trata de acertar las maximas preguntas posibles. Cada acierto te sumará 5 puntos, y cada fallo te restará 2.");
    }
    public void ayuda(View view){
        setContentView(R.layout.pantalla_ayuda);
    }

    public void atras(View view){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent pantallaInicial = new Intent(this, MainActivity.class);
        startActivity(pantallaInicial, options.toBundle());
    }

    public void comprobar(View view) {
        switch (numberFragment) {
            case 1:
                RadioButton radioButton = findViewById(R.id.radioBrok);
                numberFragment++;
                if (radioButton.isChecked())
                    corrected(view);
                else {
                    radioButton = findViewById(view.getId());
                    failed("La respuesta correcta es: Brok");
                }
                radioButton.setChecked(false);
                break;
            case 2:
                radioButton = findViewById(R.id.charmander);
                numberFragment++;
                if (radioButton.isChecked())
                    corrected(view);
                else
                    failed("La respuesta correcta es: Charmander");
                break;
            case 3:
                EditText editText = findViewById(R.id.editTextNumber);
                numberFragment++;
                if (String.valueOf(editText.getText()).equals("151"))
                    corrected(view);
                else
                    failed("La respuesta correcta es: 151");
                editText.setText("");
                break;
            case 4:
                ListView listView = findViewById(R.id.listViewId);
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
                String selectedListItem = adapter.getItem(listView.getCheckedItemPosition());
                numberFragment++;
                if (selectedListItem != null && selectedListItem.equalsIgnoreCase("7"))
                    corrected(view);
                else
                    failed("La respuesta correcta es: 7");
                break;
            case 5:
                Spinner spinner = findViewById(R.id.spinner);
                SpinnerItem selectedOption = (SpinnerItem) spinner.getSelectedItem();
                numberFragment++;
                if (selectedOption.getText().equalsIgnoreCase("Agua"))
                    corrected(view);
                else
                    failed("La respuesta correcta es: Agua");
                spinner.setSelection(0);
                break;
        }
    }

    private void corrected(View view) {
        sp.load(this, R.raw.sound_correct, 1);
        puntuacion += 3;
        Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
        onClickNext(view);
    }

    private void failed(String frase) {
        sp.load(this, R.raw.sound_fail, 1);
        puntuacion -= 2;
        Toast.makeText(this, frase, Toast.LENGTH_LONG).show();
        refreshScore();
        changeFragment(fragmentError);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerFragment, fragment).commit();
    }

    public void comenzar(View view) {
        numberFragment = 1;
        puntuacion = 0;
        refreshScore();
        changeFragment(fragmentPregunta1);
    }

    @SuppressLint("SetTextI18n")
    private void refreshScore() {
        TextView textView = findViewById(R.id.puntuacionId);
        textView.setText("Puntuación: " + puntuacion);
    }

    public void onClickNext(View view) {
        refreshScore();
        switch (numberFragment) {
            case 2:
                changeFragment(fragmentPregunta2);
                break;
            case 3:
                changeFragment(fragmentPregunta3);
                break;
            case 4:
                changeFragment(fragmentPregunta4);
                break;
            case 5:
                changeFragment(fragmentPregunta5);
                break;
            case 6:
                changeFragment(fragmentPreguntaExtra);
                break;
            case 7:
                finalScreen();
                break;
        }
    }

    private void finalScreen() {
        sp.load(this, R.raw.sound_clapping, 1);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent pantallaFinal = new Intent(this, FinalActivity.class);
        pantallaFinal.putExtra("puntuacion", puntuacion);
        startActivity(pantallaFinal, options.toBundle());
    }

    public void comprobarExtra(String respuestaCorrecta, View view) {
        ListView listView = findViewById(R.id.listQuestionDB);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        String selectedListItem = adapter.getItem(listView.getCheckedItemPosition());
        if (selectedListItem != null && selectedListItem.equalsIgnoreCase(respuestaCorrecta)) {
            corrected(view);
        } else
            failed("La respuesta correcta es: " + respuestaCorrecta);
    }
}