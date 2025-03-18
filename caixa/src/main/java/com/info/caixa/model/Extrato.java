/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.caixa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author info20242
 */
public class Extrato {
    private Date dataInicial = new Date();
    private Date dataFinal = new Date();
    private int tipo;
    
    public String getDataFormatadaInicial(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dataInicial);
    }
    
    public void setDataFormatadaInicial(String dataFormatada){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dataInicial = sdf.parse(dataFormatada);
        }catch(Exception e){
            System.out.println("Erro de formato de data");
        }
    }
    
    public String getDataFormatadaBRInicial(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataInicial);
    }
    
    public String getDataFormatadaFinal(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dataFinal);
    }
    
    public void setDataFormatadaFinal(String dataFormatada){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dataFinal = sdf.parse(dataFormatada);
        }catch(Exception e){
            System.out.println("Erro de formato de data");
        }
    }
    
    public String getDataFormatadaBRFinal(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataFinal);
    }

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
