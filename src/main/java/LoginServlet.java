import db.DbOps;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {


    private final TemplateEngine te;

    public LoginServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

//        data.put("header", "This is a login page");
//        data.put("links", Arrays.asList(
//                new LinkItem("/menu", "Menu"),
//                new LinkItem("/logout", "Logout"),
//                new LinkItem("http://google.com", "Google")
//        ));
        te.render("login.html", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail=req.getParameter("inputEmail");
        String userPass=req.getParameter("inputPassword");


        List<User> users = DbOps.getUserForLogin();
        for(User eachUser: users){
            if(eachUser.getUserEmail().equals(userEmail) && eachUser.getUserPassword().equals(userPass))
                System.out.printf("Welcome %s", userEmail);
            else
                System.out.println("No such user exist");
        }


        System.out.printf("%s: %s", userEmail,userPass);
    }
}
