package ru.i.sys.labs.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private UUID id;
    private String FIO;
    private String phoneNumber;
    private Date dateBirth;
    private String address;
}
