package com.oldiee.CatalogOnline;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/student/{studentId}")
    public List<Nota>getNotesById(@PathVariable Long studentId){
        return notaRepository.findByStudentId(studentId);
    }
    @PostMapping("/student/{studentId}")
    public  ResponseEntity<Nota> addNota(@PathVariable Long studentId,@Valid @RequestBody Nota nota){
        return  studentRepository.findById(studentId)
                .map(student -> {
                    nota.setStudent(student);
                    Nota savedNota=notaRepository.save(nota);
                    return  ResponseEntity.status(HttpStatus.CREATED).body(savedNota);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteNota(@PathVariable Long id){
        if(notaRepository.existsById(id)){
        notaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
