public class TP01q05{
    public static void main(String[] args){
        int n = MyIO.readInt(); // numero de variaveis
        
        while(n != 0){ // le ate n = 0
            // array com valores das variaveis (0 ou 1)
            int[] val = new int[n];
            for(int i = 0; i < n; i++){
                val[i] = MyIO.readInt();
            }

            // expressao booleana a ser interpretada
            String lin = MyIO.readLine();
            char[] exp = toCharArray(lin);
            replaceLetters(exp, n, val);

            // interpretar expressao
            Boolean res = parser(exp);

            // imprimir resultado (0 ou 1)
            if(res) System.out.println("1");
            else System.out.println("0");

            n = MyIO.readInt();
        }
    }

    public static char[] toCharArray(String lin){
        char[] exp = new char[lin.length()];

        // transforma string em array de char
        for(int i = 0; i < lin.length(); i++){
            exp[i] = lin.charAt(i);
        }

        return exp;
    }

    public static char toChar(int bool){
        char c = 'x';

        if(bool == 0) c = '0';
        else if(bool == 1) c = '1';

        return c;
    }
    
    public static void replaceLetters(char[] exp, int n, int[] val){
        // letras das variaveis
        char[] var = new char[n];
        for(int i = 0; i < n; i++){
            var[i] = (char) (65 + i);
        }
    
        // substitui letras na expressao por seus valores
        for(int i = 0; i < exp.length; i++){
            for(int j = 0; j < n; j++){ // verifica cada variavel
                if(exp[i] == var[j]){
                    exp[i] = toChar(val[j]);
                }
            }
        }
    }

    public static Boolean parser(char[] exp){
        boolean res = false;

        // interpreta expressao e realiza operacoes em sua devida ordem
        for(int i = 0; i < exp.length; i++){
            // procura ')' => fim de uma operacao
            if(exp[i] == ')'){
               
                // volta procurando operacao
                for(int j = i; j > 0; j--){
                    // procura '(' => inicio de uma operacao
                    if(exp[j] == '('){
                       
                        // verifica qual operacao booleana
                        if(exp[j-1] == 't'){ // not
                            not(exp, j, i);
                            j = 0; // sai do for interno
                        } else if(exp[j-1] == 'd'){ // and
                            and(exp, j, i);
                            j = 0;
                        } else if(exp[j-1] == 'r'){ // or
                            or(exp, j, i);
                            j = 0; // sai do for interno
                        }
                    
                    }
                }
                
            }
        }

        // atribui resultado da expressao
        if(exp[0] == '1') res = true;
        else if(exp[0] == '0') res = false;

        return res;
    }

    public static void not(char[] exp, int j, int i){
        char res = 'x';

        // verifica resultado da operacao
        if(exp[j+1] == '0'){
            res = '1';
        } else if(exp[j+1] == '1'){
            res = '0';
        }            
        
        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-3); k--){
            exp[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        exp[k] = res;
    }

    public static void and(char[] exp, int j, int i){
        char res = 'x';
        char[] val = new char[100];
        int n = 0;

        // percorre entre parenteses da expressao
        for(int k = j+1; k < i; k++){
            if(exp[k] != ' ' && exp[k] != ','){ // nao eh espaco nem virgula => valor (0 ou 1)
                val[n] = exp[k];
                n++;
            }
        }

        res = '1';
        // verifica resultado da operacao
        while( n > 0 && res != '0' ){
            n--;
            
            if(val[n] != '1')
                res = '0';
        }

        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-3); k--){
            exp[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        exp[k] = res;
    }

    public static void or(char[] exp, int j, int i){
        char res = 'x';
        char[] val = new char[100];
        int n = 0;

        // percorre entre parenteses da expressao
        for(int k = j+1; k < i; k++){
            if(exp[k] != ' ' && exp[k] != ','){ // nao eh espaco nem virgula => valor (0 ou 1)
                val[n] = exp[k];
                n++;
            }
        }

        res = '0';
        // verifica resultado da operacao
        while( n > 0 && res != '1' ){
            n--;
            
            if(val[n] == '1')
                res = '1';
        }

        int k;
        // limpa operacao (substitui por espacos)
        for(k = i; k > (j-2); k--){
            exp[k] = ' ';
        }

        // substitui primeira letra da operacao pelo resultado
        exp[k] = res;
    }
}
