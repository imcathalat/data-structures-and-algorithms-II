/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.olimpiadasmedalhistas;

import java.time.LocalDate;

/**
 *
 * @author 1487306
 */
public class Medalha {
    private TipoMedalha medalType;
    private LocalDate medalDate;
    private String discipline;
    private String event;
    
    public Medalha(TipoMedalha type, LocalDate date, String discipline, String event){
        this.medalType = type;
        this.discipline = discipline;
        this.event = event;
        this.medalDate = date;
    }

    public TipoMedalha getMedalType() {
        return medalType;
    }

    @Override
    public String toString() {
        return "Medalha{" + "medalType=" + medalType + ", medalDate=" + medalDate + ", discipline=" + discipline + ", event=" + event + '}';
    }
    
    
    
}
