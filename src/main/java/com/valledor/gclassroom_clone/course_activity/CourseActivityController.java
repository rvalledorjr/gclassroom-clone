package com.valledor.gclassroom_clone.course_activity;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/courseActivitys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseActivityController {

    private final CourseActivityService courseActivityService;

    public CourseActivityController(final CourseActivityService courseActivityService) {
        this.courseActivityService = courseActivityService;
    }

    @GetMapping
    public ResponseEntity<List<CourseActivityDTO>> getAllCourseActivitys() {
        return ResponseEntity.ok(courseActivityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseActivityDTO> getCourseActivity(@PathVariable final Long id) {
        return ResponseEntity.ok(courseActivityService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCourseActivity(
            @RequestBody @Valid final CourseActivityDTO courseActivityDTO) {
        return new ResponseEntity<>(courseActivityService.create(courseActivityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourseActivity(@PathVariable final Long id,
            @RequestBody @Valid final CourseActivityDTO courseActivityDTO) {
        courseActivityService.update(id, courseActivityDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseActivity(@PathVariable final Long id) {
        courseActivityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
