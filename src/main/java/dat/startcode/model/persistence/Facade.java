package dat.startcode.model.persistence;

import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.BoM;

import java.util.ArrayList;


public class Facade
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

    public static int getOrderPrice(int orderId,ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getOrderPrice(orderId);
    }

    public static ArrayList<Order> getOrderDataForUser(User user, ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getOrderDataForUser(user);
    }

    public static void updatePrice(int orderId, int price, ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        orderMapper.updatePrice(orderId,price);
    }

    public static void deleteOrder(int orderId, ConnectionPool connectionPool){
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        orderMapper.deleteOrder(orderId);
    }



    /**BoMMapper**/

    public static void saveBomData(String bom, ConnectionPool connectionPool){
        BoMMapper boMMapper = new BoMMapper(connectionPool);
        boMMapper.saveBomData(bom);
    }

    public static ArrayList<BoMItems> getBomData(int orderId, int totalLength, int width, ConnectionPool connectionPool){
        System.out.println("Goddag");
        BoMMapper boMMapper = new BoMMapper(connectionPool);
        ArrayList boMItems = boMMapper.getBomData(orderId,totalLength,width,connectionPool);
        return boMItems;
    }






}
