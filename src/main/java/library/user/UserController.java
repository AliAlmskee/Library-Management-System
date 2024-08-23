package library.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patrons")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getUser(id));
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UpdateUserRequest request) {
//        service.updateUser(id, request);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {

        service.deleteUser(id);
    }

}
