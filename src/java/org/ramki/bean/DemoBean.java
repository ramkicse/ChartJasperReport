package org.ramki.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ramki
 */
@ManagedBean
@SessionScoped
public class DemoBean {

    private List<Student> listOfStudent;
  
    public DemoBean() {
        
        listOfStudent=new ArrayList<Student>();
                
       Student student=new Student();
        
      List<SemesterPaper>   listOfSemesterPaper = new ArrayList<SemesterPaper>();
        listOfSemesterPaper.add(new SemesterPaper("Algorithm", 80.5));
        listOfSemesterPaper.add(new SemesterPaper("Data Struct", 96.5));
        listOfSemesterPaper.add(new SemesterPaper("S/w Engg", 50.5));
        listOfSemesterPaper.add(new SemesterPaper("Lab 1", 90.5));
        listOfSemesterPaper.add(new SemesterPaper("Lab 2", 95.5));

        student.setListOfSemesterPaper(listOfSemesterPaper);
        student.setName("Rama krishnan");
        student.setRollNo("02CS24");
        student.setImagePath("/home/ramki/Downloads/ramki.jpg");

        listOfStudent.add(student);
    }

    public String pdf() throws JRException, IOException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfStudent);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/chartReport.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
        
        return null;
    }
}
