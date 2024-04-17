package servlet;

import dao.ExpenseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.HibernateUtil;
import java.io.IOException;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int id = Integer.parseInt(req.getParameter("id"));
       ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
       boolean f = dao.deleteExpense(id);
       HttpSession session= req.getSession();

       if(f){
            session.setAttribute("msg","Delete Successfully");
            resp.sendRedirect("user/view_expense.jsp");
       }else{
           session.setAttribute("msg","something wrong on server");
           resp.sendRedirect("user/view_expense.jsp");
       }
    }
}
