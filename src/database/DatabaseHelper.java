package database;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

/**
 * Created by Olcha on 20.06.2016.
 */
public class DatabaseHelper {
    private static SessionFactory dbSessions;

    //Создаём новую сессию для работы с БД
    private void createSessionFactory(){
        dbSessions = new AnnotationConfiguration()
                .configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(History.class)
                .buildSessionFactory();
    }

    //Проверяем наличие пользователя в БД
    public String checkUser(String login, String password){
        if(dbSessions == null)
            createSessionFactory();
        Session session = dbSessions.openSession();

        try {
            Query query = session.createQuery("FROM users WHERE login = :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List usersList = query.list();
            if(usersList.size() == 0){ //Проверка на размер выборки(Если 0, то значит такой логин и пароль отсутствует в БД)
                return "Простите, но ваш логин или пароль введён неверно. Пожалуйста, проверьте корректность вводимых данных";
            }
            User user = (User) usersList.iterator().next();
            return "Добро пожаловать " + user.getLogin() + " " + user.getPassword();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        //Этот return придётся оставить, чтобы метод работал.
        return "Простите, но ваш логин или пароль введён неверно. Пожалуйста, проверьте корректность вводимых данных";
    }

    public void insertRequest(String reqNumber, String date){
        if (dbSessions == null)
            createSessionFactory();
        Session session = dbSessions.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            History table = new History();
            table.setDate(date);
            table.setRequestNumber(reqNumber);
            session.save(table);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
