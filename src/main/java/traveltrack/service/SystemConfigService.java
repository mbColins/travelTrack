package traveltrack.service;

import traveltrack.dto.request.SystemConfigRequestDTO;
import traveltrack.dto.response.SystemConfigResponseDTO;
import traveltrack.models.entity.SystemConfig;
import traveltrack.exception.SystemConfigNotFoundException;
import traveltrack.mapper.SystemConfigMapper;
import traveltrack.repository.SystemConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemConfigService  {

    private final SystemConfigRepository systemConfigRepository;

    public SystemConfig getSystemConfiguration(){
        return systemConfigRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new SystemConfigNotFoundException("No SystemConfig found"));
    }
}