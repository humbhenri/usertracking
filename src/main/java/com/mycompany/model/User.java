package com.mycompany.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable {

    public static final int EMAIL_LEN = 100;
    public static final int LAST_NAME_LEN = 100;
    public static final int FIRST_NAME_LEN = 100;

    @Column(name = "firstName", length = FIRST_NAME_LEN)
    private String firstName;

    @Column(name = "lastName", length = LAST_NAME_LEN)
    private String lastName;

    private Date birthDate;

    @Column(name = "email", length = EMAIL_LEN)
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
