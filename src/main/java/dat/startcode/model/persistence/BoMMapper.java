package dat.startcode.model.persistence;

import dat.startcode.model.entities.BoMItems;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoMMapper {
    ConnectionPool connectionPool;

    protected BoMMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    protected void saveBomData(String bom){
        System.out.println(bom);

        Logger.getLogger("web").log(Level.INFO, "");

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(bom))
            {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    protected ArrayList<BoMItems> getBomData(int orderId, int totalLength, int width, ConnectionPool connectionPool) {
        ArrayList<Order> orders = new ArrayList<>();

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<BoMItems> boMItems = new ArrayList<>();

        String sql =    "SELECT carport.bom.itemId, carport.bom.amount , carport.material.name, carport.material.length, carport.material.description, carport.material.unit, carport.material.price " +
                        "from carport.bom " +
                        "inner join carport.material on carport.bom.itemId = carport.material.itemId "+
                        "where bom.orderId = ? "+
                        "order by itemId;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,orderId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int itemId = rs.getInt("itemId");
                    int amount = rs.getInt("amount");
                    String name = rs.getString("name");
                    int length = rs.getInt("length");
                    String description = rs.getString("description");
                    String unit = rs.getString("unit");
                    int price = rs.getInt("price");
                    if(itemId == 8 && amount == 2) length = totalLength;
                    if(itemId == 8 && amount > 2) length = width;

                    boMItems.add(new BoMItems(orderId,itemId,amount,name,length,description,unit,price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boMItems;
    }

}
