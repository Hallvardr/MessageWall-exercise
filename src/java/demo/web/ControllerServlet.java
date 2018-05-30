package demo.web;

import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String view = perform_action(request);
        forwardRequest(request, response, view);
    }

    protected String perform_action(HttpServletRequest request)
        throws IOException, ServletException {
        
        String serv_path = request.getServletPath();
        HttpSession session = request.getSession();
        if (serv_path.equals("/login.do")) {

            this.user = request.getParameter("user");
            this.pass = request.getParameter("password");

            this.conn = this.getRemoteLogin().connect(user, pass);

            if (conn != null){
                session.setAttribute("useraccess", conn);
                session.setAttribute("accumMsg",restart);
                session.setAttribute("refresh", false);
                //return "/wallview";
                return "/view/wallview.jsp";
            }

            return "/error-not-loggedin.html";
        }

        else if (serv_path.equals("/put.do")) {

            //this.conn = this.getRemoteLogin().connect(user, pass);

            if (conn != null){

                accumMsg = (Integer) session.getAttribute("accumMsg");

                accumMsg = accumMsg + 1;

                session.setAttribute("accumMsg", accumMsg);

                //session.setAttribute("newMsg", true);

                if ((msg = request.getParameter("msg"))!= null)
                    conn.put(msg);

                //return "/wallview";
                return "/view/wallview.jsp";
            }

            return "/error-not-loggedin.html";
        }

        else if (serv_path.equals("/refresh.do")) {

            if (conn != null){

                session.setAttribute("refresh", true);

                //return "/wallview";
                return "/view/wallview.jsp";
            }

            return "/error-not-loggedin.html";

        }

        else if (serv_path.equals("/delete.do")) {

            index = Integer.valueOf(request.getParameter("index"));

            conn.delete(index);

            //return "/wallview";
            return "/view/wallview.jsp";

        }

        else if (serv_path.equals("/logout.do")) {
            //...
            return "/goodbye.html";
        }

        else {
            return "/error-bad-action.html";
        }
    }

    public RemoteLogin getRemoteLogin() {
        return (RemoteLogin) getServletContext().getAttribute("remoteLogin");
    }
    public void forwardRequest(HttpServletRequest request, HttpServletResponse response, String view) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        if (dispatcher == null) {
            throw new ServletException("No dispatcher for view path '"+view+"'");
        }
        dispatcher.forward(request,response);
    }
}


