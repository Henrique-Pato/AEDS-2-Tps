/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */
import java.util.Random;

public class AlteracaoAleatoria {
    /*Função que percorre a string substituindo quando necessário os valores aleatórios
    é salvando-os em uma nova string, que é retornada
    */
     static String troca(String str, char prim, char seg){
        String straux = "";
        for(int i =0; i<str.length();i++){
            if(str.charAt(i) == prim){
                straux += seg;
            } else {
                straux += str.charAt(i);
            }
        }
        return straux;
    }

    //Verifica se o usuário digitou FIM
    static boolean fim(String str){ 
        if (str.charAt(0)== 'F' && str.charAt(1)== 'I' && str.charAt(2) == 'M'){
            return true;
        } 
        return false;
    }

    public static void main(String[] args){
        String strN, strA;
        boolean resp=false;
        char prim, seg;

        Random gerador = new Random() ;
        gerador.setSeed (4) ;
        while(resp==false){

            strN = MyIO.readLine();  
            resp = fim(strN);

            if(resp==false){   
                //Gera as letras aleatórias
                prim = ((char)('a' + (Math.abs (gerador.nextInt())%26)));
                seg = ((char)('a' + (Math.abs (gerador.nextInt())%26)));
                //Chama a função para criar a nova string
                strA = troca(strN, prim, seg);
                MyIO.println(strA);
            }

        }
    }
}
