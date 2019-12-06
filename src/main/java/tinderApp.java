import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


/*
*
* http://localhost:9001/users/
*
 */
public class tinderApp {

    public static void main(String[] args) throws Exception {
        Server server = new Server(9001);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(testServlet.class,"/users/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }

}

