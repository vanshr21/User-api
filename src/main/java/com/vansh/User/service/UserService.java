package com.vansh.User.service;

import com.vansh.User.dto.*;
import com.vansh.User.exception.DuplicateEmailException;
import com.vansh.User.exception.EmptyDataSetException;
import com.vansh.User.exception.UserNotFoundException;
import com.vansh.User.model.User;
import com.vansh.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createDtoToModel(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setName(createUserRequestDTO.getName());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setAge(createUserRequestDTO.getAge());
        user.setCountry(createUserRequestDTO.getCountry());
        user.setDeleted(false);

        return user;
    }

    private CreateUserResponseDTO createModelToDto(User user) {
        CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO();
        createUserResponseDTO.setId(user.getId());
        createUserResponseDTO.setName(user.getName());
        createUserResponseDTO.setEmail(user.getEmail());
        createUserResponseDTO.setAge(user.getAge());
        createUserResponseDTO.setCountry(user.getCountry());
        createUserResponseDTO.setMessage("User created Successfully");

        return createUserResponseDTO;
    }

    private UpdateUserResponseDTO UpdateModelToDto(User user) {
        UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO();
        updateUserResponseDTO.setId(user.getId());
        updateUserResponseDTO.setName(user.getName());
        updateUserResponseDTO.setEmail(user.getEmail());
        updateUserResponseDTO.setCountry(user.getCountry());
        updateUserResponseDTO.setAge(user.getAge());

        return updateUserResponseDTO;
    }

    private GetUserResponseDTO GetModelToDto(User user) {
        GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
        getUserResponseDTO.setId(user.getId());
        getUserResponseDTO.setName(user.getName());
        getUserResponseDTO.setAge(user.getAge());
        getUserResponseDTO.setEmail(user.getEmail());
        getUserResponseDTO.setCountry(user.getCountry());
        return getUserResponseDTO;
    }

    private User UpdateDtoToModel(UpdateUserRequestDTO updateUserRequestDTO) {
        User user = new User();
        user.setName(updateUserRequestDTO.getName());
        user.setAge(updateUserRequestDTO.getAge());
        user.setCountry(updateUserRequestDTO.getCountry());

        return user;
    }

    private boolean validEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        User createdUser = createDtoToModel(createUserRequestDTO);
        if(validEmail(createdUser.getEmail())) throw new DuplicateEmailException(createdUser.getEmail() + " already exists!!!");
        return createModelToDto(userRepository.save(createdUser));
    }

    public GetUserResponseDTO getUser(Integer id) {
        User get = userRepository.findUserByIdAndIsDeletedFalse(id)
                                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        return GetModelToDto(get);

    }

    public List<GetUserResponseDTO> getUsers() {
        List<User> gets = userRepository.findUsersByIsDeletedFalse();
        if(gets.isEmpty()) throw new EmptyDataSetException("Data set is empty");

        return gets.stream().map(this::GetModelToDto).toList();
    }

    public UpdateUserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(updateUserRequestDTO.getName());
        user.setAge(updateUserRequestDTO.getAge());
        user.setCountry(updateUserRequestDTO.getCountry());
        User updateUser = userRepository.save(user);
        return UpdateModelToDto(updateUser);
    }

    public void softDeleteUser(Integer id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("No such user found"));
        user.setDeleted(true);
        userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("No such user found"));
        userRepository.deleteById(id);
    }


}
