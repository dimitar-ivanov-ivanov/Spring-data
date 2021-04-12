package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents();

        // then
        //check if this was called with this method
        //we don't want to test the repository when we are testing the service
        //the unit test is now faster
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        //given
        String email = "pesho@gmail.com";
        Student student = new Student(
                "Pesho",
                email,
                Gender.MALE
        );

        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent)
                .isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        String email = "pesho@gmail.com";
        Student student = new Student(
                "Pesho",
                email,
                Gender.MALE
        );

        //always returns that the email exists
        given(studentRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() ->
                underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "Email " + student.getEmail() + " taken"
                );

        //never saves any student
        verify(studentRepository, never())
                .save(any());
    }


    @Test
    @Disabled
    void deleteStudent() {
    }
}