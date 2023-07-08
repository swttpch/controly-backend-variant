package controly.backend.services;

import controly.backend.dto.*;
import controly.backend.entities.UserEntity;
import controly.backend.exceptions.EmailAlreadyExistsException;
import controly.backend.exceptions.NicknameAlreadyExistsException;
import controly.backend.exceptions.UserAlreadyDisabledException;
import controly.backend.exceptions.UsersIdNotFould;
import controly.backend.mappers.UserMapper;
import controly.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;
  private final SecureRandom secureRandom = new SecureRandom(); //threadsafe
  private final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  @Transactional
  public UserResponse createNewUser(CreateNewUserRequest newUser) {
    Optional<UserEntity> optionalUser = userRepository.findByEmail(newUser.getEmail());
    if (optionalUser.isPresent()) throw new EmailAlreadyExistsException();
    UserEntity user =
      UserEntity.builder()
        .createdAt(new Date())
        .updatedAt(new Date())
        .isActive(true)
        .build();
    userMapper.createUserFromDto(newUser, user);
    System.out.println(user.getEmail());
    userRepository.save(user);
    return getUserResponse(user);
  }

  @Transactional
  public UserEntity getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(UsersIdNotFould::new);
  }

  @Transactional
  public List<UserEntity> getListOfUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public UserResponse disableUserById(Long id) {
    UserEntity user = this.getUserById(id);
    if (!user.getIsActive()) throw new UserAlreadyDisabledException();
    user.setIsActive(false);
    user.setDisabledIn(new Date());
    user.setUpdatedAt(new Date());
    return getUserResponse(user);
  }

  @Transactional
  public UserResponse reactivateUserById(Long id) {
    UserEntity user = this.getUserById(id);
    if (user.getIsActive()) throw new UserAlreadyDisabledException();
    user.setIsActive(true);
    user.setDisabledIn(null);
    user.setUpdatedAt(new Date());
    return getUserResponse(user);
  }


  public UserResponse updateUsersInfo(Long id, UpdateUsersInfoRequest form) {
    UserEntity user = this.getUserById(id);
    userMapper.updateUserFromDto(form, user);
    if(userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException();
    }
    if (userRepository.findByNickname(user.getNickname()).isPresent()) {
      throw new NicknameAlreadyExistsException();
    }
    user.setUpdatedAt(new Date());
    userRepository.save(user);
    return getUserResponse(user);
  }

  public void verifyIfEmailExists(String email) {
    Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
  }

  public SimplifiedUserResponse getSimplifiedUser(UserEntity user) {
    SimplifiedUserResponse simplifiedUser = new SimplifiedUserResponse();
    userMapper.getSimplifiedDtoFromUser(user, simplifiedUser);
    return simplifiedUser;
  }

  @Transactional
  public UserResponse getUserResponse(UserEntity user) {
    UserResponse userResponse = new UserResponse();
    userMapper.getResponseDtoFromUser(user, userResponse);
    return userResponse;
  }

  public String generateNewToken() {
    byte[] randomBytes = new byte[15];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}
