package com.cdmk.app;

/**
 * Created by javon on 09/07/2016.
 */
public class Concept {

    public String prefLabel;
    public int score;

    public Concept()
    {

    }

    public Concept(String title, int score) {
        this.prefLabel = title;
        this.score = score;
    }

    public String getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(String prefLabel) {
        this.prefLabel = prefLabel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
