/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ramki.bean;

/**
 *
 * @author ramki
 */
public class SemesterPaper {
    
    private String name;
    private double mark;

    public SemesterPaper() {
    }

    
    public SemesterPaper(String name, double mark) {
        this.name = name;
        this.mark = mark;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
    
    
}
