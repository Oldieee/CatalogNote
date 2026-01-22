package com.oldiee.CatalogOnline;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;

    private final StudentService studentService;


    public StudentController(StudentRepository studentRepository,

                             StudentService studentService) {
        this.studentRepository = studentRepository;

        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student>students=studentRepository.findAll();
        return  ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student>getStudentById(@PathVariable Long id){
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid  @RequestBody Student student) {
        Student savedStudent=studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





    @GetMapping("/{studentId}/media")
    public ResponseEntity<Double> getMedieStudent(@PathVariable Long studentId) {
        try{
            double medie=studentService.calculeazaMedia(studentId);
            return ResponseEntity.ok(medie);
        }catch (RuntimeException e){
            return  ResponseEntity.notFound().build();
        }
    }
}