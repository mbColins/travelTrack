package traveltrack.controller;

import traveltrack.dto.request.SystemConfigRequestDTO;
import traveltrack.dto.response.SystemConfigResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/systemconfigs")
@RequiredArgsConstructor
public class SystemConfigController {

    private final SystemConfigService systemConfigService;


    // ── GET //api/v1/systemconfigs ─────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<SystemConfigResponseDTO>> findAll() {
        return ResponseEntity.ok(systemConfigService.findAll());
    }

    // ── GET //api/v1/systemconfigs/{id} ──────────────────────────────────

    @GetMapping("/{id}")
    public ResponseEntity<SystemConfigResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(systemConfigService.findById(id));
    }

    // ── POST //api/v1/systemconfigs ────────────────────────────────────────

    @PostMapping
    public ResponseEntity<SystemConfigResponseDTO> create(@RequestBody SystemConfigRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(systemConfigService.create(dto));
    }

    // ── PUT //api/v1/systemconfigs/{id} ──────────────────────────────────

    @PutMapping("/{id}")
    public ResponseEntity<SystemConfigResponseDTO> update(
            @PathVariable Long id,
            @RequestBody SystemConfigRequestDTO dto
    ) {
        return ResponseEntity.ok(systemConfigService.update(id, dto));
    }

    // ── DELETE //api/v1/systemconfigs/{id} ───────────────────────────────

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        systemConfigService.delete(id);
        return ResponseEntity.noContent().build();
    }

}