package db;

public class User {
  private int id;
  private String userName;
  private String userEmail;
  private String userPassword;
  private String userImageLink;
  private String userAction;



  public User(int id, String name, String link, String action) {
    this.id = id;
    this.userName = name;
    this.userImageLink = link;
    this.userAction=action;
  }
    public User(int id, String email, String password) {
        this.id = id;
        this.userEmail = email;
        this.userPassword = password;
    }

  public User(String name, String link) {
    this.userName = name;
    this.userImageLink = link;
  }

  public User(int id, String action) {
    this.id = id;
    this.userAction = action;
  }



  public int getId() {
    return id;
  }


  public String getUserName() {
    return userName;
  }

  public String getUserAction() { return userAction; }

  public String getUserEmail() {
    return userEmail;
  }

    public String getUserPassword() { return userPassword;}

    public String getUserImageLink() {
    return userImageLink;
  }

  @Override
  public String toString() {
    return this.id+" "+ this.userName+" "+ this.userImageLink+" "+this.userAction+"\n";
  }
}
