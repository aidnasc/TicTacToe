/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author airan.nascimento
 */
public final class Controle {
    private char[][] tabuleiro;
    private int vitoriasJogador1 = 0;
    private int vitoriasJogador2 = 0;

    public Controle() {
        this.tabuleiro = new char[3][3];
    }

    public int getVitoriasJogador1() {
        return vitoriasJogador1;
    }

    public int getVitoriasJogador2() {
        return vitoriasJogador2;
    }
    
    public void adicionarVitoriaJogador1() {
        vitoriasJogador1++;
    }
    
    public void adicionarVitoriaJogador2() {
        vitoriasJogador2++;
    }
    
    public void resetarVitorias() {
        vitoriasJogador1 = 0;
        vitoriasJogador2 = 0;
    }
    
    public void resetarMatriz() {
        tabuleiro = new char[3][3];
    }
    
    public void substituirCaractere(char caractere, int lin, int col) {
        tabuleiro[lin][col] = caractere;
    }
    
    public int verificarVencedor(int vez) {        
        if ((tabuleiro[0][0] == 'X' && tabuleiro[0][1] == 'X' && tabuleiro[0][2] == 'X') ||
            (tabuleiro[0][0] == 'O' && tabuleiro[0][1] == 'O' && tabuleiro[0][2] == 'O')) {
            return vez;
        } else if ((tabuleiro[1][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[1][2] == 'X') ||
                   (tabuleiro[1][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[1][2] == 'O')) {
            return vez;
        } else if ((tabuleiro[2][0] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[2][2] == 'X') ||
                   (tabuleiro[2][0] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[2][2] == 'O')) {
            return vez;
        } else if ((tabuleiro[0][0] == 'X' && tabuleiro[1][0] == 'X' && tabuleiro[2][0] == 'X') ||
                   (tabuleiro[0][0] == 'O' && tabuleiro[1][0] == 'O' && tabuleiro[2][0] == 'O')) {
            return vez;
        } else if ((tabuleiro[0][1] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][1] == 'X') ||
                   (tabuleiro[0][1] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][1] == 'O')) {
            return vez;
        } else if ((tabuleiro[0][2] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[2][2] == 'X') ||
                   (tabuleiro[0][2] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[2][2] == 'O')) {
            return vez;
        } else if ((tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X') ||
                   (tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O')) {
            return vez;
        } else if ((tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == 'X') ||
                   (tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][0] == 'O')) {
            return vez;
        } else {
            return 0;
        }
    }
    
    public boolean verificarEmpate(int vez) {
        if (tabuleiroCheio()) {
            return verificarVencedor(vez) == 0;  
        } else {
            return false;
        }
    }
    
    private boolean tabuleiroCheio() {
        return (tabuleiro[0][0] == 'X' || tabuleiro[0][0] == 'O') && (tabuleiro[0][1] == 'X' || tabuleiro[0][1] == 'O') &&
               (tabuleiro[0][2] == 'X' || tabuleiro[0][2] == 'O') && (tabuleiro[1][0] == 'X' || tabuleiro[1][0] == 'O') &&
               (tabuleiro[1][1] == 'X' || tabuleiro[1][1] == 'O') && (tabuleiro[1][2] == 'X' || tabuleiro[1][2] == 'O') &&
               (tabuleiro[2][0] == 'X' || tabuleiro[2][0] == 'O') && (tabuleiro[2][1] == 'X' || tabuleiro[2][1] == 'O') &&
               (tabuleiro[2][2] == 'X' || tabuleiro[2][2] == 'O');
    }
    
    public int passarVez(int vez) {
        if (vez == 1) {
            vez = 2;
        } else {
            vez = 1;
        }
        
        return vez;
    }
    
    public void contabilizarVitoria(int vez) throws IOException {
        if (vez == 1) {
            adicionarVitoriaJogador1();
            salvarArquivo(); 
        } else {
            adicionarVitoriaJogador2();
            salvarArquivo();
        }   
    }
    
    public void carregarArquivo() throws FileNotFoundException, IOException {
        BufferedReader rd = new BufferedReader(new FileReader("vitorias.txt"));
        String linha;
        
        linha = rd.readLine(); // Lê a primeira linha
                
        if (linha == null) { // Se primeira linha nula, ainda não há vitórias
            vitoriasJogador1 = vitoriasJogador2 = 0;
        } else {
            vitoriasJogador1 = Integer.parseInt(linha);
            linha = rd.readLine(); // Lê a segunda linha
            vitoriasJogador2 = Integer.parseInt(linha);
        }
              
        rd.close();   
    }

    public void salvarArquivo() throws IOException {
        OutputStream os = new FileOutputStream("vitorias.txt"); 
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr); 
        
        br.write(String.valueOf(vitoriasJogador1));
        br.newLine();
        br.write(String.valueOf(vitoriasJogador2));
        br.close();
    }
}
