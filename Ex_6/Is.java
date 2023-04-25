/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */

public class Is {

    //Verifica se a entrada inteira é formada por vogais
    static boolean vogais(String str){

        boolean resp=true;
        int i=0;

        while(i<str.length() && resp){
            
            if(!isVogal(str.charAt(i))){
                resp = false;
            } 

            i++;
        }
        return resp;
    }
    //Verifica se a entrada inteira é formada por consoantes
    static boolean consoantes(String str){
        boolean resp=true;
        int i=0;

        while(i<str.length() && resp){
            
            if(!isConsoante(str.charAt(i))){
                resp = false;
            } 

            i++;
        }
        return resp;
    }
    //Verifica se a entrada inteira é formada por números inteiros
    static boolean inteiros(String str){
        boolean resp=true;
        int i=0;

        while(i<str.length() && resp){
            
            if(!isNumI(str.charAt(i))){
                resp = false;
            } 

            i++;
        }
        return resp;
    }
    //Verifica se a entrada inteira é formada por numeros reais
    static boolean reais(String str){
        boolean resp = true;
        int i, pont=0,num=0;
        //passa pela string verificando se são números ou pontos
        for(i=0;i<str.length();i++){
            if(isNumI(str.charAt(i))){
                num++;
            } else if(str.charAt(i)== '.' || str.charAt(i)== ','){
                pont++;
            }
            
        }
        if(num == str.length()){
            resp = true;
        } 
        else if(num == str.length() -1 && pont == 1){
            resp=true;
        } else {
            resp = false;
        }
        return resp;
    }

    // Verifica se o char mandado é uma vogal
    static boolean isVogal (char c){
        if(c=='a'|| c== 'e'|| c== 'i'|| c== 'o'|| c== 'u'|| c== 'A'|| c== 'E'|| c== 'I'|| c== 'O'|| c== 'U'){
            return true;
        } else {
            return false;
        }
        
    }

    //Verifica se é uma consoante
    static boolean isConsoante (char c){
        if((c>='A' && c<='Z' || c>='a' && c<='z') && !isVogal(c)){
            return true;
        } else {
            return false;
        }
    }

    //Verifica se é um número 
    static boolean isNumI (char c){
        if(c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='0' ){
            return true;
        } else {
            return false;
        }
    }
    //Verifica se o usuário digitou FIM
    static boolean fim(String str){ 
        if (str.charAt(0)== 'F' && str.charAt(1)== 'I' && str.charAt(2) == 'M'){
            return true;
        } 
        return false;
    }
    
    public static void main(String[] args){
        String str = "";
        boolean resp = false;
        //Roda o código enquanto o usuário não digitar FIM
        while(resp == false){

           str = MyIO.readLine();
           resp = fim(str);

           if(resp == false){
            //Imprime se vogais
            if(vogais(str)== true){
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            //Imprime se consoante
            if(consoantes(str)== true){
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            //Imprime se inteiro
            if(inteiros(str)== true){
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }
            //Imprime se real
            if(reais(str)== true){
                MyIO.print("SIM ");
            } else {
                MyIO.print("NAO ");
            }

            MyIO.print("\n");

           }
        }
    }
}
