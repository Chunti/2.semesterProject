package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {
    ConnectionPool connectionPool;

    protected OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    protected int createOrder(Carport carport, Shed shed, User user) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "insert into carport.order (length, width, material, userId, shedIncluded) values(?,?,?,?,?)";

        int shedIncluded = 1;
        if (shed == null) shedIncluded = 0;

        int maxRows = 0;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, carport.getLength());
                ps.setInt(2, carport.getWidth());
                ps.setString(3, carport.getMaterial());
                ps.setInt(4, user.getUserId());
                ps.setInt(5, shedIncluded);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Carport data is saved");
                } else {
                    throw new DatabaseException("Could not save Carport data");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        maxRows = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

            }
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }

        if (shedIncluded == 1) createShedOrder(shed, maxRows);


        return maxRows;
    }

    private void createShedOrder(Shed shed, int maxRows) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "insert into shed (orderId, length, material) values(?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, maxRows);
                ps.setInt(2, shed.getLength());
                ps.setString(3, shed.getMaterial());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Shed data is saved");
                } else {
                    throw new DatabaseException("Could not save shed data");
                }
            }
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    protected ArrayList<Order> getOrderDataForAdmin() {
        ArrayList<Order> orders = new ArrayList<>();

        int orderId;
        int phone;
        String fullname;
        int length;
        int width;
        String mat;
        int shedLength;

        int counter = 0;

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT carport.order.orderId,carport.user.fullname, carport.user.phone, carport.order.length, carport.order.width, carport.order.material, carport.shed.length AS shedLength from carport.order " +
                "left join carport.shed on carport.order.orderId = carport.shed.orderId " +
                "inner join carport.user on carport.order.userId =  carport.user.userId ";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderId = rs.getInt("orderId");
                    fullname = rs.getString("fullname");
                    phone = rs.getInt("phone");
                    length = rs.getInt("length");
                    width = rs.getInt("width");
                    mat = rs.getString("material");


                    orders.add(new Order(orderId, fullname, phone, length, width, mat));

                    if (rs.getInt("shedLength") != 0) {
                        shedLength = rs.getInt("shedLength");
                        orders.get(counter).setShed(shedLength);
                    }
                    counter++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    protected ArrayList<Order> getOrderDataForUser(User user) {
        ArrayList<Order> orders = new ArrayList<>();

        int orderId;
        int length;
        int width;
        String mat;
        int shedLength;

        int counter = 0;

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT carport.order.orderId, carport.order.length, carport.order.width, carport.order.material, carport.shed.length AS shedLength, carport.order.price from carport.order " +
                "left join carport.shed on carport.order.orderId = carport.shed.orderId " +
                "inner join carport.user on carport.order.userId =  carport.user.userId where `order`.userId = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user.getUserId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderId = rs.getInt("orderId");
                    length = rs.getInt("length");
                    width = rs.getInt("width");
                    mat = rs.getString("material");


                    orders.add(new Order(orderId, user.getName(), user.getPhoneNumber(), length, width, mat));

                    orders.get(counter).setPrice(rs.getInt("price"));
                    if (rs.getInt("shedLength") != 0) {
                        shedLength = rs.getInt("shedLength");
                        orders.get(counter).setShed(shedLength);
                    }
                    counter++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    protected int getOrderPrice(int orderId) {

        int price = 0;

        String sql = "SELECT SUM(bom.amount*material.price) AS 'price' from bom inner join material on material.itemId = bom.itemId where bom.orderId = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    price = rs.getInt("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    protected void updatePrice(int orderId, int price) {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "update carport.`order` set carport.`order`.price = ? where carport.`order`.orderId = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, price);
                ps.setInt(2, orderId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Price data is saved");
                } else {
                    throw new DatabaseException("Could not save Price data");
                }
            }
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void deleteOrder(int orderId) {
        Logger.getLogger("web").log(Level.INFO, "");

        String sqlFK = "SET FOREIGN_KEY_CHECKS=0;";


        String sql = "delete carport.order.*, carport.bom.*, carport.shed.* from carport.order " +
                "left join carport.bom on carport.order.orderId = carport.bom.orderId " +
                "left join carport.shed on carport.order.orderId = carport.shed.orderId " +
                "where carport.order.orderId = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sqlFK)) {
                ps.executeUpdate();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("delete data success");
                } else {
                    throw new DatabaseException("delete data didn't work");
                }
            }
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }
}
