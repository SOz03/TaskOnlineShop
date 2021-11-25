package ru.i.sys.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.i.sys.labs.dto.CustomerDTO;
import ru.i.sys.labs.entity.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {

    @Query("select c from Customer c where c.phoneNumber = :phoneNumber")
    Optional<Customer> getCustomerByPhoneNumber(@Param("phoneNumber") String phone);
}
