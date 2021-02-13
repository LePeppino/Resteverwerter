package de.prog3.proj2021.repositories;

/*
 * This class interacts with the User DAO
 * to retrieve and cache users from database
 * to pass them to a ViewModel
 *
 * File author: Giuseppe Buccellato
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

    //constructor
    public UserRepository(Application application){
        AppDatabase userDB = AppDatabase.getInstance(application);
        userDao = userDB.userDao();
        updateUsers();
    }

    //getter and setter for userDataSet
    public LiveData<List<User>> getUsers() {return userDataSet;}

    private void updateUsers(){
        userDataSet = userDao.getUsers();
    }

    /*
    * database operations communicating with UserDao
    * */
    public void insert(User user){
        AppDatabase.databaseExecutor.execute(() -> {
            userDao.insertUser(user);
        });
        System.out.println("user inserted");
    }
    public void update(User user){
        AppDatabase.databaseExecutor.execute(() -> {
            userDao.updateUser(user);
        });
        System.out.println("user updated");
    }
    public void delete(User user){
        AppDatabase.databaseExecutor.execute(() -> {
            userDao.deleteUser(user);
        });
        System.out.println("user deleted");
    }

    /*
    * getters for different queries here
    * */
    public LiveData<User> getUserById(int userId){
        return userDao.getUserById(userId);
    }

}
