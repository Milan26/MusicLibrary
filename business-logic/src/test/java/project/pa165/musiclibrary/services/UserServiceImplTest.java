package project.pa165.musiclibrary.services;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
* @author Martin
*/
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceImpl getUserService() {
        return userService;
    }

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        userService.setDozerBeanMapper(new DozerBeanMapper());

        user1 = createUser(1l, "martinzahuta@gmail.com", "Martin", "Zahuta", "password", true);
        user2 = createUser(2l, "john@doe.com", "John", "Doe", "123456", true);

        userDto1 = createUserDto(1l, "martinzahuta@gmail.com", "Martin", "Zahuta", "password", true);
        userDto2 = createUserDto(2l, "john@doe.com", "John", "Doe", "123456", true);

        when(userDao.find(1l)).thenReturn(user1);
        when(userDao.find(2l)).thenReturn(user2);
        when(userDao.find(3l)).thenReturn(null);
        when(userDao.getAll()).thenReturn(Arrays.asList(user1, user2));
    }

    @Test
    public void testCreateUser() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        getUserService().createUser(userDto1);
        verify(userDao).create(argumentCaptor.capture());
        deepAssert(user1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateNullUser() {
        doThrow(new ServiceException("user")).when(userDao).create(null);
        userService.createUser(null);
        verify(userDao).create(null);
    }

    @Test
    public void testDeleteUser() {
        getUserService().deleteUser(1l);
        verify(userDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNullUser() {
        doThrow(new ServiceException("id")).when(userDao).delete(null);
        userService.deleteUser(null);
        verify(userDao).delete(null);
    }

    @Test
    public void testUpdateUser() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        getUserService().updateUser(userDto1);
        verify(userDao).update(argumentCaptor.capture());
        deepAssert(user1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateNullUser() {
        doThrow(new ServiceException("user")).when(userDao).update(null);
        userService.updateUser(null);
        verify(userDao).update(null);
    }

    @Test
    public void testFindUser() {
        UserDto result = getUserService().findUser(1l);
        verify(userDao).find(1l);
        deepAssert(userDto1, result);
    }

    @Test
    public void testFindUserOnEmptyDb() {
        when(userDao.find(any(Long.class))).thenReturn(null);
        UserDto result = getUserService().findUser(1l);
        verify(userDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindUserByWrongId() {
        UserDto result = getUserService().findUser(3l);
        verify(userDao).find(3l);
        assertNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindUserWithNullId() {
        doThrow(new ServiceException("id")).when(userDao).find(null);
        getUserService().findUser(null);
        verify(userDao).find(null);
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> allUsers = Arrays.asList(userDto1, userDto2);
        List<UserDto> result = getUserService().getAllUsers();
        verify(userDao).getAll();
        assertEquals(2, result.size());
        deepAssert(allUsers.toArray(), result.toArray());
    }

    @Test
    public void testGetAllUsersOnEmptyDb() {
        when(userDao.getAll()).thenReturn(new ArrayList<User>());
        List<UserDto> result = getUserService().getAllUsers();
        verify(userDao).getAll();
        assertEquals(0, result.size());
    }

    private User createUser(Long id, String email, String firstName, String lastName, String password,
                            Boolean enabled) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEnabled(enabled);
        return user;
    }

    private UserDto createUserDto(Long id, String email, String firstName, String lastName, String password,
                                  Boolean enabled) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setPassword(password);
        userDto.setEnabled(enabled);
        return userDto;
    }

    private void deepAssert(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getEnabled(), user2.getEnabled());
    }

    private void deepAssert(UserDto userDto1, UserDto userDto2) {
        assertEquals(userDto1.getId(), userDto2.getId());
        assertEquals(userDto1.getEmail(), userDto2.getEmail());
        assertEquals(userDto1.getFirstName(), userDto2.getFirstName());
        assertEquals(userDto1.getLastName(), userDto2.getLastName());
        assertEquals(userDto1.getEnabled(), userDto2.getEnabled());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            UserDto userDto1 = (UserDto) arr1[i];
            UserDto userDto2 = (UserDto) arr2[i];
            deepAssert(userDto1, userDto2);
        }
    }
}
