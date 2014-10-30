package project.pa165.musiclibrary.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.DaoException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Martin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoImplTest {

    private User user1;
    private User user2;

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Before
    public void setUp() {
        user1 = createUser("Alain", "Doe", "alain@doe.com", "pAssword");
        user2 = createUser("Marco", "Polo", "marco@polo.com", "12345");
    }

    @Test
    public void testCreateUser() throws DaoException {
        persist(user1);
    }

    @Test
    public void testCreatedUserProperties() throws DaoException {
        persist(user1);

        User actual = getUserDao().find(user1.getId());
        deepAssert(user1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testCreateUserNull() throws DaoException {
        getUserDao().create(null);
    }

    @Test
    public void testDeleteUser() throws DaoException {
        persist(user1);

        getUserDao().delete(user1.getId());
        assertNull(getUserDao().find(user1.getId()));
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteUserNull() throws DaoException {
        getUserDao().delete(null);
    }

    @Test
    public void testUpdateUser() throws DaoException {
        persist(user1);

        User actual = getUserDao().find(user1.getId());
        assertNotEquals("Delon", actual.getLastName());
        assertNotEquals("alain@delon", actual.getEmail());

        user1.setLastName("Delon");
        user1.setEmail("alain@delon");

        getUserDao().update(user1);
        actual = getUserDao().find(user1.getId());

        deepAssert(user1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateUserNull() throws DaoException {
        getUserDao().update(null);
    }

    @Test
    public void testFindUser() throws DaoException {
        persist(user1);

        User actual = getUserDao().find(user1.getId());
        assertNotNull(actual);
        assertEquals(user1, actual);
    }

    @Test(expected = DataAccessException.class)
    public void testFindUserWithNullId() throws DaoException {
        getUserDao().find(user1.getId());
    }

    @Test
    public void testFindUserWithWrongId() throws DaoException {
        assertNull(getUserDao().find(5l));
    }

    @Test
    public void testGetAllUsers() throws DaoException {
        persist(user1);
        persist(user2);

        List<User> expected = Arrays.asList(user1, user2);
        assertEquals(expected.size(), 2);
        List<User> actual = getUserDao().getAll();
        assertEquals(actual.size(), 2);

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }

    @Test
    public void testGetAllAlbumsEmptyDb() throws DaoException {
        List<User> actual = getUserDao().getAll();
        assertEquals(actual.size(), 0);
    }

    private void persist(User user) throws DaoException {
        assertNull(user.getId());
        getUserDao().create(user);
        assertNotNull(user.getId());
    }

    private User createUser(String firstName, String secondName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    private void deepAssert(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            User user1 = (User) arr1[i];
            User user2 = (User) arr2[i];
            deepAssert(user1, user2);
        }
    }
}