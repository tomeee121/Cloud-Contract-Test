package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

import java.net.http.HttpHeaders

Contract.make {
    description "should return all customers"

    request {
        method GET()
        url "/customers"
    }

    response {
        status 200
        headers {
            header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        }
        body ([[id: 1L, name: "Jane"], [id: 2L, name: "Bob"]])
    }
}
