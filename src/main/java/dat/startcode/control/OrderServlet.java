package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {

    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user.getRole() == 1){
            System.out.println("Hej");
            session.setAttribute("orders", UserFacade.getOrderDataForAdmin(connectionPool));
        }

        request.getRequestDispatcher("WEB-INF/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete = request.getParameter("delete");
        String edit = request.getParameter("edit");


        if(delete != null){
            System.out.println("delete" + delete);
        }

        else if (edit != null){
            HttpSession session = request.getSession();
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
            session.setAttribute("editOrder", orders.get(Integer.parseInt(edit)));
            request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request,response);
        }

        doGet(request,response);
    }
}
