package com.oldiee.CatalogOnline;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final NotaRepository notaRepository;
    private final StudentService studentService;


    public StudentController(StudentRepository studentRepository,
                             NotaRepository notaRepository,
                             StudentService studentService) {
        this.studentRepository = studentRepository;
        this.notaRepository = notaRepository;
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);

    }
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
    studentRepository.deleteById(studentId);
    }





    @GetMapping("/{studentId}/media")
    public double getMedieStudent(@PathVariable Long studentId) {
        return studentService.calculeazaMedia(studentId);
    }
}