package TB.StubContractApi.customerservice;

import TB.StubContractApi.Customer;
import TB.StubContractApi.CustomerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CustomerRestControllerTest {

    @MockBean
    private CustomerRepo customerRepo;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllCustomers() throws Exception {
        when(customerRepo.findAll()).thenReturn(Arrays.asList(new Customer(1L, "Jane"), new Customer(2L, "Bob")));
        String json = getJsonListOfCustomers(new Customer(1L, "Jane"), new Customer(2L, "Bob"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(json))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Jane"));

    }

    String getJsonListOfCustomers(Customer ... customers) throws JsonProcessingException {
        return objectMapper.writeValueAsString(Arrays.asList(customers));
    }
}
