package Controller;

import Repository.Product;
import Repository.newUser;
import Service.ProductService;
import Service.UserServices;

import javax.ejb.EJB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import Repository.Student;

import java.sql.SQLException;
import java.util.List;


@Path("/hello-world")
public class HelloResource {
    @EJB
    UserServices userServices;

    @EJB
    ProductService productService;



    @GET
    @Produces("application/json")
    public List<Student> hello() {
        return Student.getStudents();
    }

    @GET
    @Path("/age")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getAge() throws SQLException, ClassNotFoundException {
        return userServices.ageUser();
    }

    @POST
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(newUser user)throws SQLException, ClassNotFoundException {
        userServices.addUser(user);
        return "User added";
    }

    @GET
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<newUser> getAllUsers()throws SQLException, ClassNotFoundException {
        return userServices.getAllUsers();
    }

    @PUT
    @Path("/updateUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUserById(newUser user)throws SQLException, ClassNotFoundException {
        userServices.updateUserById(user);
        return "User info with this id was updated";
    }

    @DELETE
    @Path("/deleteUserNameById")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUserNameById(newUser user)throws SQLException, ClassNotFoundException {
        userServices.deleteUserNameById(user);
        return "User info with this id was deleted";
    }

    @GET
    @Path("/getUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public newUser getUserById(newUser user)throws SQLException, ClassNotFoundException {
       return userServices.getUserById(user);
    }

    @GET
    @Path("/getProductAmount")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductAmount()throws SQLException, ClassNotFoundException {
        return productService.getProductAmount();
    }

    @GET
    @Path("/getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts()throws SQLException, ClassNotFoundException {
        return productService.getAllProducts();
    }

    @PUT
    @Path("/updateProductById")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateProductById(Product product)throws SQLException, ClassNotFoundException {
        productService.updateProductById(product);
        return "Product info with this id was updated";
    }

    @POST
    @Path("/addProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addProduct(Product product)throws SQLException, ClassNotFoundException {
        productService.addProduct(product);
        return "Product added";
    }

    @DELETE
    @Path("/deleteProductById")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteProductById(Product product)throws SQLException, ClassNotFoundException {
        productService.deleteProductById(product);
        return "Product info with this id was deleted";
    }
    }
