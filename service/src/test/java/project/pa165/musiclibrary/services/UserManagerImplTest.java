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
import project.pa165.musiclibrary.exception.PersistenceException;
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
public class UserManagerImplTest {

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserManagerImpl userManager;

    public UserManagerImpl getUserManager() {
        return userManager;
    }

    @Before
    public void setup() throws PersistenceException {
        MockitoAnnotations.initMocks(this);

        userManager.setDozerBeanMapper(new DozerBeanMapper());

        user1 = createUser(1l, "martinzahuta@gmail.com", "Martin", "Zahuta", "8+VV6L076X@1{<n", "canEdit");
        user2 = createUser(2l, "john@doe.com", "John", "Doe", "q1NF=1e5A.-B7qv", "canView");

        userDto1 = createUserDto(1l, "martinzahuta@gmail.com", "Martin", "Zahuta", "8+VV6L076X@1{<n", "canEdit");
        userDto2 = createUserDto(2l, "john@doe.com", "John", "Doe", "q1NF=1e5A.-B7qv", "canView");

        when(userDao.find(1l)).thenReturn(user1);
        when(userDao.find(2l)).thenReturn(user2);
        when(userDao.find(3l)).thenReturn(null);
        when(userDao.getAll()).thenReturn(Arrays.asList(user1, user2));
    }

    @Test
    public void testCreateUser() throws PersistenceException, ServiceException {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        getUserManager().createUser(userDto1);
        verify(userDao).create(argumentCaptor.capture());
        deepAssert(user1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testCreateNullUser() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("user")).when(userDao).create(null);
        userManager.createUser(null);
        verify(userDao).create(null);
    }

    @Test
    public void testDeleteUser() throws PersistenceException, ServiceException {
        getUserManager().deleteUser(1l);
        verify(userDao).delete(1l);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNullUser() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("id")).when(userDao).delete(null);
        userManager.deleteUser(null);
        verify(userDao).delete(null);
    }

    @Test
    public void testUpdateUser() throws PersistenceException, ServiceException {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        getUserManager().updateUser(userDto1);
        verify(userDao).update(argumentCaptor.capture());
        deepAssert(user1, argumentCaptor.getValue());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateNullUser() throws PersistenceException, ServiceException {
        doThrow(new PersistenceException("user")).when(userDao).update(null);
        userManager.updateUser(null);
        verify(userDao).update(null);
    }

    @Test
    public void testFindUser() throws PersistenceException, ServiceException {
        UserDto result = getUserManager().findUser(1l);
        verify(userDao).find(1l);
        deepAssert(userDto1, result);
    }

    @Test
    public void testFindUserOnEmptyDb() throws PersistenceException, ServiceException {
        when(userDao.find(any(Long.class))).thenReturn(null);
        UserDto result = getUserManager().findUser(1l);
        verify(userDao).find(1l);
        assertNull(result);
    }

    @Test
    public void testFindUserByWrongId() throws PersistenceException, ServiceException {
        UserDto result = getUserManager().findUser(3l);
        verify(userDao).find(3l);
        assertNull(result);
    }

    @Test
    public void testGetAllUsers() throws PersistenceException, ServiceException {
        List<UserDto> allUsers = Arrays.asList(userDto1, userDto2);
        List<UserDto> result = getUserManager().getAllUsers();
        verify(userDao).getAll();
        assertEquals(2, result.size());
        deepAssert(allUsers.toArray(), result.toArray());
    }

    @Test
    public void testGetAllUsersOnEmptyDb() throws PersistenceException, ServiceException {
        when(userDao.getAll()).thenReturn(new ArrayList<User>());
        List<UserDto> result = getUserManager().getAllUsers();
        verify(userDao).getAll();
        assertEquals(0, result.size());
    }

    private User createUser(Long id, String email, String firstName, String lastName, String password, String role) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    private UserDto createUserDto(Long id, String email, String firstName, String lastName, String password,
                                  String role) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setPassword(password);
        userDto.setRole(role);
        return userDto;
    }

    private void deepAssert(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getRole(), user2.getRole());
    }

    private void deepAssert(UserDto userDto1, UserDto userDto2) {
        assertEquals(userDto1.getId(), userDto2.getId());
        assertEquals(userDto1.getEmail(), userDto2.getEmail());
        assertEquals(userDto1.getFirstName(), userDto2.getFirstName());
        assertEquals(userDto1.getLastName(), userDto2.getLastName());
        assertEquals(userDto1.getPassword(), userDto2.getPassword());
        assertEquals(userDto1.getRole(), userDto2.getRole());
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
