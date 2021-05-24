/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tabella;

/**
 *
 * @author ghegghe
 */
public class Main {
    public static void main(String[] args) {
        int righe=3, colonne=5;
        String[][] tabella=new String [righe][colonne];
        for(int i=0; i<righe*colonne; i++)
            tabella[i/colonne][i%colonne]=String.valueOf(i);
        System.out.println(Tabella.outTabella(tabella, righe, colonne));
        Tabella tab=new Tabella(colonne);
        tab.setTitle(tabella[0]);
        for(int i=0; i<righe; i++) {
            tab.addRow(tabella[i]);
        }
        System.out.println(tab.outTabella());
    }
}
