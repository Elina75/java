package Service;

import Repository.UserRepository;
import Repository.newUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.List;


@Stateless
public class UserServices {
    @EJB
    UserRepository UserRepository;

    public List<Integer> ageUser() throws SQLException, ClassNotFoundException {
        System.out.println("service is working");
        return UserRepository.getAgeUser();
    }


    public String addUser(newUser user) throws SQLException, ClassNotFoundException {
        return UserRepository.addUser(user);
    }

    public List<newUser> getAllUsers()throws SQLException, ClassNotFoundException {
        return UserRepository.getAllUser();
    }

    public String updateUserById(newUser user) throws SQLException, ClassNotFoundException {
        return UserRepository.updateUserNameById(user);
    }


    public String deleteUserNameById(newUser user) throws SQLException, ClassNotFoundException {
        return UserRepository.deleteUserNameById(user);
    }

    public newUser getUserById(newUser user) throws SQLException, ClassNotFoundException {
        return UserRepository.getUserById(user);
    }

}
