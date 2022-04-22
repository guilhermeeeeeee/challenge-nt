package br.com.challenge.controller;

import br.com.challenge.controller.dto.NotificationDto;
import br.com.challenge.infra.rabbit.producer.NotificationPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationPublisher notificationPublisher;

    @PostMapping
    public ResponseEntity send(@RequestBody @Valid NotificationDto notificationDto) {

        String json = converterObjectToJson(notificationDto);

        notificationPublisher.send(json);

        return ResponseEntity.ok().build();
    }

    public String converterObjectToJson(Object object) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            return ow.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
