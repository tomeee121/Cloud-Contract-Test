package TB.StubContractApi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private CustomerRepo customerRepo;

    @GetMapping("/customers")
    public Collection<Customer> getCustomers(){
        return customerRepo.findAll();
    }

}

