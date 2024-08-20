package kafkademo.notificationservice.model;

import lombok.Data;

@Data
public class MessageData {
    private String subject;
    private String text;
    //TODO: add all required fields
}
