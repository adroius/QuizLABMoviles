package com.example.quizlabmoviles;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DBPreguntas extends AppCompatActivity {

    public void createQuestions(SQLiteDatabase BdD, int codigo, String pregunta, String opcion1,
                                String opcion2, String opcion3, String opcion4, String respuesta) {

        ContentValues question = new ContentValues();

        question.put("codigo", Integer.toString(codigo));
        question.put("pregunta", pregunta);
        question.put("opcion1", opcion1);
        question.put("opcion2", opcion2);
        question.put("opcion3", opcion3);
        question.put("opcion4", opcion4);
        question.put("respuesta", respuesta);

        BdD.insert("pregunta", null, question);
    }

    public Cursor selectQuestions(SQLiteDatabase BdD, int cod) {
        Cursor question = BdD.rawQuery("select pregunta, opcion1, opcion2, opcion3, opcion4," +
                " respuesta from pregunta where codigo =" + String.valueOf(cod), null);
        return question;
    }
}
