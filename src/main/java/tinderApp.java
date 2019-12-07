import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;


/*
*
* http://localhost:9001/user/
*
*/
public class tinderApp {

    public static void main(String[] args) throws Exception {
        Server server = new Server(9001);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet((new ServletHolder(new StaticContentServlet("templates"))),"/static/*");
        handler.addServlet(user.class, "/user");
        handler.addServlet(new ServletHolder(new RedirectServlet("/user")),"/*");

        //handler.addServlet(testServlet.class,"/users/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }

}

