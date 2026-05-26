package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiCourseController {
    //@Autowired // dependency - injection
    CourseService courseService;

    //@Autowired
    StudentService studentService;

   public DiCourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("get-course")
    public String getCourse(){
        return courseService.getCourse();
    }

    @GetMapping("/get-student")
    public String getStudent() {
        return studentService.getStudent();
    }
}
