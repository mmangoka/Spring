package org.example;

public class StudentController {

    private Student student;
    private StudentView view;

    public StudentController(Student student, StudentView view){
        this.student = student;
        this.view = view;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudentName(String name){
        student.setName(name);
    }

    public void setStudentRollNo(String  rollNo){
        student.setRollNo(rollNo);
    }

    public String getStudentRollNo(){
        return student.getRollNo();
    }


    public String getStudentName(){
        return student.getName();
    }


    public void updateview(){
        view.printStudentDetails(student.getName(),student.getRollNo());
    }
}
