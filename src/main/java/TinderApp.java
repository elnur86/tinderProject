import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * http://localhost:9001/*
 */
public class TinderApp {
  public static void main(String[] args) throws Exception {
    TemplateEngine te = TemplateEngine.resources("/template");
    ServletContextHandler handler = new ServletContextHandler();

    handler.addServlet((new ServletHolder(new StaticContentServlet("content"))), "/static/*");
    handler.addServlet((new ServletHolder(new LoginServlet(te))), "/login/*");
    handler.addServlet((new ServletHolder(new UserServlet(te))), "/like/*");
    handler.addServlet((new ServletHolder(new ChatServlet(te))), "/chat/*");
    handler.addServlet((new ServletHolder(new PeopleServlet(te))), "/people/*");
    handler.addServlet(new ServletHolder(new RedirectServlet("/like/*")), "/*");

    Server server = new Server(9001);
    server.setHandler(handler);
    server.start();
    server.join();
  }

}
