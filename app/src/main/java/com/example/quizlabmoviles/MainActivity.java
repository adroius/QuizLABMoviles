package com.example.quizlabmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FragmentInicial fragmentInicial;
    private FragmentPregunta1 fragmentPregunta1;
    private FragmentPregunta2 fragmentPregunta2;
    private FragmentPregunta3 fragmentPregunta3;
    private FragmentPregunta4 fragmentPregunta4;
    private FragmentPregunta5 fragmentPregunta5;
    private FragmentError fragmentError;

    private int puntuacion = 0;
    private int numberFragment = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta);
        fragmentInicial = new FragmentInicial();
        fragmentPregunta1 = new FragmentPregunta1();
        fragmentPregunta2 = new FragmentPregunta2();
        fragmentPregunta3 = new FragmentPregunta3();
        fragmentPregunta4 = new FragmentPregunta4();
        fragmentPregunta5 = new FragmentPregunta5();
        fragmentError = new FragmentError();
        changeFragment(fragmentInicial);
    }

    public void comprobar(View view) {
        switch (numberFragment) {
            case 1:
                RadioButton radioButton = findViewById(R.id.radioBrok);
                numberFragment++;
                if (radioButton.isChecked()) {
                    puntuacion += 3;
                    radioButton.setChecked(false);
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    onClickNext(view);
                } else {
                    RadioButton radioButtonError = findViewById(view.getId());
                    radioButtonError.setChecked(false);
                    puntuacion -= 2;
                    Toast.makeText(this,"La respuesta correcta es: Brok", Toast.LENGTH_LONG).show();
                    refreshScore();
                    changeFragment(fragmentError);
                }
                break;
            case 2:
                radioButton = findViewById(R.id.charmander);
                numberFragment++;
                if (radioButton.isChecked()) {
                    puntuacion += 3;
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    onClickNext(view);
                } else {
                    puntuacion -= 2;
                    Toast.makeText(this, "La respuesta correcta es: Charmander", Toast.LENGTH_LONG).show();
                    refreshScore();
                    changeFragment(fragmentError);
                }
                break;
            case 3:
                EditText editText = findViewById(R.id.editTextNumber);
                numberFragment++;
                if (String.valueOf(editText.getText()).equals("151")) {
                    puntuacion += 3;
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    onClickNext(view);
                } else {
                    puntuacion -= 2;
                    Toast.makeText(this, "La respuesta correcta es: 151", Toast.LENGTH_LONG).show();
                    refreshScore();
                    changeFragment(fragmentError);
                }
                editText.setText("");
                break;
            case 4:
                ListView listView = findViewById(R.id.listViewId);
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
                String selectedListItem = adapter.getItem(listView.getCheckedItemPosition());
                numberFragment++;
                if (selectedListItem != null && selectedListItem.equalsIgnoreCase("7")) {
                    puntuacion += 3;
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    onClickNext(view);
                } else {
                    puntuacion -= 2;
                    Toast.makeText(this, "La respuesta correcta es: 7", Toast.LENGTH_LONG).show();
                    refreshScore();
                    changeFragment(fragmentError);
                }
                break;
            case 5:
                Spinner spinner = findViewById(R.id.spinner);
                SpinnerItem selectedOption = (SpinnerItem) spinner.getSelectedItem();
                numberFragment++;
                if (selectedOption.getText().equalsIgnoreCase("Agua")) {
                    puntuacion += 3;
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    onClickNext(view);
                } else {
                    puntuacion -= 2;
                    Toast.makeText(this, "La respuesta correcta es: Agua", Toast.LENGTH_LONG).show();
                    refreshScore();
                    changeFragment(fragmentError);
                }
                spinner.setSelection(0);
                break;
        }
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
        textView.setText("Puntuación: " + String.valueOf(puntuacion));
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
                finalScreen();
                break;
        }
    }

    private void finalScreen() {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent pantallaFinal = new Intent(this, FinalActivity.class);
        pantallaFinal.putExtra("puntuacion", puntuacion);
        startActivity(pantallaFinal, options.toBundle());
    }
}