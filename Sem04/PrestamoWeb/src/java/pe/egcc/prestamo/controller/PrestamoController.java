package pe.egcc.prestamo.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.egcc.prestamo.model.PrestamoModel;
import pe.egcc.prestamo.service.PrestamoService;

@WebServlet(name = "PrestamoController", urlPatterns = {"/PrestamoController"})
public class PrestamoController extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, 
          HttpServletResponse response) 
          throws ServletException, IOException {
  
    // Variable
    double capital, interes;
    int meses;
    List<PrestamoModel> lista = null;
    // Datos
    capital = Double.parseDouble(request.getParameter("capital"));
    interes = Double.parseDouble(request.getParameter("interes"));
    meses = Integer.parseInt(request.getParameter("meses"));
    // Proceso
    PrestamoService service = new PrestamoService();
    lista = service.procPrestamo(capital, interes, meses);
    Gson gson = new Gson();
    String listaJson = gson.toJson(lista);
    // response JSON
    response.setContentType("application/json");
    // response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    // Get the printwriter object from response to write the required json object to the output stream      
    PrintWriter out = response.getWriter();
    // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
    out.print(listaJson);
    out.flush();
  }

  
  
}
