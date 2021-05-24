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
public class Tabella {
    private int righe, colonne;
    String[][] tabella;
    
    public Tabella(int colonne) {
        //Righe mantenuto ad 1 come minimo per permettere di aggiungere il titolo alla tabella
        righe=1;
        this.colonne=colonne;
        tabella=new String[1][colonne];
        for(int i=0; i<colonne; i++)
            tabella[0][i]="";
    }
    
    /**
     * Metodo che aggiunge un titolo ai parametri della tabella ma che non consente di aggiungerne di più delle colonne esistenti.
     * @param titoli 
     */
    public void setTitle(String [] titoli) {
        for(int i=0; i<tabella[0].length; i++)
            tabella[0][i]=titoli[i];
    }
    
    /*
    public void setTitleAndErase(String [] titoli) {
        
    }*/
    
    /**
     * Metodo che ti permette di aggiungere una nuova riga ma i valori di input non saranno inseriti dopo aver superato il numero massimo di colonne
     * @param riga 
     */
    public void addRow(String[] riga) {
        //Istanzio una nuova tabella di una riga maggiore
        String[][] newTabella = new String[righe+1][colonne];
        //Copio i valori nella nuova tabella
        for(int i=0; i<righe*colonne; i++)
            newTabella[i/colonne][i%colonne]=tabella[i/colonne][i%colonne];
        //Aggiungo la nuova riga
        for(int i=0; i<colonne; i++)
            newTabella[righe][i]=riga[i];
        //Modifico il numero delle righe
        righe++;
        //Ridimensiono la vecchia tabella
        tabella = new String[righe][colonne];
        //Copio i valori della nuova tabella nella nuova tabella ridimensionata
        for(int i=0; i<righe*colonne; i++)
            tabella[i/colonne][i%colonne]=newTabella[i/colonne][i%colonne];
        
    }
    
    public static String outTabella(String[][] tabella, int righe, int colonne) {
        //Array di int per numero caratteri in una colonna
        //Usato per la larghezza della colonna
        int[] numeroCaratteriColonna=new int[colonne];
        //Inizializzazione a 0
        for(int i=0; i<numeroCaratteriColonna.length; i++)
            numeroCaratteriColonna[i]=0;
        //Conto il massimo dei caratteri per colonna
        //Ciclo per ogni colonna
        int numeroCaratteriCella=0;
        for(int i=0; i<colonne; i++) {
            //Ciclo per ogni riga
            for(int j=0; j<righe; j++) {
                //Conta caratteri
                numeroCaratteriCella=tabella[j][i].length();
                //Sostituzione
                if(numeroCaratteriColonna[i]<numeroCaratteriCella)
                    numeroCaratteriColonna[i]=numeroCaratteriCella;
            }
        }
        //Costruzione stringa tabella
        String strTabella="";
        //Stampa bordo orizzontale superiore
        for(int i=0; i<colonne; i++) {
            strTabella+="·";
            //Stampa linea del blocco con spazio orizzontale con i bordi (+2)
            for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                strTabella+="-";
        }
        //Carattere finale
        strTabella+="·\n";
        //Aggiunta titolo
        //Controllo presenza titolo
        boolean presenzaTitolo=false;
        for(int i=0; i<colonne; i++) {
            if(tabella[0][i]!="") {
                presenzaTitolo=true;
                break;
            }
        }
        //Se titolo presente
        if(presenzaTitolo) {
            //Valore celle
            for(int i=0; i<colonne; i++) {
                strTabella+="| "+tabella[0][i]+" ";
                //Spazio per titoli minori della dimensione massima
                if(tabella[0][i].length()<numeroCaratteriColonna[i]) {
                    //Per tutti i caratteri mancanti
                    for(int j=tabella[0][i].length(); j<numeroCaratteriColonna[i]; j++)
                        strTabella+=" ";
                }
            }
            //Carattere finale
            strTabella+="|\n";
            //Spazio inferiore
            for(int i=0; i<colonne; i++) {
                strTabella+="·";
                //Stampa linea vuota per spazio verticale con spazio orizzontale con i bordi (+2)
                for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                    strTabella+="-";
            }
            //Carattere finale
            strTabella+="·\n";
        }
        //Aggiunta dati tabella
        if(righe>1) {
            //Ciclo inserimento dati (i=1 per escludere il titolo)
            for(int i=1; i<righe; i++) {
                //Aggiunta dati riga
                for(int j=0; j<colonne; j++) {
                    strTabella+="| "+tabella[i][j]+" ";
                    //Spazio per dati minori della dimensione massima
                    if(tabella[i][j].length()<numeroCaratteriColonna[j]) {
                        //Per tutti i caratteri mancanti
                        for(int k=tabella[i][j].length(); k<numeroCaratteriColonna[j]; k++)
                            strTabella+=" ";
                    }
                }
                //Carattere finale
                strTabella+="|\n";
                //Aggiunta bordo inferiore, spazio con prossima riga se non è l'ultima
                if(i!=righe-1) {
                    for(int j=0; j<colonne; j++) {
                        //Carattere iniziale
                        strTabella+="|";
                        //Spazio del dato di lunghezza massima nella colonna più bordi orizzontali (+2)
                        for(int k=0; k<numeroCaratteriColonna[j]+2; k++)
                            strTabella+=" ";
                    }
                    //Carattere finale
                    strTabella+="|\n";
                }

            }
            //Chiusura tabella bordo inferiore
            for(int i=0; i<colonne; i++) {
                strTabella+="·";
                //Stampa linea del blocco con spazio orizzontale con i bordi (+2)
                for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                    strTabella+="-";
            }
            //Carattere finale
            strTabella+="·\n";
        }
        
        return strTabella;
    }
    
