package org.example;

public class MVCPattern {
    public static void main(String[] args) {

        Student model = retreiveFromDatabase();
        StudentView studentView = new StudentView();
        StudentController controller = new StudentController(model,studentView);

        controller.updateview();
        controller.setStudentName("Linet Mwithi");
        controller.updateview();
    }

    public static Student retreiveFromDatabase() {
        Student student = new Student();
        student.setName("Michelle Mimi");
        student.setRollNo("12345");
        return student;
    }
}