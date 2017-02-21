/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aluno
 */
@WebServlet(urlPatterns = {"/ListaRegistroServlet"})
public class ListaRegistroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaRegistroServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem de Produtos</h1>");
            
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                
                Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
                
                String sql = "SELECT nome,quantidade FROM produto";
                
                Statement operacao = conexao.createStatement();
                ResultSet resultado = operacao.executeQuery(sql);
                
                out.println("<h4>Consulta Realizada com Sucesso!!!</h4>");
                out.println("<table border='5' text-align='center'><tr><th>Nome</th><th>Quantidade</th></tr>");
                while(resultado.next())
                {
                    out.println("<tr>");
                    out.print("<td>"+resultado.getString("nome")+"</td>");
                    out.print("<td>"+resultado.getString("quantidade")+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                
                
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(ListaRegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<h4>ERRO: "+ex.getMessage());
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
