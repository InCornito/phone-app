package es.masmovil.phoneorder.entity;

public class User {

    private String id;
    private String name;
    private String surName;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(final String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
