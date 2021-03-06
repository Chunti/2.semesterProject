package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateUser", value = "/CreateUser")
public class CreateUser extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //UserFacade.createUserTest(connectionPool);

        response.setContentType("text/html");
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int zipcode = Integer.parseInt(request.getParameter("zipcode"));
        int phoneNumber = Integer.parseInt(request.getParameter("phone"));
        String password = request.getParameter("password");

        try {
            Facade.createUser(fullName,email,address,zipcode,password,phoneNumber,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("login.jsp").forward(request,response);

    }
}
