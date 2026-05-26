package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Ram", "KS");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //http://localhost:8080/students
    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Ram", "KS"));
        studentList.add(new Student(2, "Ravi", "Kumar"));
        studentList.add(new Student(3, "Anu", "Priya"));
        studentList.add(new Student(4, "Abi", "Pal"));
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    //http://localhost:8080/5/priya/ram
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                      @PathVariable("first-name") String firstName,
                                      @PathVariable("last-name") String lastName
                                      )
    {
        Student student = new Student(studentId, firstName, lastName);
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/query?Id=1&firstName=Ram&lastName=Kumar
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int Id,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(Id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // http:localhost:8080/1/update
    @PutMapping("{id}/update")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId,
                                        @RequestBody Student student) {
        return ResponseEntity.accepted().body(student);
    }

    // http:localhost:8080/1/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId
                                        ) {
        return ResponseEntity.accepted().body("Data removed successfully!");
    }
}
