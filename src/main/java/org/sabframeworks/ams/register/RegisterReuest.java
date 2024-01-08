package org.sabframeworks.ams.register;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RegisterReuest {

    private final String firstName;
    private final String lastName;

    private final String emailId;
    private final String passWord;

    private final String country;

    private final String state;



    @JsonCreator
    public RegisterReuest(@JsonProperty("firstname") String firstName, @JsonProperty("lastname") String lastName,
                          @JsonProperty("email") String emailId, @JsonProperty("password") String passWord,
                          @JsonProperty("country") String country, @JsonProperty("state") String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.passWord = passWord;
        this.country =country;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterReuest that = (RegisterReuest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(emailId, that.emailId) && Objects.equals(passWord, that.passWord) && Objects.equals(country, that.country) && Objects.equals(state, that.state);
    }

    @Override
    public String toString() {
        return "RegisterReuest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", passWord='" + passWord + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, emailId, passWord, country, state);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassWord() {
        return passWord;
    }
}
