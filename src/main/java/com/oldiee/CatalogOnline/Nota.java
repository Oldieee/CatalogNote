package com.oldiee.CatalogOnline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank(message = "Disciplina este obligatorie")
    @Size(min = 3, message = "Numele disciplinei este prea scurt")
    private String disciplina;
    @Min(value=1, message="Nota minima este 1")
    @Max(value=10,message="Nota maxima este 10")
    private int nota;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Nota() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
