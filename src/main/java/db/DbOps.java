package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOps {
  public static ArrayList<String> getAction(){
    try {
      Connection conn = DbConn.get();
      String SQLS = "select useraction from tinder";
      PreparedStatement stmt = conn.prepareStatement(SQLS);
      ResultSet result = stmt.executeQuery();

      ArrayList<String> action = new ArrayList<>();
      while (result.next()) {
        String userAction=result.getString("userAction");
        action.add(userAction);
      }
      return action;


    } catch (SQLException e) {
      throw new RuntimeException("Smth went wrong insertAction(id)", e);
    }


  }

  public static void insert_user(String name, String email) throws SQLException {
    Connection conn = DbConn.get();
    String SQLI = "insert into tinder (userName, userEmail) values (?, ?)";
    PreparedStatement stmt = conn.prepareStatement(SQLI);
    stmt.setString(1, name);
    stmt.setString(2, email);
    stmt.execute();
  }

  public static void insertAction(int id, String action){
    try {
      Connection conn = DbConn.get();
      String SQLI = "update tinder set useraction=? where userID=?";
      PreparedStatement stmt = conn.prepareStatement(SQLI);
      stmt.setString(1, action);
      stmt.setInt(2, id);
      stmt.execute();
    } catch (SQLException e) {
      throw new RuntimeException("Smth went wrong insertAction(id)", e);
    }
  }

  public static User getUser(int id) {
    try {
      Connection conn = DbConn.get();
      String SQLS = "select username, userpiclink from tinder where userID=?";
      PreparedStatement stmt = conn.prepareStatement(SQLS);
      stmt.setInt(1, id);
      ResultSet result = stmt.executeQuery();
      ArrayList<User> userNameFromDB=new ArrayList<>();
      while (result.next()){
        String userN=result.getString("userName");
        String userPL=result.getString("userPicLink");
        User user = new User( userN, userPL);
        userNameFromDB.add(user);
      }
      return userNameFromDB.get(0);
    } catch (SQLException e) {
      throw new RuntimeException("Smth went wrong getUser(id)", e);
    }
  }

  public static List<User> get_all_users() throws SQLException {
    Connection conn = DbConn.get();
    String SQLS = "select * from tinder";
    PreparedStatement stmt = conn.prepareStatement(SQLS);
    ResultSet outcome = stmt.executeQuery();
    ArrayList<User> data = new ArrayList<>();
    while (outcome.next()) {
      int id = outcome.getInt("userID");
      String name = outcome.getString("userName");
      String email = outcome.getString("userEmail");
      String link =outcome.getString("userpiclink");
      User user = new User(id, name, email, link);
      data.add(user);
    }
    return data;
  }

    public static List<User> getUserIdAction() {
      try {
          Connection conn = DbConn.get();
          String SQLS = "select userid, useraction from tinder order by userid";
          PreparedStatement stmt = conn.prepareStatement(SQLS);
          ResultSet outcome = stmt.executeQuery();
          ArrayList<User> data = new ArrayList<>();
          while (outcome.next()) {
              int id = outcome.getInt("userID");
              String action = outcome.getString("useraction");
              User user = new User(id, action);
              data.add(user);
          }
          return data;
      }
      catch (SQLException e) {
          throw new RuntimeException("Smth went wrong getUser(id)", e);
      }
    }

    public static List<User> getUserWithNoActions() {
      try {
          Connection conn = DbConn.get();
          String SQLS = "select userid, username, userpiclink, useraction from tinder  where useraction is null order by userid";
          PreparedStatement stmt = conn.prepareStatement(SQLS);
          ResultSet outcome = stmt.executeQuery();
          ArrayList<User> data = new ArrayList<>();
          while (outcome.next()) {
              int id = outcome.getInt("userID");
              String name=  outcome.getString("username");
              String link= outcome.getString("userpiclink");
              String action = outcome.getString("useraction");
              User user = new User(id, name, link, action);
              data.add(user);
          }
          return data;
      }
      catch (SQLException e) {
          throw new RuntimeException("Smth went wrong getUser(id)", e);
      }
    }
    public static List<User> getUserWithActionsLike() {
        try {
            Connection conn = DbConn.get();
            String SQLS = "select userid, username, userpiclink, useraction from tinder  where useraction='yes' order by userid";
            PreparedStatement stmt = conn.prepareStatement(SQLS);
            ResultSet outcome = stmt.executeQuery();
            ArrayList<User> data = new ArrayList<>();
            while (outcome.next()) {
                int id = outcome.getInt("userID");
                String name=  outcome.getString("username");
                String link= outcome.getString("userpiclink");
                String action = outcome.getString("useraction");
                User user = new User(id, name, link, action);
                data.add(user);
            }
            return data;
        }
        catch (SQLException e) {
            throw new RuntimeException("Smth went wrong getUser(id)", e);
        }
    }
  public static void insert_op(int op1, int op2, String op, int result, int user) {
    try {
      Connection conn = DbConn.get();
      String SQLI = "insert into operations (op1, op2, op, r, user_id) VALUES (?,?,?,?,?)";
      PreparedStatement stmt = conn.prepareStatement(SQLI);
      stmt.setInt(1, op1);
      stmt.setInt(2, op2);
      stmt.setString(3, op);
      stmt.setInt(4, result);
      stmt.setInt(5, user);
      stmt.execute();
    } catch (SQLException e) {
      throw new RuntimeException("DbOpsEX!!!", e);
    }
  }
}
