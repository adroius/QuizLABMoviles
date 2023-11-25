package com.example.quizlabmoviles;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DBPreguntas extends AppCompatActivity {

    public Cursor selectQuestions(SQLiteDatabase BdD, int cod) {
        Cursor question = BdD.rawQuery("select pregunta, opcion1, opcion2, opcion3, opcion4," +
                " respuesta from pregunta where codigo =" + String.valueOf(cod), null);
        return question;
    }
}
