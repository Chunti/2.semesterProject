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

        if(user.getRole() == 1){
            session.setAttribute("orders", Facade.getOrderDataForAdmin(connectionPool));
        }
        else session.setAttribute("orders", Facade.getOrderDataForUser(user, connectionPool));

        request.getRequestDispatcher("WEB-INF/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete = request.getParameter("delete");
        String edit = request.getParameter("edit");
        String details = request.getParameter("details");

        if(delete != null){
            System.out.println("delete" + delete);
        }
        else if (edit != null){
            int editNum = Integer.parseInt(edit);
            HttpSession session = request.getSession();
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
            System.out.println(editNum);
            session.setAttribute("editOrder", orders.get(editNum));

            int price = Facade.getOrderPrice(orders.get(editNum).getOrderId(),connectionPool);
            session.setAttribute("price",price);

            int offerPrice = (int) (Math.ceil(((price * 1.3)/100)))*100;
            session.setAttribute("offerPrice",offerPrice);

            request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request,response);
        }
        else if(details != null){
            request.getRequestDispatcher("WEB-INF/svgpage.jsp").forward(request,response);
        }



        doGet(request,response);
    }
}
