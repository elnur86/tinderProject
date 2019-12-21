import db.DbOps;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserServlet extends HttpServlet{

    private final TemplateEngine te;

    public UserServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//---------------------------Checking for cookies----------------------------

        Cookie[] cookies = req.getCookies();
        Optional<Cookie> user = Optional.empty();
        int userID=0;

        if (cookies==null)
        {
            resp.sendRedirect("/login/*");
        }
        for (Cookie c: cookies) {
            if (c.getName().equals("myCookie")) {
                user = Optional.of(c);
                userID=  Integer.valueOf(c.getValue());
            }
        }
//----------------------------------------------------------------------------

         HashMap<String, Object> data = new HashMap<>();
         List<User> users = DbOps.getUserWithNoActions();
         User loggedUser=null;
         for(User getLoggedUser: users)
             if(getLoggedUser.getId()==userID)
                 loggedUser=getLoggedUser;

         users.remove(loggedUser);
         if (!users.isEmpty()) {
             data.put("userlink", users.get(0).getUserImageLink());
             data.put("username", users.get(0).getUserName());
             data.put("userid", users.get(0).getId());
         } else
             resp.sendRedirect("/people/*");
         te.render("like-page.html", data, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonDisLike=req.getParameter("button1");
        String buttonLike=req.getParameter("button2");

        String result= "";
        if(buttonDisLike!=null) result=buttonDisLike;
        else result=buttonLike;
        int id = Integer.parseInt(req.getParameter("id"));
//        System.out.printf("ID=%d",id);
        DbOps.insertAction(id,result);

        resp.sendRedirect("/like/*");
    }


}
