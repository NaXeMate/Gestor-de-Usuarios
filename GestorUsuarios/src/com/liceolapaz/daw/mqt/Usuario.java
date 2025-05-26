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
        return "  {\n"
                + "    \"id\": " + id + ",\n"
                + "    \"email\": " + email + ",\n"
                + "    \"first_name\": " + firstName + ",\n"
                + "    \"last_name\": " + lastName + ",\n"
                + "    \"avatar\": " + avatar + "\n"
                + "  },";
    }
}
