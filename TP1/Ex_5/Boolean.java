package TP1.Ex_5;

/** 
 * 
 * @author Henrique Pato Magalhães
 * @since 23/02/2023
 * @version 23/02/2023
 */
import java.util.Scanner;

public class Boolean {
    // Main
    public static void main(String[] args){

        Scanner scr = new Scanner(System.in);
        int num = scr.nextInt();
        // Mantem o codigo rodando enquanto nao for escrito '0'
        while(num != 0){

            // Salva os valores das letras
            int [] val = new int[num];
            for(int i = 0; i < num; i++ ){
                val[i] = scr.nextInt();
            }

            // Salva a expressao
            String exp = scr.nextLine();
            // Converte ela para facilitar manipulacao
            char [] expEmChar = troca(exp);
            trocaLetras(expEmChar, num, val);

            // Interpreta a expressao
            boolean resp = interp(expEmChar);

            // Imprime resultado 
            if(resp == true){ 
                System.out.println("1");
            } else{ 
                System.out.println("0");
            }

            num = scr.nextInt();
        }
        scr.close();
    }

    // Transforma a String recebida em um array de char
    public static char[] troca(String exp){
        char[] resp = new char[exp.length()];
        for(int i = 0; i < exp.length(); i++){
            resp[i] = exp.charAt(i);
        }
        return resp;
    }

    // Troca as letras na expressao pelo seu valor
    public static void trocaLetras(char[] expEmChar, int num, int[] val){
        char[]var = new char[num];
        for(int i = 0; i < num; i++){
            var[i] = (char) (65+i);
        }

        for(int i = 0; i < expEmChar.length; i++){
            for(int j = 0; j < num; j++){ 
                if(expEmChar[i] == var[j]){
                    expEmChar[i] = toChar(val[j]);
                }
            }
        }
    }


    public static char toChar(int bool){
        char c = 'x';

        if(bool == 0) c = '0';
        else if(bool == 1) c = '1';

        return c;
    }

    // Interpreta o que deve ser feito
    public static boolean interp(char[] x){
        boolean resp = false;
        // interpreta expressao e realiza operacoes em sua devida ordem
        for(int i = 0; i < x.length; i++){
            // procura ')' => fim de uma operacao
            if(x[i] == ')'){
               
                // volta procurando operacao
                for(int j = i; j > 0; j--){
                    // procura '(' => inicio de uma operacao
                    if(x[j] == '('){
                       
                        // verifica qual operacao booleana
                        if(x[j-1] == 't'){ // not
                            not(x, j, i);
                            j = 0; // sai do for interno
                        } else if(x[j-1] == 'd'){ // and
                            and(x, j, i);
                            j = 0;
                        } else if(x[j-1] == 'r'){ // or
                            or(x, j, i);
                            j = 0; // sai do for interno
                        }
                    
                    }
                }
                
            }
        }
        // atribui resultado da expressao
        if(x[0] == '1'){
            resp = true;
        }
        else if(x[0] == '0'){
            resp = false;
        }
    
        return resp;   
    }

    // Resolve a operacao Not
    public static void not(char[] x, int j, int i){
        char resp = 'x';

        // verifica resultado da operacao
        if(x[j+1] == '0'){
            resp = '1';
        } else if(x[j+1] == '1'){
            resp = '0';
        }            
        
        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-3); k--){
            x[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        x[k] = resp;
    }

    // Resolve a operacao And
    public static void and(char[] x, int j, int i){
        char resp = 'x';
        char[] val = new char[100];
        int n = 0;

        // percorre entre parenteses da expressao
        for(int k = j+1; k < i; k++){
            if(x[k] != ' ' && x[k] != ','){ // nao e espaco nem virgula => valor (0 ou 1)
                val[n] = x[k];
                n++;
            }
        }

        resp = '1';
        // verifica resultado da operacao
        while( n > 0 && resp != '0' ){
            n--;
            
            if(val[n] != '1')
                resp = '0';
        }

        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-3); k--){
            x[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        x[k] = resp;
    }

    // Resolve a opreacao Or
    public static void or(char[] x, int j, int i){
        char resp = 'x';
        char[] val = new char[100];
        int n = 0;

        // percorre entre parenteses da expressao
        for(int k = j+1; k < i; k++){
            if(x[k] != ' ' && x[k] != ','){ // nao eh espaco nem virgula => valor (0 ou 1)
                val[n] = x[k];
                n++;
            }
        }

        resp = '0';
        // verifica resultado da operacao
        while( n > 0 && resp != '1' ){
            n--;
            
            if(val[n] == '1')
                resp = '1';
        }

        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-2); k--){
            x[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        x[k] = resp;
    }
}
