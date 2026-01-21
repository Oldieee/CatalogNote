package com.oldiee.CatalogOnline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes")
@CrossOrigin(origins = "http://localhost:4200")
public class NotaController {
    private final NotaRepository notaRepository;
    private  final  StudentRepository studentRepository;
    public NotaController(NotaRepository notaRepository,StudentRepository studentRepository){
        this.notaRepository=notaRepository;
        this.studentRepository=studentRepository;
    }
    @GetMapping
    public List<Nota>getAllNotes(){
        return notaRepository.findAll();
    }
    @GetMapping("/students/{studentId}")
    public List<Nota>getNotesById(@PathVariable Long studentId){
        return notaRepository.findByStudentId(studentId);
    }
    @PostMapping("/{studentId}/note")
    public Nota addNota(@PathVariable Long studentId, @RequestBody Nota nota) {
        return studentRepository.findById(studentId).map(student -> {
            nota.setStudent(student);
            return notaRepository.save(nota);
        }).orElseThrow(() -> new RuntimeException("Studentul nu exista!"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteNota(@PathVariable Long id){
        notaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
