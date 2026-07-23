package traveltrack.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemConfigRequestDTO {

    private Long id;
    private Long maxAuthAttempt;

}