    public String outTabella() {
        //Array di int per numero caratteri in una colonna
        //Usato per la larghezza della colonna
        int[] numeroCaratteriColonna=new int[colonne];
        //Inizializzazione a 0
        for(int i=0; i<numeroCaratteriColonna.length; i++)
            numeroCaratteriColonna[i]=0;
        //Conto il massimo dei caratteri per colonna
        //Ciclo per ogni colonna
        int numeroCaratteriCella=0;
        for(int i=0; i<colonne; i++) {
            //Ciclo per ogni riga
            for(int j=0; j<righe; j++) {
                //Conta caratteri
                try {
                    numeroCaratteriCella=tabella[j][i].length();
                }
                catch(NullPointerException ex) {
                    System.out.println("i="+i+" j="+j);
                    System.out.println(tabella[j][i]);
                }
                //Sostituzione
                if(numeroCaratteriColonna[i]<numeroCaratteriCella)
                    numeroCaratteriColonna[i]=numeroCaratteriCella;
            }
        }
        //Costruzione stringa tabella
        String strTabella="";
        //Stampa bordo orizzontale superiore
        for(int i=0; i<colonne; i++) {
            strTabella+="·";
            //Stampa linea del blocco con spazio orizzontale con i bordi (+2)
            for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                strTabella+="-";
        }
        //Carattere finale
        strTabella+="·\n";
        //Aggiunta titolo
        //Controllo presenza titolo
        boolean presenzaTitolo=false;
        for(int i=0; i<colonne; i++) {
            if(tabella[0][i]!="") {
                presenzaTitolo=true;
                break;
            }
        }
        //Se titolo presente
        if(presenzaTitolo) {
            //Valore celle
            for(int i=0; i<colonne; i++) {
                strTabella+="| "+tabella[0][i]+" ";
                //Spazio per titoli minori della dimensione massima
                if(tabella[0][i].length()<numeroCaratteriColonna[i]) {
                    //Per tutti i caratteri mancanti
                    for(int j=tabella[0][i].length(); j<numeroCaratteriColonna[i]; j++)
                        strTabella+=" ";
                }
            }
            //Carattere finale
            strTabella+="|\n";
            //Spazio inferiore
            for(int i=0; i<colonne; i++) {
                strTabella+="·";
                //Stampa linea vuota per spazio verticale con spazio orizzontale con i bordi (+2)
                for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                    strTabella+="-";
            }
            //Carattere finale
            strTabella+="·\n";
        }
        //Aggiunta dati tabella
        if(righe>1) {
            //Ciclo inserimento dati (i=1 per escludere il titolo)
            for(int i=1; i<righe; i++) {
                //Aggiunta dati riga
                for(int j=0; j<colonne; j++) {
                    strTabella+="| "+tabella[i][j]+" ";
                    //Spazio per dati minori della dimensione massima
                    if(tabella[i][j].length()<numeroCaratteriColonna[j]) {
                        //Per tutti i caratteri mancanti
                        for(int k=tabella[i][j].length(); k<numeroCaratteriColonna[j]; k++)
                            strTabella+=" ";
                    }
                }
                //Carattere finale
                strTabella+="|\n";
                //Aggiunta bordo inferiore, spazio con prossima riga se non è l'ultima
                if(i!=righe-1) {
                    for(int j=0; j<colonne; j++) {
                        //Carattere iniziale
                        strTabella+="|";
                        //Spazio del dato di lunghezza massima nella colonna più bordi orizzontali (+2)
                        for(int k=0; k<numeroCaratteriColonna[j]+2; k++)
                            strTabella+=" ";
                    }
                    //Carattere finale
                    strTabella+="|\n";
                }

            }
            //Chiusura tabella bordo inferiore
            for(int i=0; i<colonne; i++) {
                strTabella+="·";
                //Stampa linea del blocco con spazio orizzontale con i bordi (+2)
                for(int j=0; j<numeroCaratteriColonna[i]+2; j++)
                    strTabella+="-";
            }
            //Carattere finale
            strTabella+="·\n";
        }
        return strTabella;
    }
}
