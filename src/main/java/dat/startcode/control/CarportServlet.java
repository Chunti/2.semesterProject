package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import dat.startcode.model.services.BomGenerater;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CarportServlet", value = "/CarportServlet")
public class CarportServlet extends HttpServlet {

    HttpSession session;
    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //session = request.getSession();

        request.getRequestDispatcher("WEB-INF/carport.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        Shed shed = null;

        response.setContentType("text/html");

        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));

        boolean shedBol = Boolean.parseBoolean(request.getParameter("shed"));

        if(shedBol){
            int shedlength = Integer.parseInt(request.getParameter("shedlength"));

            shed = new Shed(shedlength,"Andet lækkert træ",userId);
        }

        Carport carport = new Carport(length,width,"Lækkert træ",userId);

        int orderId = Facade.createOrder(carport,shed,user,connectionPool);


        Order order = new Order(orderId, user.getName(),user.getPhoneNumber(),length,width,"Lækkert træ");

        if(shedBol){
            order.setShed(shed.getLength());
        }

        BomGenerater bomGenerater = new BomGenerater(order);
        String bom = bomGenerater.createBoM();
        Facade.saveBomData(bom,connectionPool);

        session.setAttribute("orderId", orderId);



        request.getRequestDispatcher("WEB-INF/orderrequest.jsp").forward(request, response);


    }
}
