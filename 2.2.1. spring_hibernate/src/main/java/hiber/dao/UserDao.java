package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   void clearDB();
   List<User> listUsers();

   User getUserByModel(String model, int series);
}
