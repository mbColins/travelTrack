package traveltrack.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import traveltrack.models.enums.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private static final long serialVersionUID = 1L;

    private String destination;
    private Events event;
    private List<Object> payloads = new ArrayList<>();

    public void addPayLoad(Object object){
        this.payloads.add(object);
    }

    public String getBody(String template) {

        if (this.payloads.isEmpty()) {
            return template;
        }
        for (Object payload : this.payloads) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> replacements = objectMapper.convertValue(payload, Map.class);

            // Convert all values to Strings (to avoid issues with numbers, booleans, etc.)
            Map<String, String> stringReplacements = replacements.entrySet().stream()
                    .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));

            for (Map.Entry<String, String> entry : stringReplacements.entrySet()) {
                template = template.replace("%" + entry.getKey() + "%", entry.getValue());
            }
        }
        return template;
    }
}
