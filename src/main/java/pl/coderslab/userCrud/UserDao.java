package pl.coderslab.userCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao{

    private final String database;

    public UserDao(String database) {
        this.database = database;
    }

    public UserDao() {
        this.database = DbUtil.MAIN_DB;
    }

    public void create(User user) {
        String sql = "INSERT INTO users(email, username, password) VALUES(?, ?, ?)";
        try(PreparedStatement stmt = DbUtil.connect(database).prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet res = stmt.getGeneratedKeys();
            if(res.next()) user.setId(res.getInt(1));
        }
        catch (SQLException e){
            user.setId(0);
            System.out.println(e.getMessage());
        }
    }

    public User read(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.connect(database).prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if(set.next()){
                return buildFromSet(set);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(User user) {
        String sql = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.connect(database).prepareStatement(sql)){
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.connect(database).prepareStatement(sql)){
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql ="SELECT * FROM users";
        try(PreparedStatement stmt = DbUtil.connect(database).prepareStatement(sql)){
            ResultSet set = stmt.executeQuery();
            while (set.next()){
                list.add(buildFromSet(set));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    private User buildFromSet(ResultSet set) throws SQLException{
        User user = new User();
        user.setId(set.getInt(1));
        user.setEmail(set.getString(2));
        user.setUserName(set.getString(3));
        user.setPassword(set.getString(4));
        return user;
    }
}