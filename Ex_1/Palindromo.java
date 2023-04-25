/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */

class Palindromo{
    //Metodo que recebe a string, e verifica se a mesma é um palindromo
    static boolean palindromo(String str)
    {
        
        int i = 0, j = str.length() - 1;
 
        while (i < j) {
 

            if (str.charAt(i) != str.charAt(j))
                return false;
 

            i++;
            j--;
        }

        return true;
    }
    //Metodo que verifica se o usuário digitou FIM
    static boolean fim(String str)
    {   //Testa se os primeiros espaços são "F" "I" e "M" e retorna true se sim
        if (str.charAt(0)== 'F' && str.charAt(1)== 'I' && str.charAt(2) == 'M'){
        return true;
        } 
        return false;
    }

    public static void main(String[] args)
    {
        String str;
        boolean resp = false;
        //Mantem o codigo rodando até que resposta seja true
        while (resp == false){
           //Le o teclado 
           str= MyIO.readLine();
           //Verifica se foi escrito FIM
           resp = fim(str);
           //Encerra o codigo se resposa for verdadeira
           if (resp != true){
            //Imprime SIM se a string for um palindromo e NAO se não for
             if (palindromo(str)){
                    System.out.print("SIM\n");
              } else {
                     System.out.print("NAO\n");
              }
          }
    }
}
}
