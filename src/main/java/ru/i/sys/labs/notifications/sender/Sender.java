package ru.i.sys.labs.notifications.sender;

public interface Sender {

    void sendNotification();

    boolean checkingFilterIsEnabled();
}
