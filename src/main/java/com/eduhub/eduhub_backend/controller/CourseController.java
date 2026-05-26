package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/courses")
@RestController
public class CourseController {
    static List<Course> courseList = new ArrayList<>();

    static {
        courseList.add(new Course("C101", "DSA", 3));
        courseList.add(new Course("C102", "Java", 4));
        courseList.add(new Course("E103", "DSP", 4));
        courseList.add(new Course("E104", "ControlSystem", 4));
        courseList.add(new Course("M105", "Maths", 3));
    }

    @GetMapping("/get-courses")
    public ResponseEntity<List<Course>>  getAllCourses(){
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/get-course/{code}")
    public ResponseEntity<Course> getCourse(@PathVariable String code){
        return courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(code))
                .findFirst().map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseCode", code));
    }

    // http://localhost:8080/courses/search/get-course?courseCode=C102
    @GetMapping("/search/get-course")
    public ResponseEntity<Course> searchCourse(@RequestParam String courseCode){
        return courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst().map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseCode", courseCode));
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseList.add(course);
        return ResponseEntity.ok(course);
    }
    // http://localhost:8080/courses/update/M102
    // {
    //    "courseCode":"M102",
    //    "courseName":"Probability",
    //    "credit":4
    //}

    @PutMapping("/update/{code}")
    public ResponseEntity<Course> updateCourse(@PathVariable String code, @RequestBody Course updatedCourse) {
        Course course = courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseCode", code));
        course.setCourseName(updatedCourse.getCourseName());
        course.setCredit(updatedCourse.getCredit());

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete-course/{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable String code){
        Course course = courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseCode", code));
        courseList.remove(course);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/query/{code}")
    public String queryCourse(@PathVariable String code) throws Exception {
        if(code.startsWith("*")) {
            throw new IllegalArgumentException("It is having a special character");
        }
        else if(code.startsWith("6")) {
            throw new RuntimeException();
        }
        return code;
    }
}
