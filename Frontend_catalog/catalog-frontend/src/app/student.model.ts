export interface Student {
  id?:number;
  nume:string;
  prenume:string;
  email:string;
  note?:Nota[];
}
export interface Nota {
  id:number;
 nota:number;
  disciplina:string;
}