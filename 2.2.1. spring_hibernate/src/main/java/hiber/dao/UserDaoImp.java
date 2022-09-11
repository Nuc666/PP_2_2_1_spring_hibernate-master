package hiber.dao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public void clearDB() {
      String hql = "DELETE FROM User";

      sessionFactory
              .getCurrentSession()
              .createQuery(hql)
              .executeUpdate();
      String hql2 = "DELETE FROM Car";

      sessionFactory
              .getCurrentSession()
              .createQuery(hql2)
              .executeUpdate();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModel(String model, int series) {
      String hql = "FROM User WHERE car.model = :model AND car.series = :series";

      return (User) sessionFactory
              .getCurrentSession()
              .createQuery(hql)
              .setParameter("model",model)
              .setParameter("series",series)
              .getSingleResult();
   }



}
