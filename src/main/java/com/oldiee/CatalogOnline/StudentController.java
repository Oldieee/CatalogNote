package com.oldiee.CatalogOnline;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/note/{notaId}")
    public String deleteNota(@PathVariable Long notaId){
        if(notaRepository.existsById(notaId)){
            notaRepository.deleteById(notaId);
            return "Nota cu ID-ul " + notaId + " a fost ștearsă cu succes! ";
        }else{
            return "Eroare: Nota cu ID-ul " + notaId + " nu există. ";
        }
    }
    @GetMapping("/{studentId}/note")
    public List<Nota>getToateNotele(@PathVariable Long studentId){
        return  notaRepository.findAll();
    }

    @PostMapping("/{studentId}/note")
    public Nota addNota(@PathVariable Long studentId, @RequestBody Nota nota) {
        return studentRepository.findById(studentId).map(student -> {
            nota.setStudent(student);
            return notaRepository.save(nota);
        }).orElseThrow(() -> new RuntimeException("Studentul cu ID-ul " + studentId + " nu a fost găsit!"));
    }


    @GetMapping("/{studentId}/media")
    public double getMedieStudent(@PathVariable Long studentId) {
        return studentService.calculeazaMedia(studentId);
    }
}