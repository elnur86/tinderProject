import db.DbOps;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserServlet extends HttpServlet {

    private final TemplateEngine te;

    public UserServlet(TemplateEngine te) {
        this.te = te;
    }
    int id=2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        ArrayList<String> action=new ArrayList<>();
        action=DbOps.getAction();



        User user= DbOps.getUser(id);
        data.put("imageLink", user.getUserImageLink());
        data.put("userName",  user.getUserName());

        te.render("like-page.html", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonDisLike=req.getParameter("button1");
        String buttonLike=req.getParameter("button2");
        String result="";

        if (buttonDisLike!=null) result=buttonDisLike;
        else result=buttonLike;
        DbOps.insertAction(id,result);

        resp.sendRedirect("/like/*");

    }


}
