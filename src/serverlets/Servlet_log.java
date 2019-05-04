package serverlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "Servlet_log")
public class Servlet_log extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        String log_property_map = getServletConfig().getInitParameter("log4j-properties-location");
        ServletContext context = getServletContext() ;
        if(log_property_map != null) {
            String web_path = context.getRealPath("/");
            System.out.println("得到的路径"+web_path);
            String log_config_file_path = web_path + log_property_map;
            File log_config_file = new File(log_config_file_path);
            if(log_config_file.exists()) {
                PropertyConfigurator.configure(log_config_file_path);
            }else {
                BasicConfigurator.configure();
            }
        }else {
            BasicConfigurator.configure();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
