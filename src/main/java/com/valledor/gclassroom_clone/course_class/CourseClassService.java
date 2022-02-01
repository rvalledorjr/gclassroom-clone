package com.valledor.gclassroom_clone.course_class;

import com.valledor.gclassroom_clone.user.User;
import com.valledor.gclassroom_clone.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CourseClassService {

    private final CourseClassRepository courseClassRepository;
    private final UserRepository userRepository;

    public CourseClassService(final CourseClassRepository courseClassRepository,
            final UserRepository userRepository) {
        this.courseClassRepository = courseClassRepository;
        this.userRepository = userRepository;
    }

    public List<CourseClassDTO> findAll() {
        return courseClassRepository.findAll()
                .stream()
                .map(courseClass -> mapToDTO(courseClass, new CourseClassDTO()))
                .collect(Collectors.toList());
    }

    public CourseClassDTO get(final Long id) {
        return courseClassRepository.findById(id)
                .map(courseClass -> mapToDTO(courseClass, new CourseClassDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CourseClassDTO courseClassDTO) {
        final CourseClass courseClass = new CourseClass();
        mapToEntity(courseClassDTO, courseClass);
        return courseClassRepository.save(courseClass).getId();
    }

    public void update(final Long id, final CourseClassDTO courseClassDTO) {
        final CourseClass courseClass = courseClassRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(courseClassDTO, courseClass);
        courseClassRepository.save(courseClass);
    }

    public void delete(final Long id) {
        courseClassRepository.deleteById(id);
    }

    private CourseClassDTO mapToDTO(final CourseClass courseClass,
            final CourseClassDTO courseClassDTO) {
        courseClassDTO.setId(courseClass.getId());
        courseClassDTO.setName(courseClass.getName());
        courseClassDTO.setSection(courseClass.getSection());
        courseClassDTO.setSubject(courseClass.getSubject());
        courseClassDTO.setRoom(courseClass.getRoom());
        courseClassDTO.setBannerImage(courseClass.getBannerImage());
        courseClassDTO.setArchived(courseClass.getArchived());
        courseClassDTO.setTeacher(courseClass.getTeacher() == null ? null : courseClass.getTeacher().getId());
        return courseClassDTO;
    }

    private CourseClass mapToEntity(final CourseClassDTO courseClassDTO,
            final CourseClass courseClass) {
        courseClass.setName(courseClassDTO.getName());
        courseClass.setSection(courseClassDTO.getSection());
        courseClass.setSubject(courseClassDTO.getSubject());
        courseClass.setRoom(courseClassDTO.getRoom());
        courseClass.setBannerImage(courseClassDTO.getBannerImage());
        courseClass.setArchived(courseClassDTO.getArchived());
        if (courseClassDTO.getTeacher() != null && (courseClass.getTeacher() == null || !courseClass.getTeacher().getId().equals(courseClassDTO.getTeacher()))) {
            final User teacher = userRepository.findById(courseClassDTO.getTeacher())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "teacher not found"));
            courseClass.setTeacher(teacher);
        }
        return courseClass;
    }

}
