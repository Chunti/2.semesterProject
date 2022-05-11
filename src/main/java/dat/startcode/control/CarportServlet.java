package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

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

        session = request.getSession();

        /*OrderMapper orderMapper = new OrderMapper(connectionPool);
        if( (int) session.getAttribute("orderId") == 0){
            User user = (User) session.getAttribute("user");
            int orderId = orderMapper.createOrder(user.getUserId());
            session.setAttribute("orderId", orderId);
        }

        BottomMapper bottomMapper = new BottomMapper(connectionPool);
        ToppingMapper toppingMapper = new ToppingMapper(connectionPool);
        ArrayList<Bottom> bottomArrayList = null;
        ArrayList<Topping> toppingArrayList = null;
        try {
            bottomArrayList = bottomMapper.getBottomData();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            toppingArrayList = toppingMapper.getToppingData();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("bottoms", bottomArrayList);
        servletContext.setAttribute("topping", toppingArrayList);
        System.out.println(bottomArrayList);
        System.out.println(toppingArrayList);*/

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
        int height = Integer.parseInt(request.getParameter("height"));

        boolean shedBol = Boolean.parseBoolean(request.getParameter("shed"));

        if(shedBol){
            int shedlength = Integer.parseInt(request.getParameter("shedlength"));
            int shedwidth = Integer.parseInt(request.getParameter("shedwidth"));

            shed = new Shed(shedlength,shedwidth,height,"Andet lækkert træ",userId);
            System.out.println(shed);
        }

        Carport carport = new Carport(length,width,height,"Lækkert træ",userId);

        int orderId = UserFacade.createOrder(carport,shed,user,connectionPool);

        session.setAttribute("orderId", orderId);

        System.out.println(carport);
        request.getRequestDispatcher("WEB-INF/orderrequest.jsp").forward(request, response);


    }
}
