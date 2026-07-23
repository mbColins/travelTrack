package traveltrack.rabbitMq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import traveltrack.models.entity.NotificationEvent;
import traveltrack.models.enums.Queues;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RabbitTemplate rabbitTemplate;

    @Async
    public void sendNotification(NotificationEvent event, Queues queues) {
        rabbitTemplate.convertAndSend(RabbitMqConfigs.EXCHANGE, queues.getDescription(), event, message -> {
            message.getMessageProperties().setContentType("application/json");
            return message;
        });
    }
}
