package TB.StubContractApi.customerservice;

import TB.StubContractApi.Customer;
import TB.StubContractApi.CustomerRepo;
import TB.StubContractApi.CustomerRestController;
import TB.StubContractApi.StubContractApiApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = StubContractApiApplication.class)
public class BaseContractClass {
    @Autowired
    CustomerRestController restController;

    @MockBean
    private CustomerRepo customerRepo;

    @Before
    public void setUp(){
        when(customerRepo.findAll()).thenReturn(Arrays.asList(new Customer(1L, "Jane"), new Customer(2L, "Bob")));
        RestAssuredMockMvc.standaloneSetup(restController);
    }
}
