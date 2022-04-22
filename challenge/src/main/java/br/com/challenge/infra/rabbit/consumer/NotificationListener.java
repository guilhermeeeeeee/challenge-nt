package br.com.challenge.infra.rabbit.consumer;

import br.com.challenge.controller.dto.NotificationDto;
import br.com.challenge.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {


    @Autowired
    private NotificationService notificationService;

    public NotificationListener(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String notificationMessage) throws JsonProcessingException {

        System.out.println("Message " + notificationMessage);

        ObjectMapper objectMapper = new ObjectMapper();

        var notificationDto = objectMapper.readValue(notificationMessage, NotificationDto.class);

        notificationService.process(notificationDto);

    }

}
