/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */


public class Cifra {
    //Encripita a string na base de 3 e retorna a string encriptada
    static String encriptar( String strN) {
        int i, n = strN.length();
          String saux = "";
 
          for (i=0; i<n; i++) {
            saux = saux + (char)(strN.charAt(i) + 3);
          }
 
          return saux;
    }
    //Verifica se o usuário digitou FIM
    static boolean fim(String str)
    { 
        if (str.charAt(0)== 'F' && str.charAt(1)== 'I' && str.charAt(2) == 'M'){
        return true;
        } 
        return false;
    }

    public static void main(String[] args){
        String strN, strC;
        boolean resp = false;

        while(resp == false){
        strN = MyIO.readLine();
        resp = fim(strN);
        
        if(resp == false){
        strC = encriptar (strN) ;
        MyIO.print(strC + "\n");
        }
    }
    } 
}
