package br.com.challenge.service;

import br.com.challenge.service.converter.SubscriptionConverter;
import br.com.challenge.controller.dto.NotificationDto;
import br.com.challenge.domain.model.EventHistory;
import br.com.challenge.domain.model.Subscription;
import br.com.challenge.domain.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionConverter subscriptionConverter;

    public NotificationService(final SubscriptionRepository subscriptionRepository,
                               final SubscriptionConverter subscriptionConverter) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionConverter = subscriptionConverter;
    }

    public void process(NotificationDto notificationDto) {

        LOGGER.info("stage=init method=NotificationService.process "
                + "Message=Starting Save or Update Subscription uuid={}", notificationDto.getSubscription());

        Optional<Subscription> subscriptionFound = subscriptionRepository.
                findById(notificationDto.getSubscription());

        if (subscriptionFound.isPresent()) {
            update(notificationDto, subscriptionFound.get());
            return;
        }

        save(notificationDto);

        LOGGER.info("stage=init method=NotificationService.process "
                + "Message=Finishing Save or Update Subscription uuid={}", notificationDto.getSubscription());

    }

    private void save(NotificationDto notificationDto) {

        LOGGER.info("stage=init method=NotificationService.save "
                + "Message=Starting Save Subscription uuid={}", notificationDto.getSubscription());

        Subscription subscription = subscriptionConverter.apply(notificationDto);

        subscriptionRepository.save(subscription);

        LOGGER.info("stage=init method=NotificationService.save "
                + "Message=Starting Save Subscription uuid={}", notificationDto.getSubscription());
    }

    private void update(NotificationDto notificationDto, Subscription subscriptionFound) {

        LOGGER.info("stage=init method=NotificationService.update "
                + "Message=Starting Update Subscription uuid={}", notificationDto.getSubscription());

        subscriptionFound.addEvent(new EventHistory(subscriptionFound, notificationDto.getNotificationType()));
        subscriptionFound.setStatusByTypeNotification(notificationDto.getNotificationType());

        subscriptionRepository.save(subscriptionFound);

        LOGGER.info("stage=init method=NotificationService.update "
                + "Message=Starting Update Subscription uuid={}", notificationDto.getSubscription());
    }
}
