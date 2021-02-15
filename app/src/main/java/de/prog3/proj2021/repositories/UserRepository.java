package de.prog3.proj2021.repositories;

/**
 * This class interacts with the User DAO
 * to retrieve and cache users from database
 * to pass them to a ViewModel
 * NOT CURRENTLY IN USE
 *
 * @author Giuseppe Buccellato
 * */

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.UserDao;
import de.prog3.proj2021.models.User;

public class UserRepository {

    private LiveData<List<User>> userDataSet;
    private final UserDao userDao;

    /**
     * constructor initialises Database instance and DAO interface
     * to update local data cache.
     * @param application passed from ViewModel to get Database Instance
     */
    public UserRepository(Application application){
        AppDatabase userDB = AppDatabase.getInstance(application);
        userDao = userDB.userDao();
        updateUsers();
    }

    /**
     * returns live data of list of users
     * @return live data
     */
    public LiveData<List<User>> getUsers() {return userDataSet;}

    private void updateUsers(){
        userDataSet = userDao.getUsers();
    }

    /**
     * inserts user
     */
    public void insert(User user){
        userDao.insertUser(user);
        System.out.println("user inserted");
    }
    /**
     * updates user
     */
    public void update(User user){
        userDao.insertUser(user);
        System.out.println("user updated");
    }

    /**
     * deletes user
     */
    public void delete(User user){
        userDao.deleteUser(user);
        System.out.println("user deleted");
    }

    /**
     * getter for queries
     * @return live data
     */
    public LiveData<List<User>> getUserById(int userId){
        return userDataSet = userDao.getUserById(userId);
    }

}
