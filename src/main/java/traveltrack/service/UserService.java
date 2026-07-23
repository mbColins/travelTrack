package traveltrack.service;

import traveltrack.dto.request.UserRequestDTO;
import traveltrack.dto.response.UserResponseDTO;
import traveltrack.models.entity.User;
import traveltrack.exception.UserNotFoundException;
import traveltrack.mapper.UserMapper;
import traveltrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    // ── findAll ──────────────────────────────────────────────────────────────


    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    // ── findById ─────────────────────────────────────────────────────────────


    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toResponseDTO(entity);
    }

    // ── create ───────────────────────────────────────────────────────────────

    public UserResponseDTO create(UserRequestDTO dto) {
        User entity = userMapper.toEntity(dto);
        _resolveRelations(entity, dto);
        return userMapper.toResponseDTO(userRepository.save(entity));
    }

    // ── update ───────────────────────────────────────────────────────────────

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existing.setId(dto.getId());
        existing.setUuid(dto.getUuid());
        existing.setUsername(dto.getUsername());
        existing.setFirstname(dto.getFirstname());
        existing.setLastname(dto.getLastname());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setPassword(dto.getPassword());
        existing.setCountry(dto.getCountry());
        existing.setNeighbourhood(dto.getNeighbourhood());
        _resolveRelations(existing, dto);
        return userMapper.toResponseDTO(userRepository.save(existing));
    }

    // ── delete ───────────────────────────────────────────────────────────────


    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    // ── relation resolution ─────────────────────────────────────────────

    private void _resolveRelations(User entity, UserRequestDTO dto) {
        // No relations to resolve for this entity.
    }

    public User loadUserByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("user.not.found"));
    }
}