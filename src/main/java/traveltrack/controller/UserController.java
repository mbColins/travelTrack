//package traveltrack.controller;
//
//import traveltrack.dto.request.UserRequestDTO;
//import traveltrack.dto.response.UserResponseDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//
//    // ── GET //api/v1/users ─────────────────────────────────────────
//
//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> findAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    // ── GET //api/v1/users/{id} ──────────────────────────────────
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.findById(id));
//    }
//
//    // ── POST //api/v1/users ────────────────────────────────────────
//
//    @PostMapping
//    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(userService.create(dto));
//    }
//
//    // ── PUT //api/v1/users/{id} ──────────────────────────────────
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> update(
//            @PathVariable Long id,
//            @RequestBody UserRequestDTO dto
//    ) {
//        return ResponseEntity.ok(userService.update(id, dto));
//    }
//
//    // ── DELETE //api/v1/users/{id} ───────────────────────────────
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}