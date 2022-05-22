package dat.startcode.control;


import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.BoMItems;
import dat.startcode.model.entities.Order;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import dat.startcode.model.services.BomGenerater;
import dat.startcode.model.services.SVGGenerater;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SVGServlet", value = "/SVGServlet")
public class SVGServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("editOrder");


        SVGGenerater svgGenerater = new SVGGenerater(order.getLength(),order.getWidth(), order.getShedLength());
        String svg = svgGenerater.getFullSVG();

        /*BomGenerater bomGenerater = new BomGenerater(order);
        String bom = bomGenerater.createBoM();
        Facade.saveBomData(bom,connectionPool);*/

        System.out.println("hej");

        ArrayList<BoMItems> boMItems = Facade.getBomData(order.getOrderId(),order.getLength()+order.getShedLength(),order.getWidth(),connectionPool);

        System.out.println(boMItems);
        session.setAttribute("bomitems",boMItems);
        request.setAttribute("svgdrawing", svg);
        request.getRequestDispatcher("WEB-INF/svgpage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
