package traveltrack.mapper;

import traveltrack.dto.request.SystemConfigRequestDTO;
import traveltrack.dto.response.SystemConfigResponseDTO;
import traveltrack.models.entity.SystemConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SystemConfigMapper {

    // ── Entity → ResponseDTO ────────────────────────────────────────────────
    public SystemConfigResponseDTO toResponseDTO(SystemConfig entity) {
        if (entity == null) return null;

        return SystemConfigResponseDTO.builder()
                .id(entity.getId())
                .id(entity.getId())
                .maxAuthAttempt(entity.getMaxAuthAttempt())
                .build();
    }

    // ── RequestDTO → Entity ─────────────────────────────────────────────────
    public SystemConfig toEntity(SystemConfigRequestDTO dto) {
        if (dto == null) return null;

        return SystemConfig.builder()
                .id(dto.getId())
                .maxAuthAttempt(dto.getMaxAuthAttempt())
                .build();
        // Note: relations are handled in the Service
        // to avoid circular dependencies between Mappers.
    }

    // ── List Entity → List ResponseDTO ────────────────────────────────────
    public List<SystemConfigResponseDTO> toResponseDTOList(List<SystemConfig> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}