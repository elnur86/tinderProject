import db.DbOps;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PeopleServlet extends HttpServlet {

    private final TemplateEngine te;

    public PeopleServlet(TemplateEngine te) {
        this.te = te;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        List<User> users = DbOps.getUserWithActionsLike();
        data.put("likedUsers",users);

        te.render("people-list.ftl", data, resp);
    }
}

