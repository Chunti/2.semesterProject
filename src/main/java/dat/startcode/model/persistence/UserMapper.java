package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper
{
    ConnectionPool connectionPool;

    protected UserMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }


    protected User login(String email, String password) throws DatabaseException {

        //createUser("mogens@lykketoft.dk", "Mogens Lykketoft", "MogensErGud","admin");
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;
        System.out.println(email);

        String sql = "SELECT * FROM `user` WHERE email = ? ";
        int userId;
        byte[] hash;
        byte[] salt;
        String name;
        int role;
        int phone;

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    System.out.println("ello");
                    userId = rs.getInt("userId");
                    hash = rs.getBytes("Hash");
                    salt = rs.getBytes("Salt");
                    name = rs.getString("fullname");
                    role = rs.getInt("role");
                    phone = rs.getInt("phone");
                } else
                {
                    throw new DatabaseException("Fejl i brugernavn eller kodeord");
                }

                KeySpec check = new PBEKeySpec(password.toCharArray(),salt,65536,128);
                SecretKeyFactory factory1 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] userPassword = factory1.generateSecret(check).getEncoded();
                if(Arrays.equals(hash,userPassword)){
                    user = new User(userId,name,email,role,phone);
                    System.out.println("Hej det er faktisk rigtigt password");
                }
                else{
                    System.out.println("Wrong Password");
                    return null;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl under indlogning. Der er noget galt med databasen");
        }
        return user;
    }


    protected void createUser(String name, String email, String address, int zipcode, String password,int phone, int role) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "insert into `user` ( fullname, email, address, zipcode, phone, role, Hash, Salt) values (?,?,?,?,?,?,?,?)";

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        byte[] hash = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt , 65536, 128);

        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3,address);
                ps.setInt(4,zipcode);
                ps.setInt(5,phone);
                ps.setInt(6,role);
                ps.setBytes(7,hash);
                ps.setBytes(8,salt);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1)
                {
                    System.out.println("everything went good");
                } else
                {
                    throw new DatabaseException("Brugeren med email = " + email + " kunne ikke oprettes i databasen");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Kunne ikke indsætte bruger i databasen");
        }
        //return user;
    }


    /*public void updateUser(User user){

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "update `user` set `name` = ?, email = ?, phone = ?, balance = ? where user_id = ?;";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setInt(3,user.getPhoneNumber());
                ps.setInt(4,user.getBalance());
                ps.setInt(5,user.getUserId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /*public ArrayList<Customer> getAllUsers(){

        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM `user`;";

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {

                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    int phoneNumber = rs.getInt("phone");
                    int balance = rs.getInt("balance");
                    customers.add(new Customer(userId,name,email,phoneNumber,balance));
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }*/
}
