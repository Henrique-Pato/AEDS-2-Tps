/** 
 * @author Henrique Pato Magalhães
 * @since 22/02/2023
 * @version 22/02/2023
 */

#include <stdio.h>
#include <string.h>
//Metodo que verifica se a string inserida é um palindromo
int palindromo(char pal[1000]) {
  for (int i = strlen(pal) - 1, j = 0; i != 0; i--, j++) {
    if (pal[i] != pal[j]) {
      //Retorna 0 como falso
      return 0;

    } else {
      //retorna 1 como true
      return 1;
    }
  }
}
//Metodo que verifica se o usuario digitou FIM
int fim(char pal[1000]) {
  if (pal[0] == 'F' && pal[1] == 'I' && pal[2] == 'M') {
    return 1;
  } else {
    return 0;
  }
}

int main() {
  //Variáveis
  char pal[1000];
  int resp, fin = 0;
  //Mantem o código rodando enquanto FIM não foi digitado
  while (fin != 1) {

    scanf(" %[^\n]", pal);
    resp = palindromo(pal);
    fin = fim(pal);
    //Encerra o código se foi digitado FIM
    if (fin == 0) {
      //Imprime NAO e SIM dependendo de se a strin é um palindromo ou não
      if (resp == 0) {
        printf("NAO\n");
      } else {
        printf("SIM\n");
      }
    }
  }
  return 0;
}