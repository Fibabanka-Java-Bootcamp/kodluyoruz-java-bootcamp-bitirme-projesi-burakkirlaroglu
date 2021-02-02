package org.kodluyoruz.mybank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.kodluyoruz.mybank.controller.CustomerController;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
        import static org.hamcrest.Matchers.containsString;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;

//spring boot test db h2
    @SpringBootTest
    @AutoConfigureMockMvc
    public class CustomerControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CustomerService customerService;

        @Test
        public void shouldReturnDefaultMessage() throws Exception {
            this.mockMvc.perform(get("/v1/api/customer")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello, World")));
        }

        @Test
        public void customerCreatedStatus() throws Exception {

        CustomerDto customerDto = new CustomerDto();

        this.mockMvc.perform(post("/v1/api/customer").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(asJsonString(customerDto))).
                andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    }

