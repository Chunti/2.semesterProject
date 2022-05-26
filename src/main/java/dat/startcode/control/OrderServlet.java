package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
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

        if(user.getRole() == 1) session.setAttribute("orders", Facade.getOrderDataForAdmin(connectionPool));
        else session.setAttribute("orders", Facade.getOrderDataForUser(user, connectionPool));

        request.getRequestDispatcher("WEB-INF/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String delete = request.getParameter("delete");
        String details = request.getParameter("details");

        if(delete != null){
            int orderId = Integer.parseInt(delete);
            Facade.deleteOrder(orderId, connectionPool);
            System.out.println(delete);
        }

        else if(details != null){

            int detailsNum = Integer.parseInt(details);
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
            System.out.println(detailsNum);
            System.out.println(orders.get(detailsNum));
            session.setAttribute("editOrder", orders.get(detailsNum));
            request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request,response);
        }

        doGet(request,response);
    }
}
