package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = StudentController.class)
@ComponentScan(basePackages = "com.example.studentapi.controller")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() throws Exception {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(new Student()));

        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", studentId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student(); // Set student details as needed

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"John Doe\" }")) // Set content as needed
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testUpdateStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student(); // Set updated student details as needed

        mockMvc.perform(MockMvcRequestBuilders.put("/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Updated Name\" }")) // Set content as needed
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/students/{id}", studentId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}