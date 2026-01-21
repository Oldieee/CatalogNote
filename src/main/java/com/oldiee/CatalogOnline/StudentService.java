package com.oldiee.CatalogOnline;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
private   final StudentRepository studentRepository;
public  StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
}
    public double calculeazaMedia(Long studentId){
        Student student=studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student negăsit"));
        return student.getNote().stream()
                .mapToInt(Nota::getNota)
                .average()
                .orElse(0.0);
    }
}

