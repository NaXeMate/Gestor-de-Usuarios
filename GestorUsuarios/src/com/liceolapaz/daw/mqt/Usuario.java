package com.liceolapaz.daw.mqt;

public class Usuario {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public Usuario(int id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", avatar=" + avatar + "]";
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }
}
