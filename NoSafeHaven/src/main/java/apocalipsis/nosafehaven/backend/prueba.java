/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

/**
 *
 * @author cristina
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ranking r=new Ranking();
        
        String s5= "z0001";
        int si5=50;
        String s0= "z0004";
        int si0=600;
        String s1= "z0001";
        int si1=40;
        String s2= "z0002";
        int si2=20;
        String s3= "z0003";
        int si3=400;
        String s4= "z0004";
        int si4=10;
        
        si2+=31;
        
        
        r.actualizarRanking(s4, si4);
        r.actualizarRanking(s1, si1);
        r.actualizarRanking(s3, si3);
        r.actualizarRanking(s2, si2);
        r.actualizarRanking(s5, si5);
        r.actualizarRanking(s2, si2);
        r.actualizarRanking(s0, si0);
        
    }
    
}
