package br.com.challenge.controller.dto;

import br.com.challenge.controller.enums.NotificationType;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NotificationDto {

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private String subscription;

    private List<EventHistoryDto> events;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public List<EventHistoryDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventHistoryDto> events) {
        this.events = events;
    }
}
