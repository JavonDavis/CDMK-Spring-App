package com.cdmk.app;

/**
 * Created by javon on 09/07/2016.
 */
public class Concept {

    public String prefLabel;
    public int score;
    public boolean checked = false;

    public Concept()
    {

    }

    public Concept(String title, int score) {
        this.prefLabel = title;
        this.score = score;
    }

    public Concept(String title) {
        this.prefLabel = title;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Concept)
        {
            return this.prefLabel.toLowerCase().equals(((Concept) obj).getPrefLabel().toLowerCase());
        }
        return false;
    }
}
