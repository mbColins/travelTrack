package traveltrack.mapper;

import traveltrack.dto.request.UserRequestDTO;
import traveltrack.dto.response.UserResponseDTO;
import traveltrack.models.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // ── Entity → ResponseDTO ────────────────────────────────────────────────
    public UserResponseDTO toResponseDTO(User entity) {
        if (entity == null) return null;

        return UserResponseDTO.builder()
                .id(entity.getId())
                .id(entity.getId())
                .uuid(entity.getUuid())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .password(entity.getPassword())
                .country(entity.getCountry())
                .neighbourhood(entity.getNeighbourhood())
                .build();
    }

    // ── RequestDTO → Entity ─────────────────────────────────────────────────
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .uuid(dto.getUuid())
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .country(dto.getCountry())
                .neighbourhood(dto.getNeighbourhood())
                .build();
        // Note: relations are handled in the Service
        // to avoid circular dependencies between Mappers.
    }

    // ── List Entity → List ResponseDTO ────────────────────────────────────
    public List<UserResponseDTO> toResponseDTOList(List<User> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}