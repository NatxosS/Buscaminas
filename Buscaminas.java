
package recursividad;

/**
 *
 * @author NatxosS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Buscaminas extends JFrame implements ActionListener {
    
    JButton [][] busca;
    JButton reinicio;
    int contadorBombas;
    Integer bombasCerca = 0;
    boolean soloUnaVez;
    
    Buscaminas() {
        
        setLayout(null);                                                        // generamos la matriz de botones \/\/
        setTitle("Buscaminas");
        
        busca = new JButton[10][10];
        for (int f=0; f<10; f++) {
            for (int c=0; c<10; c++) {
                busca[f][c] = new JButton("0");                                 // 0, vacia
                busca[f][c].setBounds(20+c*41,50+f*41,41,41);
                busca[f][c].setBackground(Color.GRAY);
                busca[f][c].setForeground(Color.GRAY);
                busca[f][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
                busca[f][c].addActionListener(this);                
                add(busca[f][c]);
            }
        }
        
        reinicio = new JButton("Partida Nueva");
        reinicio.setBounds(20,470,175,25);
        add(reinicio);
        reinicio.addActionListener(this);
        
        NuevoJuego();
    }
    
    public void NuevoJuego() {
        
        while (contadorBombas < 10) {
            repetir();
        }

        for (int f=0; f<10; f++) {                                              // Doble bucle For para asignar un texto al resto de los botones en funcion de las bombas del alrededor
            for (int c=0; c<10; c++) {
                if (!busca[f][c].getText().equals("b")) {
                    bombasCerca = RellenarResto(f-1, c-1);
                    String bombas = String.valueOf(bombasCerca);
                    busca[f][c].setText(bombas);
                }
            }
        }
    }
    
    public void repetir() {
        
        int coordenadaX = (int)(Math.random()*10);
        int coordenadaY = (int)(Math.random()*10);
        if (!busca[coordenadaX][coordenadaY].getText().equals("b")) {
            busca[coordenadaX][coordenadaY].setText("b");
            contadorBombas++;
        } else {
            repetir();
        }
    }
    
    public int RellenarResto(int fil, int col) {
        
        bombasCerca = 0;
        if (fil<10 && fil>=0 && col<10 && col>=0){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        col = col + 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c+1);
        col = col + 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c+1);
        fil = fil + 1;
        col = col - 2;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f+1,c-2);
        col = col + 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c+1);
        col = col + 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c+1);
        fil = fil + 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f+1,c);
        col = col - 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c-1);
        col = col - 1;
        if (fil<10 && fil>-1 && col<10 && col>-1){
            if (busca[fil][col].getText().equals("b")) {
                bombasCerca++; 
            }
        }
        //RellenarResto(f,c-1);
        
        return bombasCerca; 
    }
    
    public void actionPerformed(ActionEvent e) {
        
        for(int f=0; f<10; f++) {
            for (int c=0; c<10; c++) {
                if (e.getSource() == busca[f][c]) {
                    if (busca[f][c].getText().equals("b")) {
                        setTitle("Buscaminas   BOOOMBAZOO");
                        for (f=0; f<10; f++) {
                            for (c=0; c<10; c++) {
                                busca[f][c].setEnabled(false);
                                busca[f][c].setBackground(Color.yellow);
                                if (busca[f][c].getText().equals("b")) {
                                    busca[f][c].setForeground(Color.RED);
                                    busca[f][c].setBackground(Color.black);
                                }
                            }
                        }
                    } else {
                        busca[f][c].setBackground(Color.blue);
                        busca[f][c].setForeground(Color.BLACK);
                        busca[f][c].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
                        busca[f][c].setEnabled(false);                            
                        if (busca[f][c].getText().equals("0")) {
                            Destapar(f, c);
                        }
                        Comprobacion();
                    }
                }
            }
        }
        
        if (e.getSource() == reinicio) {                                        // Si le damos al boton de nuevo juego, ponemos la tabla entera a 0, el contador de bomba a 0 y llamamos al metodo nuevo juego
            contadorBombas = 0;
            for (int f=0; f<10; f++) {
                for (int c=0; c<10; c++) {
                    busca[f][c].setText("0");
                    busca[f][c].setBackground(Color.GRAY);
                    busca[f][c].setForeground(Color.GRAY);
                    busca[f][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
                    busca[f][c].setEnabled(true);
                }
            }
            NuevoJuego();
        }
    }
    
    public void Destapar(int fil, int col) {
        
        if (fil<10 && fil>=0 && col<10 && col>=0) {
            if (busca[fil][col].getText().equals("0")) {
                busca[fil][col].setText(" ");
                busca[fil][col].setBackground(Color.blue);
                busca[fil][col].setForeground(Color.BLACK);
                busca[fil][col].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
                Destapar(fil,col+1);
                Destapar(fil,col-1);
                Destapar(fil-1,col);
                Destapar(fil+1,col);
                Destapar(fil-1,col-1);
                Destapar(fil-1,col+1);
                Destapar(fil+1,col+1);
                Destapar(fil+1,col-1);
            } else {
                busca[fil][col].setBackground(Color.blue);
                busca[fil][col].setForeground(Color.BLACK);
                busca[fil][col].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
                busca[fil][col].setEnabled(true);
            }
        }
    }
    
    public void Comprobacion() {
        
        int cant = 0;
        for (int f=0; f<10; f++) {
            for (int c=0; c<10; c++) {
                if (busca[f][c].isEnabled()) {
                    cant++;
                }
            }
        }
        if (cant == 90) {
            setTitle("Buscaminas  Has ganadooOOO!!");
            for (int f=0; f<10; f++) {
                for (int c=0; c<10; c++) {
                    busca[f][c].setForeground(Color.red);
                    busca[f][c].setEnabled(true);
                }
            }
        }
    }
    
    public static void main(String[] ar) {
        
        Buscaminas bu = new Buscaminas();
        bu.setBounds(0,0,490,600);
        bu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bu.setVisible(true);
    }
}