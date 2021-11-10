package Service;

import Repository.Product;
import Repository.ProductRepository;
import Repository.UserRepository;
import Repository.newUser;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.List;


@Stateless
public class ProductService {
    @EJB
    ProductRepository productRepository;

    public List<Product> getProductAmount() throws SQLException, ClassNotFoundException {
        return productRepository.getAllProductAmount();
    }


    public String addProduct(Product product) throws SQLException, ClassNotFoundException {
        return productRepository.addProduct(product);
    }

    public List<Product> getAllProducts()throws SQLException, ClassNotFoundException {
        return productRepository.getAllProducts();
    }

    public String updateProductById(Product product) throws SQLException, ClassNotFoundException {
        return productRepository.updateProductById(product);
    }


    public String deleteProductById(Product product) throws SQLException, ClassNotFoundException {
        return productRepository.deleteProductById(product);
    }
//
//    public newUser getUserById(newUser user) throws SQLException, ClassNotFoundException {
//        return UserRepository.getUserById(user);
//    }

}
