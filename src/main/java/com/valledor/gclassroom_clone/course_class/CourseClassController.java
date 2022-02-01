package com.valledor.gclassroom_clone.course_class;

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
@RequestMapping(value = "/api/courseClasss", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseClassController {

    private final CourseClassService courseClassService;

    public CourseClassController(final CourseClassService courseClassService) {
        this.courseClassService = courseClassService;
    }

    @GetMapping
    public ResponseEntity<List<CourseClassDTO>> getAllCourseClasss() {
        return ResponseEntity.ok(courseClassService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseClassDTO> getCourseClass(@PathVariable final Long id) {
        return ResponseEntity.ok(courseClassService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCourseClass(
            @RequestBody @Valid final CourseClassDTO courseClassDTO) {
        return new ResponseEntity<>(courseClassService.create(courseClassDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourseClass(@PathVariable final Long id,
            @RequestBody @Valid final CourseClassDTO courseClassDTO) {
        courseClassService.update(id, courseClassDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseClass(@PathVariable final Long id) {
        courseClassService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
