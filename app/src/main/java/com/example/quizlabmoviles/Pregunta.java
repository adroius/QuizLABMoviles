package com.example.quizlabmoviles;

public class Pregunta {
    private int id;
    private String pregunta;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private String respuestaCorrecta;

    public Pregunta(int id, String pregunta, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuestaCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getId() {
        return id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}
