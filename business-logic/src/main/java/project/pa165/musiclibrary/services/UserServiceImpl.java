package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserManager
 *
 * @author Martin
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private DozerBeanMapper dozerBeanMapper;

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    @Autowired
    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void createUser(final UserDto userDto) throws ServiceException {
        User user = null;
        if (userDto != null) user = getDozerBeanMapper().map(userDto, User.class);
        try {
            getUserDao().create(user);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteUser(final Long id) throws ServiceException {
        try {
            getUserDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateUser(final UserDto userDto) throws ServiceException {
        User user = null;
        if (userDto != null) user = getDozerBeanMapper().map(userDto, User.class);
        try {
            getUserDao().update(user);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public UserDto findUser(final Long id) throws ServiceException {
        User user = null;
        try {
            user = getUserDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return user != null ? getDozerBeanMapper().map(user, UserDto.class) : null;
    }

    @Override
    public List<UserDto> getAllUsers() throws ServiceException {
        List<User> allUsers = null;
        try {
            allUsers = getUserDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }

        return getMappedUserDtoCollection(allUsers);
    }

    private List<UserDto> getMappedUserDtoCollection(List<User> users) {
        List<UserDto> mappedCollection = new ArrayList<>();
        for (User user : users) {
            mappedCollection.add(getDozerBeanMapper().map(user, UserDto.class));
        }
        return mappedCollection;
    }
}
