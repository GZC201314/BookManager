package org.bsm.pageModel;

public class Combobox {
    private String id;
    private String text;

    @Override
    public String toString() {
        return "Combobox [id=" + id + ", text=" + text + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
