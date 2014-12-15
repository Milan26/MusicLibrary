package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.List;

/**
 * @author Martin
 */
public interface UserService {

    /**
     * Insert given userDto.
     *
     * @param userDto userDto to be inserted
     */
    void createUser(UserDto userDto);

    /**
     * Delete user by given id.
     *
     * @param id identification of user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Update given userDto.
     *
     * @param userDto userDto to be updated
     */
    void updateUser(UserDto userDto);

    /**
     * Get user by given id.
     *
     * @param id identification of user to be found
     * @return found user
     */
    UserDto findUser(Long id);

    /**
     * Get all users.
     *
     * @return list of all users
     */
    List<UserDto> getAllUsers();

}
