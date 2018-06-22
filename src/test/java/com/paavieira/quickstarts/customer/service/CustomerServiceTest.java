package com.paavieira.quickstarts.customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.persistence.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    private CustomerService service;

    @Before
    public void setUp() throws Exception {
        this.service = new CustomerService(repository);
    }

    @Test
	public void getSingle_ShouldReturnCustomer() throws Exception {
		given(repository. findById("1"))
            .willReturn(Optional.of(new Customer("Karl", "Marx")));
            
        Customer customer = service.findById("1");

        assertThat(customer.getFirstName()).isEqualTo("Karl");
        assertThat(customer.getLastName()).isEqualTo("Marx");
	}

}