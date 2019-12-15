package db;

public class User {
  private int id;
  private String userName;
  private String userEmail;
  private String userImageLink;



  public User(int id, String name, String userEmail, String link) {
    this.id = id;
    this.userName = name;
    this.userEmail = userEmail;
    this.userImageLink = link;
  }


  public User(String name, String link) {
    this.userName = name;
    this.userImageLink = link;
  }
  public int getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getUserImageLink() {
    return userImageLink;
  }
}
