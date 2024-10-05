package com.thenews.entity;

public class NewsLetter {

    private int newsletterId;

    private String email;

    private boolean enabled;

    public NewsLetter() {
    }

    public NewsLetter(int id, String email, boolean enabled) {
        this.newsletterId = id;
        this.email = email;
        this.enabled = enabled;
    }

    public int getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(int newsletterId) {
        this.newsletterId = newsletterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
