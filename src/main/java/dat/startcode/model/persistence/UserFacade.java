package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.ArrayList;


public class UserFacade
{

    /**UserMapper**/
    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.login(username, password);
    }

    public static void createUser(String name, String email, String address, int zipcode, String password,int phone, int role, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);
        userMapper.createUser(name, email, address,zipcode,password,phone,role);
    }

    public static void createUserTest(ConnectionPool connectionPool){
        UserMapper userMapper = new UserMapper(connectionPool);
        try {
            userMapper.createUser("Zack Ottesen","zo@pyra.dk", "Sømoseparken 80", 2750,"Hej",30329013,1);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }




    /**OrderMapper**/

    public static int createOrder(Carport carport, Shed shed, User user, ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.createOrder(carport,shed,user);
    }

    public static ArrayList<Order> getOrderDataForAdmin(ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getOrderDataForAdmin();
    }




}
