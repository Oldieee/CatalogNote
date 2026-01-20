package com.oldiee.CatalogOnline;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    private String  prenume;
    private String email;
    private String password;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Nota>note=new ArrayList<>();

    public Student(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public  String getPassword(){
    return  password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }
}
