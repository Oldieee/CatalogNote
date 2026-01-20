package com.oldiee.CatalogOnline;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final  NotaRepository notaRepository;

    public StudentController(StudentRepository studentRepository,NotaRepository notaRepository) {
        this.studentRepository = studentRepository;
        this.notaRepository=notaRepository;

    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);

    }
    @PostMapping("/{studentId}/note")
    public Nota addNota(@PathVariable Long studentId,@RequestBody Nota nota){
        return  studentRepository.findById(studentId).map(student -> {
            nota.setStudent(student);
            return notaRepository.save(nota);
        }).orElseThrow(() -> new RuntimeException("Studentul cu ID-ul " + studentId + " nu a fost găsit!"));
    }
}
