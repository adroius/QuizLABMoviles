package com.example.quizlabmoviles;

public class SpinnerItem {
    private int imageResourceId;
    private String text;

    public SpinnerItem(int imageResourceId, String text) {
        this.imageResourceId = imageResourceId;
        this.text = text;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getText() {
        return text;
    }
}
