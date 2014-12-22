package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.entities.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserManager
 *
 * @author Martin
 */
@Named
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private DozerBeanMapper dozerBeanMapper;

    public UserDao getUserDao() {
        return userDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Inject
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createUser(final UserDto userDto) {
        User user = null;
        if (userDto != null) user = getDozerBeanMapper().map(userDto, User.class);
        getUserDao().create(user);
    }

    @Override
    public void deleteUser(final Long id) {
        getUserDao().delete(id);
    }

    @Override
    public void updateUser(final UserDto userDto) {
        User user = null;
        if (userDto != null) user = getDozerBeanMapper().map(userDto, User.class);
        getUserDao().update(user);
    }

    @Override
    public UserDto findUser(final Long id) {
        User user = getUserDao().find(id);
        return user != null ? getDozerBeanMapper().map(user, UserDto.class) : null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return getMappedUserDtoCollection(getUserDao().getAll());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = getUserDao().findUserByEmail(email);
        return user != null ? getDozerBeanMapper().map(user, UserDto.class) : null;
    }

    private List<UserDto> getMappedUserDtoCollection(List<User> users) {
        List<UserDto> mappedCollection = new ArrayList<>();
        for (User user : users) {
            mappedCollection.add(getDozerBeanMapper().map(user, UserDto.class));
        }
        return mappedCollection;
    }
}
