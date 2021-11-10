import Repository.Product;
import Repository.UserRepository;
import Repository.newUser;
import Service.ProductService;
import Service.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import Controller.HelloResource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Tests {

    @Test
    public void testRestResult() throws SQLException, ClassNotFoundException {
        HelloResource HelloResource = Mockito.mock(HelloResource.class);

       // UserServices userServices = Mockito.mock(UserServices.class);
        UserServices userServices =new UserServices();
        ProductService productService=new ProductService();
        UserRepository userRepository=new UserRepository();

        List<Integer> age = userServices.ageUser();
        Mockito.when(HelloResource.getAge()).thenReturn(age);
        assertEquals(HelloResource.getAge(), userServices.ageUser());
        verify(HelloResource).getAge();

//        List<Integer> age1 = userRepository.getAgeUser();
//        Mockito.when(userServices.ageUser()).thenReturn(age1);
//        assertEquals(userServices.ageUser(), userRepository.getAgeUser());
//        verify(userServices,atLeastOnce()).ageUser();

        newUser user = new newUser();
        user.setId(10);
        user.setFirst("Polina");
        user.setLast("Gagarina");
        user.setAge(36);
        Mockito.when(HelloResource.addUser(user)).thenReturn(user.toString());
        assertEquals(HelloResource.addUser(user), user.toString());
        verify(HelloResource).addUser(user);

//        Mockito.when(userServices.addUser(user)).thenReturn(user.toString());
//        assertEquals(userServices.addUser(user), user.toString());
//        verify(userServices).addUser(user);

        List<newUser> newUser = userServices.getAllUsers();
        Mockito.when(HelloResource.getAllUsers()).thenReturn(newUser);
        assertEquals(HelloResource.getAllUsers(), userServices.getAllUsers());
        verify(HelloResource).getAllUsers();

//        Mockito.when(userServices.getAllUsers()).thenReturn(UserRepository.getAllUser());
//        assertEquals(userServices.getAllUsers(), userRepository.getAllUser());
//        verify(userServices,atLeastOnce()).getAllUsers();


        Mockito.when(HelloResource.updateUserById(user)).thenReturn("User name is updated");
        assertEquals(HelloResource.updateUserById(user), userServices.updateUserById(user));
        verify(HelloResource).updateUserById(user);

        Mockito.when(HelloResource.deleteUserNameById(user)).thenReturn("User is deleted");
        assertEquals(HelloResource.deleteUserNameById(user), userServices.deleteUserNameById(user));
        verify(HelloResource).deleteUserNameById(user);

        newUser user1 = new newUser();
        user.setId(2);
        user.setFirst("Julia");
        user.setLast("Martin");
        user.setAge(45);
        Mockito.when(HelloResource.getUserById(user1)).thenReturn(user1);
        assertEquals(HelloResource.getUserById(user1), userServices.getUserById(user1));
        verify(HelloResource).getUserById(user1);

        Mockito.when(HelloResource.getUserByAge(user1)).thenReturn(user1);
        assertEquals(HelloResource.getUserByAge(user1), userServices.getUserByAge(user1));
        verify(HelloResource).getUserByAge(user1);

        Mockito.when(HelloResource.getUserByFirstname(user)).thenReturn(user);
        assertEquals(HelloResource.getUserByFirstname(user), user);
        verify(HelloResource).getUserByFirstname(user);

        List<Product> products = productService.getAllProducts();
        Mockito.when(HelloResource.getAllProducts()).thenReturn(products);
        assertEquals(HelloResource.getAllProducts(), productService.getAllProducts());
        verify(HelloResource).getAllProducts();

        List<Product> products1 = productService.getProductAmount();
        Mockito.when(HelloResource.getProductAmount()).thenReturn(products1);
        assertEquals(HelloResource.getProductAmount(), productService.getProductAmount());
        verify(HelloResource).getProductAmount();

        Product product = new Product();
        product.setId(100);
        product.setName("Sweeties");
        product.setDescription("cool");
        product.setPrice(3600.0);
        product.setAmount(270);
        Mockito.when(HelloResource.addProduct(product)).thenReturn(product.toString());
        assertEquals(HelloResource.addProduct(product), product.toString());
        verify(HelloResource).addProduct(product);

    }
}
