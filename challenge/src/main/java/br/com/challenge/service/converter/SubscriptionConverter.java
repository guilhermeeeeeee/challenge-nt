package br.com.challenge.service.converter;

import br.com.challenge.controller.dto.NotificationDto;
import br.com.challenge.domain.model.EventHistory;
import br.com.challenge.domain.model.Subscription;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SubscriptionConverter implements Function<NotificationDto, Subscription> {

    private final EventHistoryConverter eventHistoryConverter;

    public SubscriptionConverter(final EventHistoryConverter eventHistoryConverter) {
        this.eventHistoryConverter = eventHistoryConverter;
    }

    @Override
    public Subscription apply(NotificationDto notificationDto) {

        var subscription = new Subscription();

        subscription.setId(notificationDto.getSubscription());
        subscription.addEvent(new EventHistory(subscription, notificationDto.getNotificationType()));
        subscription.setStatusByTypeNotification(notificationDto.getNotificationType());

        return subscription;
    }
}
