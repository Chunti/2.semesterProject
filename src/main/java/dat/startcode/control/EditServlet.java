package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {

    private ConnectionPool connectionPool;
    private HttpSession session;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String edit = request.getParameter("edit");

        int editNum = Integer.parseInt(edit);
        ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
        System.out.println(editNum);
        session.setAttribute("editOrder", orders.get(editNum));

        int price = Facade.getOrderPrice(orders.get(editNum).getOrderId(),connectionPool);
        session.setAttribute("price",price);
        int offerPrice = (int) (Math.ceil(((price * 1.3)/100)))*100;
        session.setAttribute("offerPrice",offerPrice);


        request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String priceString = request.getParameter("number");
        int price = Integer.parseInt(priceString);

        Order order = (Order) session.getAttribute("editOrder");

        Facade.updatePrice(order.getOrderId(),price, connectionPool);
        request.getRequestDispatcher("WEB-INF/order.jsp").forward(request,response);


    }
}
