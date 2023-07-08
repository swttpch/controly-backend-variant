package controly.backend.controllers;


import controly.backend.dto.CreateNewUserRequest;
import controly.backend.dto.UpdateUsersInfoRequest;
import controly.backend.dto.UserResponse;
import controly.backend.entities.UserEntity;
import controly.backend.mappers.UserMapper;
import controly.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
      UserResponse response = userService.getUserResponse(userService.getUserById(id));
      return ResponseEntity.status(200).body(response);
    }
    @GetMapping
    public ResponseEntity<List<UserEntity>> getListOfUsers() {
        List<UserEntity> users =  userService.getListOfUsers();
        return users.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewUser(@RequestBody @Valid CreateNewUserRequest user) {
        UserResponse newUser = userService.createNewUser(user);
        return ResponseEntity.status(201).body(newUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long id) {
        UserResponse user = userService.disableUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/reactivate/{id}")
    public ResponseEntity<UserResponse> reactiviteUserById(@PathVariable Long id) {
        UserResponse user = userService.reactivateUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUsersInfo(@PathVariable Long id,
                                                  @RequestBody @Valid UpdateUsersInfoRequest form)  {
        UserResponse user = userService.updateUsersInfo(id, form);
        return ResponseEntity.status(200).body(user);
    }

//    @PostMapping("/password-recovery")
//    public ResponseEntity<?> passwordRecovery(@RequestBody PasswordRecoveryRequest form) {
//        passwordRecoveryService.passwordRecovery(form);
//        return ResponseEntity.status(200).body("New password sent to users e-mail.");
//    }


//    @GetMapping("/email/{email}")
//    public ResponseEntity<Integer> verifyIfEmailExists(@PathVariable String email){
//        return ResponseEntity.status(200).body(userService.verifyIfEmailExists(email));
//    }

//    @GetMapping("/profile/{id}")
//    public ResponseEntity<UserProfileResponse> getProfileByUserId(@PathVariable long id) {
//        UserProfileResponse user = profileService.getUserProfile(id);
//        return ResponseEntity.status(200).body(user);
//    }

}
