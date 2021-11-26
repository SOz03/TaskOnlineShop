package ru.i.sys.labs.messages.sender;

import ru.i.sys.labs.exception.ResourceNotFoundException;

public interface NotificationSender {

    void sendMessage() throws ResourceNotFoundException;
}
