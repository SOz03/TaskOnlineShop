package ru.i.sys.labs.messages.sender;

import ru.i.sys.labs.exception.ResourceNotFoundException;

public interface Sender {

    void sendMessage() throws ResourceNotFoundException;
}
