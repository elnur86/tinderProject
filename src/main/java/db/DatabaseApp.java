package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseApp {
  public static void main(String[] args) throws SQLException {
//    DbOps.insert_user("Mario", "mario@i.ua");
//    List<User> users = DbOps.get_all_users();
//    users.forEach(u ->
//        System.out.printf("%d : %-8s : %s\n",
//            u.getId(), u.getUsername(), u.getUseremail()));


//  User user=DbOps.getUser(1);
//    System.out.println(user);

    ArrayList<String> action=DbOps.getAction();
    System.out.println(action.toString());

  }
}
