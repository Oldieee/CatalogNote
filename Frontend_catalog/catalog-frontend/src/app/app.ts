
import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StudentService } from './student.service';
import { Nota, Student } from './student.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent implements OnInit {
  listaStudenti: Student[] = [];
  seIncarca: boolean = false;
  studentNou: Student = { nume: '', prenume: '', email: '' } as Student;
  studentSelectat:Student | null=null;
  notaNoua:Nota={nota:0,disciplina:''}as Nota;

 
  constructor(
    private studentService: StudentService,
    
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.refreshData();
  }
selecteazaStudent(student:Student){
  this.studentSelectat=student;
  this.notaNoua={nota:0,disciplina:''}as Nota;
  this.cdr.detectChanges();}

  salveazaNota() {
    if (!this.studentSelectat?.id || !this.notaNoua.nota) return;
    
    this.seIncarca = true;
    this.cdr.detectChanges();
    this.studentService.addNota(this.studentSelectat.id, this.notaNoua).subscribe({
      next: (notaSalvata) => {
      
        this.listaStudenti = this.listaStudenti.map(s => {
          if (s.id === this.studentSelectat?.id) {
           
            const noteNoi = s.note ? [...s.note, notaSalvata] : [notaSalvata];
            return { ...s, note: noteNoi };
          }
          return s;
        });

      
        this.studentSelectat = null; 
        this.notaNoua = { nota: 0, disciplina: '' } as Nota;
        this.seIncarca = false;
        this.cdr.detectChanges(); 
        console.log("Notă adăugată instant!");
      },
      error: (err) => {
        console.error("Eroare la serverul Java:", err);
        this.seIncarca = false;
        this.cdr.detectChanges();
      }
    });
  }


  refreshData() {
    this.studentService.getStudents().subscribe({
      next: (date) => {
        this.listaStudenti = date;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  adaugaStudent() {
    if (!this.studentNou.nume.trim()) return;
    this.seIncarca = true;
    this.cdr.detectChanges(); 

    this.studentService.addStudent(this.studentNou).subscribe({
      next: (studentSalvat) => {
        this.listaStudenti.push(studentSalvat);
        this.studentNou = { nume: '', prenume: '', email: '' } as Student;
        this.seIncarca = false;
        
       
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error(err);
        this.seIncarca = false;
        this.cdr.detectChanges();
      }
    });
  }

  stergeStudent(id: number) {
    this.studentService.deleteStudent(id).subscribe({
      next: () => {
        this.listaStudenti = this.listaStudenti.filter(s => s.id !== id);
        this.cdr.detectChanges(); 
      }
    });
  }
}