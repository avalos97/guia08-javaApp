/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia08.viernes.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;




/**
 *
 * @author root
 */
@WebServlet(name = "jasper", urlPatterns = {"/jasper"})
public class jasper extends HttpServlet {
    
    @Resource(name = "jdbc/cine")
    DataSource poolcine;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion = null;
        response.setContentType("application/pdf");
        System.out.println("...........................................");
        HashMap<String, Object> parametros = new HashMap<>();
        try(ServletOutputStream out = response.getOutputStream()) {
            String parametro = request.getParameter("boton");
            if(parametro!=null){
                if(parametro.equals("Reporte Pelicula")){
                    System.out.println("...........................................1");
                    InputStream reporte = jasper.class.getResourceAsStream("/recursosJasper/ReportePelicula.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(reporte);
                    conexion=poolcine.getConnection();
                    
                    String fechaInicial = request.getParameter("fecha1");
                    parametros.put("fecha1", fechaInicial);
                    
                    String fechaFinal = request.getParameter("fecha2");
                    parametros.put("fecha2", fechaFinal);
                    
                    JasperPrint impresor = JasperFillManager.fillReport(reporte, parametros, conexion);
                    JRPdfExporter exportadorPdf = new JRPdfExporter();
                    
                    exportadorPdf.setExporterInput(new SimpleExporterInput(impresor));
                    exportadorPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                    exportadorPdf.exportReport();
                }else if(parametro.equals("Reporte Funcion")){
                    System.out.println("...........................................2");
                    parametros.put("nada", 1);
                    InputStream reporte = jasper.class.getResourceAsStream("/recursosJasper/ReporteFuncion.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(reporte);
                    conexion=poolcine.getConnection();
                    System.out.println("...........................................2.1");
                    if(reporte != null){
                        System.out.println("...........................................2.2");
                    }
                    JasperPrint impresor = JasperFillManager.fillReport(report, parametros, conexion);
                    
                    JRPdfExporter exportadorPdf = new JRPdfExporter();
                    
                    exportadorPdf.setExporterInput(new SimpleExporterInput(impresor));
                    exportadorPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                    exportadorPdf.exportReport();
                }
            }
            
            
        } catch (Exception e) {
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
