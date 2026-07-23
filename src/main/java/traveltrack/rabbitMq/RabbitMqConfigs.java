package traveltrack.rabbitMq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import traveltrack.models.enums.Queues;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMqConfigs {

    public static final String EXCHANGE = "notificationExchange";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Declarables notificationQueues(DirectExchange exchange) {
        List<Declarable> declarables = new ArrayList<>();

        for (Queues queue : Queues.values()) {
            Queue amqpQueue = new Queue(queue.getDescription(), true);
            declarables.add(amqpQueue);
            declarables.add(BindingBuilder.bind(amqpQueue).to(exchange).with(queue.getDescription()));
        }

        return new Declarables(declarables);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);

        return rabbitTemplate;
    }
}
