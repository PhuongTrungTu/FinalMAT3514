package com.raven.model;

import javax.swing.Icon;

public class ModelCard {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelCard(String title, int percentage, Icon icon) {
        this.title = title;
        this.percentage = percentage;
        this.icon = icon;
    }

    public ModelCard() {
    }

    private String title;
    private int percentage;
    private Icon icon;
}
