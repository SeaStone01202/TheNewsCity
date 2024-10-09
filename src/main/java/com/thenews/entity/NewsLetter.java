package com.thenews.entity;

public class NewsLetter {

    private Integer newsletterId;

    private String email;

    private boolean enabled;

    public NewsLetter() {
    }

    public NewsLetter(Integer newsletterId, String email, boolean enabled) {
        this.newsletterId = newsletterId;
        this.email = email;
        this.enabled = enabled;
    }

    public Integer getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(Integer newsletterId) {
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
