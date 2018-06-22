
package com.paavieira.quickstarts.customer.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.paavieira.quickstarts.customer.model.Customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void findByName_ShouldReturnCustomer() throws Exception {
        Customer customer = repository.findByFirstName("Lionel");
        assertThat(customer.getFirstName()).isEqualTo("Lionel");
        assertThat(customer.getLastName()).isEqualTo("Messi");
    }

}