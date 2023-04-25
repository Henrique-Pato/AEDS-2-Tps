/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */

#include <stdio.h>
#include <string.h>
//Prototipagem
int isNumI (char c);
int vogais(char str[1000]);
int consoantes(char str[1000]);
int inteiros(char str[1000]);
int reais(char str[1000]);
int isVogal (char c);
int isConsoante (char c);
int fim(char str[1000]);

    //Verifica se a entrada inteira é formada por vogais
    int vogais(char str[1000]){

        int resp=1;
        int i=0;

        while(i<strlen(str) && resp==1){
            
            if(isVogal(str[i])==0){
                resp = 0;
            } 

            i++;
        }
        return resp;
    }

    //Verifica se a entrada inteira é formada por consoantes
    int consoantes(char str[1000]){
        int resp=1;
        int i=0;

        while(i<strlen(str) && resp==1){
            
            if(isConsoante(str[i])==0){
                resp = 0;
            } 

            i++;
        }
        return resp;
    }

    //Verifica se a entrada inteira é formada por números inteiros
    int inteiros(char str[1000]){
        int resp=1;
        int i=0;

        while(i<strlen(str) && resp==1){
            
            if(isNumI(str[i])==0){
                resp = 0;
            } 

            i++;
        }
        return resp;
    }

    //Verifica se a entrada inteira é formada por numeros reais
    int reais(char str[1000]){
        int resp = 1;
        int i, pont=0,num=0;
        //passa pela char verificando se são números ou pontos
        for(i=0;i<strlen(str);i++){
            if(isNumI(str[i])==1){
                num++;
            } else if(str[i]== '.' || str[i]== ','){
                pont++;
            }
            
        }
        if(num == strlen(str)){
            resp = 1;
        } 
        else if(num == strlen(str) -1 && pont == 1){
            resp=1;
        } else {
            resp = 0;
        }
        return resp;
    }

    // Verifica se o char mandado é uma vogal
    int isVogal (char c){
        if(c=='a'|| c== 'e'|| c== 'i'|| c== 'o'|| c== 'u'|| c== 'A'|| c== 'E'|| c== 'I'|| c== 'O'|| c== 'U'){
            return 1;
        } else {
            return 0;
        }
        
    }

    //Verifica se é uma consoante
    int isConsoante (char c){
        if(((c>='A' && c<='Z') || (c>='a' && c<='z')) && isVogal(c)==0){
            return 1;
        } else {
            return 0;
        }
    }

    //Verifica se é um número 
    int isNumI (char c){
        if(c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='0' ){
            return 1;
        } else {
            return 0;
        }
    }
    //Verifica se o usuário digitou FIM
    int fim(char str[1000]){ 
        if (str[0]== 'F' && str[1]== 'I' && str[2] == 'M'){
            return 1;
        } 
        return 0;
    }
    
   int main(){
        char str[1000];
        int resp = 0;
        //Roda o código enquanto o usuário não digitar FIM
        while(resp == 0){

           scanf(" %[^\n]", str);;
           resp = fim(str);

           if(resp == 0){
            //Imprime se vogais
            if(vogais(str)== 1){
                printf("SIM ");
            } else {
                printf("NAO ");
            }
            //Imprime se consoante
            if(consoantes(str)== 1){
                printf("SIM ");
            } else {
                printf("NAO ");
            }
            //Imprime se inteiro
            if(inteiros(str)== 1){
                printf("SIM ");
            } else {
                printf("NAO ");
            }
            //Imprime se real
            if(reais(str)== 1){
                printf("SIM ");
            } else {
                printf("NAO ");
            }

            printf("\n");

           }
        }
    }
