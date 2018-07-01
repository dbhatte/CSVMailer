package com.devendra.csvmailer.model;

public class Recipient {
    private String email;

    private String firstName;

    private String lastName;

    public Recipient(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Recipient valueOf(String s, String delimiter) {
        String[] data = s.split(delimiter);
        return new Recipient(removeQuotes(data[0]), removeQuotes(data[1]), removeQuotes(data[2]));
    }

    private static String removeQuotes(String datum) {
        return datum.replaceAll("^\"|\"$", "");
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

    @Override
    public String toString() {
        return email;
    }
}
