import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Student,Nota } from "./student.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private url = 'http://localhost:8080/api/students';

  constructor(private http: HttpClient) {}

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.url);
  }


  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.url, student);
  }


  deleteStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
  addNota(studentId:number,nota:Nota):Observable<Nota>{
    return this.http.post<Nota>(`${this.url}/${studentId}/note`,nota);
}
}