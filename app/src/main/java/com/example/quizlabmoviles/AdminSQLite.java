package com.example.quizlabmoviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AdminSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "miBaseDeDatos";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "pregunta";
    private static final String COLUMN_ID = "codigo";
    private static final String COLUMN_PREGUNTA = "pregunta";
    private static final String COLUMN_RESPUESTA1 = "respuesta1";
    private static final String COLUMN_RESPUESTA2 = "respuesta2";
    private static final String COLUMN_RESPUESTA3 = "respuesta3";
    private static final String COLUMN_RESPUESTA4 = "respuesta4";
    private static final String COLUMN_RESPUESTA_CORRECTA = "respuesta_correcta";

    public AdminSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase miBBDD) {
        //miBBDD.execSQL("create table "+TABLE_NAME+" (codigo int primary key, pregunta text, opcion1 text," +
        //        "opcion2 text, opcion3 text, opcion4 text, respuesta text)");
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PREGUNTA + " TEXT, " +
                COLUMN_RESPUESTA1 + " TEXT, " +
                COLUMN_RESPUESTA2 + " TEXT, " +
                COLUMN_RESPUESTA3 + " TEXT, " +
                COLUMN_RESPUESTA4 + " TEXT, " +
                COLUMN_RESPUESTA_CORRECTA + " TEXT)";
        miBBDD.execSQL(createTableQuery);
        crearPreguntas();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertPregunta(String pregunta, String respuesta1, String respuesta2, String respuesta3,
                               String respuesta4, String respuestaCorrecta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PREGUNTA, pregunta);
        values.put(COLUMN_RESPUESTA1, respuesta1);
        values.put(COLUMN_RESPUESTA2, respuesta2);
        values.put(COLUMN_RESPUESTA3, respuesta3);
        values.put(COLUMN_RESPUESTA4, respuesta4);
        values.put(COLUMN_RESPUESTA_CORRECTA, respuestaCorrecta);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Método para obtener una pregunta aleatoria de la base de datos
    public Pregunta obtenerPreguntaAleatoria() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {
                COLUMN_ID,
                COLUMN_PREGUNTA,
                COLUMN_RESPUESTA1,
                COLUMN_RESPUESTA2,
                COLUMN_RESPUESTA3,
                COLUMN_RESPUESTA4,
                COLUMN_RESPUESTA_CORRECTA
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        List<Pregunta> preguntas = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String pregunta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PREGUNTA));
            String respuesta1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPUESTA1));
            String respuesta2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPUESTA2));
            String respuesta3 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPUESTA3));
            String respuesta4 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPUESTA4));
            String respuestaCorrecta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPUESTA_CORRECTA));

            Pregunta nuevaPregunta = new Pregunta(id, pregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuestaCorrecta);
            preguntas.add(nuevaPregunta);
        }

        cursor.close();
        db.close();

        // Mezclar las preguntas para obtener una aleatoria
        Collections.shuffle(preguntas);

        // Devolver la primera pregunta (podrías personalizar esto para devolver una pregunta específica)
        return preguntas.isEmpty() ? null : preguntas.get(0);
    }

    private void crearPreguntas() {
        insertPregunta(
                "¿Cuál es el Pokémon inicial que puedes elegir en la región de Kanto?",
                "Charmander", "Squirtle", "Bulbasaur", "Pikachu", "Bulbasaur"
        );

// Insertar pregunta 2
        insertPregunta(
                "¿Qué tipo de Pokémon es Pikachu?",
                "Agua", "Eléctrico", "Fuego", "Planta", "Eléctrico"
        );

// Insertar pregunta 3
        insertPregunta(
                "¿Cuál es el tipo de Pokémon de Eevee?",
                "Normal", "Psíquico", "Hada", "Volador", "Normal"
        );

// Insertar pregunta 4
        insertPregunta(
                "¿Cuál es el nombre del Profesor Pokémon en la región de Johto?",
                "Profesor Oak", "Profesor Elm", "Profesor Birch", "Profesor Rowan", "Profesor Elm"
        );
        // Insertar pregunta 5
        insertPregunta(
                "¿Qué tipo de Pokémon es Gyarados?",
                "Agua/Volador", "Agua/Dragón", "Agua/Siniestro", "Agua/Eléctrico", "Agua/Dragón"
        );

// Insertar pregunta 6
        insertPregunta(
                "¿Cuántas evoluciones tiene Bulbasaur?",
                "Una", "Dos", "Tres", "Cuatro", "Tres"
        );

// Insertar pregunta 7
        insertPregunta(
                "¿Cuál es el Pokémon legendario que representa el trío de las aves en la región de Kanto?",
                "Articuno", "Zapdos", "Moltres", "Lugia", "Articuno"
        );

// Insertar pregunta 8

        insertPregunta(
                "¿En qué tipo se especializa la Líder de Gimnasio Misty?",
                "Agua", "Fuego", "Planta", "Eléctrico", "Agua"
        );
    }
}

