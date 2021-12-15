package net.guides.springboot2.springboot2jpacrudexample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import net.guides.springboot2.springboot2jpacrudexample.controller.EmployeeController;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;




@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployees() throws Exception{
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee.Builder().withId(1l).withFirstName("Juan").withLastName("Perez").build());
        when(employeeRepository.findAll()).thenReturn(employees);
        this.mvc.perform(get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Juan")));
    }


    @Test
    public void testGetEmployeeNotExists() throws Exception {
        when(employeeRepository.findAll()).thenReturn(null);
        this.mvc.perform(get("/api/v1/employees/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    


}