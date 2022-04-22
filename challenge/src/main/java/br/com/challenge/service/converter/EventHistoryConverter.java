package br.com.challenge.service.converter;

import br.com.challenge.controller.dto.EventHistoryDto;
import br.com.challenge.controller.dto.NotificationDto;
import br.com.challenge.domain.model.EventHistory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class EventHistoryConverter implements Function<NotificationDto, List<EventHistory>> {

    @Override
    public List<EventHistory> apply(NotificationDto notificationDto) {

        List<EventHistoryDto> eventHistories = notificationDto.getEvents();
        var events = new ArrayList<EventHistory>();
        if(eventHistories == null){
            eventHistories = new ArrayList<>();
        }
//        if (!eventHistories.isEmpty()) {
            eventHistories.forEach(eventHistory -> {
                var event = new EventHistory();
                event.setType(notificationDto.getNotificationType().toString());
                events.add(event);
            });
//        }
        return events;
    }
}
