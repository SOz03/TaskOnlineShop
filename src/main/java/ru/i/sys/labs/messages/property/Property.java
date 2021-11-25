package ru.i.sys.labs.messages.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Property {

    private boolean enabled;
    private String how;

}
