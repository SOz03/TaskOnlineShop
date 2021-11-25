package ru.i.sys.labs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;


@AllArgsConstructor
@ToString
@Setter
@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    protected UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

}
