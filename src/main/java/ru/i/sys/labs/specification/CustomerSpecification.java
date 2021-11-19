package ru.i.sys.labs.specification;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import ru.i.sys.labs.entity.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class CustomerSpecification {

    public static Specification<Customer> customerHasBirthday(final Date dateBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        String today = "%" + dateFormat.format(dateBirth);

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("dateBirth").as(String.class), today);
    }
}
