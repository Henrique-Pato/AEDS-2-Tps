/**
 * @author Henrique Pato Magalhães
 * @since 06/04/2023
 * @version 06/04/2023
 */

// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Definicoes
#define MAX_STRING 500
#define MAX_ATRIBUTO 50
#define MAXTAM 100
//----------------------------------------------------------------------------------------------------------------------------------------------
// Struct personagem
typedef struct {
  int altura;
  float peso;
  char nome[MAX_ATRIBUTO], corDoCabelo[MAX_ATRIBUTO], corDaPele[MAX_ATRIBUTO],
      corDosOlhos[MAX_ATRIBUTO], anoNascimento[MAX_ATRIBUTO],
      genero[MAX_ATRIBUTO], homeworld[MAX_ATRIBUTO];

} Personagem;
// Variaveis globais de Lista
Personagem array[MAXTAM]; // Elementos da pilha
int n;                    // Quantidade de array.
int comp = 0, mov = 0, k = 10;
//----------------------------------------------------------------------------------------------------------------------------------------------
// Cabecalhos
int fim(char *pal);
void new_Personagem(Personagem *per);
Personagem clonar(Personagem *per);
void Imprime(Personagem *per);
void ler(Personagem *per, char *ender);
void salvaAtributo(int j, char *atributo, Personagem *per);
void substring(char *resp, char *str, int start, int length);
int contains(char *str, char compared);
void removeVirgula(char *str);
int equals(char *str, char *compared);
void start();
void inserirInicio(Personagem *x);
void inserirFim(Personagem *x);
void inserir(Personagem *x, int pos);
Personagem removerInicio();
Personagem removerFim();
Personagem remover(int pos);
void mostrar();
void sort();
void construir(Personagem *arraytmp, int tamHeap);
void reconstruir(Personagem *arrayTmp, int tamHeap);
int getMaiorFilho(Personagem *arrayTmp, int i, int tamHeap);
void swap(int i, int j);
//----------------------------------------------------------------------------------------------------------------------------------------------
// Main
int main() {
  double time = 0.0;
  clock_t begin = clock(); // tempo de inicio
  FILE *logFile = fopen("matricula_heapsort_parcial.txt", "w");

  char ender[MAX_STRING];
  Personagem per;
  scanf(" %s", ender);

  while (fim(ender) == 0) {
    new_Personagem(&per);
    ler(&per, ender);
    inserirFim(&per);
    scanf(" %s", ender);
  }

  sort();
  mostrar();

  clock_t end = clock();                          // tempo de fim
  time += (double)(end - begin) / CLOCKS_PER_SEC; // calcula tempo de execucao
  fprintf(logFile, "747534\t%i\t%i\t%lf", comp, mov, time);
  fclose(logFile);
  return 0;
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Funcao Fim
int fim(char *pal) {
  if (pal[0] == 'F' && pal[1] == 'I' && pal[2] == 'M') {
    return 1;
  } else {
    return 0;
  }
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Contrutor vazio
void new_Personagem(Personagem *per) {
  strcpy(per->nome, "");
  strcpy(per->corDoCabelo, "");
  strcpy(per->corDaPele, "");
  strcpy(per->corDosOlhos, "");
  strcpy(per->anoNascimento, "");
  strcpy(per->genero, "");
  strcpy(per->homeworld, "");

  per->altura = -1;
  per->peso = -1;
}

// Clone
Personagem clonar(Personagem *per) {
  Personagem clone;

  strcpy(clone.nome, per->nome);
  strcpy(clone.corDoCabelo, per->corDoCabelo);
  strcpy(clone.corDaPele, per->corDaPele);
  strcpy(clone.corDosOlhos, per->corDosOlhos);
  strcpy(clone.anoNascimento, per->anoNascimento);
  strcpy(clone.genero, per->genero);
  strcpy(clone.homeworld, per->homeworld);

  clone.altura = per->altura;
  clone.peso = per->peso;
}

// Imprimir
void Imprime(Personagem *per) {
  printf(" ## %s ## %d ## ", per->nome, per->altura);
  if ((per->peso - (int)per->peso) == 0) {
    printf("%.0f", per->peso);
  } else {
    printf("%.1f", per->peso);
  }
  printf(" ## %s ## %s ## %s ## %s ## %s ## %s ## \n", per->corDoCabelo,
         per->corDaPele, per->corDosOlhos, per->anoNascimento, per->genero,
         per->homeworld);
}

// Ler arquivo
void ler(Personagem *per, char *ender) {
  // Abre o arquivo para leitura
  FILE *arq = fopen(ender, "r");
  char texto[MAX_STRING];
  char atributo[MAX_ATRIBUTO][9];
  int aspas = 0;
  int j = 0;

  // Salva o conteudo do arquivo em um vetor de char
  fgets(texto, MAX_STRING, arq);

  for (int i = 0; j < 9; i++) { // Percorre pela linha c/atributos
    int length, startIndex;

    if (aspas == 3) { // Inicio do valor de um atributo
      startIndex = i;

      while (texto[i] != '\'') { // Procura aspas q fecha valor
        i++;
      }

      length = i;
      // Pega o valor do atributo
      substring(atributo[j], texto, startIndex, length);

      // Salva atributo no personagem
      salvaAtributo(j, atributo[j], per);

      j++;
      i++;
      aspas = 0;
    }

    if (texto[i] == '\'') // conta aspas
      aspas++;
  }

  fclose(arq);
}
void salvaAtributo(int j, char *atributo, Personagem *per) {
  switch (j) {
  case 0:
    strcpy(per->nome, atributo);
    break;
  case 1: {
    if (equals(atributo, "unknown") == 1)
      per->altura = 0;
    else
      per->altura = atoi(atributo);

    break;
  }
  case 2: {
    if (contains(atributo, ',') == 1)
      removeVirgula(atributo);

    if (equals(atributo, "unknown") == 1)
      per->peso = 0;
    else
      per->peso = atof(atributo);
    break;
  }
  case 3:
    strcpy(per->corDoCabelo, atributo);
    break;
  case 4:
    strcpy(per->corDaPele, atributo);
    break;
  case 5:
    strcpy(per->corDosOlhos, atributo);
    break;
  case 6:
    strcpy(per->anoNascimento, atributo);
    break;
  case 7:
    strcpy(per->genero, atributo);
    break;
  case 8:
    strcpy(per->homeworld, atributo);
    break;
  default:
    break;
  }
}
void substring(char *resp, char *str, int start, int length) {
  char sub[MAX_ATRIBUTO] = "";
  int j = 0;

  for (int i = start; i < length; i++) {
    sub[j] = str[i];
    j++;
  }

  strcpy(resp, sub);
}

int contains(char *str, char compared) {
  int resp = 0;
  for (int i = 0; i < strlen(str); i++) {
    if (str[i] == compared) {
      i = strlen(str);
      resp = 1;
    }
  }
  return resp;
}

void removeVirgula(char *str) {
  int j = 0;

  for (int i = 0; i < strlen(str); i++) {
    if (str[i] != ',') {
      str[j] = str[i];
      j++;
    }
  }

  str[strlen(str) - 1] = '\0';
}

int equals(char *str, char *compared) {
  int resp = 1;
  int i = 0;
  while (i < strlen(str) && resp == 1) {
    if (str[i] != compared[i]) {
      resp = 0;
    }
    i++;
  }
  return resp;
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Lista

// Inicializacoes

void start() {
  for (int i = 0; i < MAXTAM; i++) {
    new_Personagem(&array[i]);
  }
  n = 0;
}

// Inserir inicio
void inserirInicio(Personagem *x) {
  int i;

  // Validar insercao
  if (n >= MAXTAM) {
    printf("Erro ao inserir!");
    exit(1);
  }

  // levar elementos para o fim do array
  for (i = n; i > 0; i--) {
    array[i] = array[i - 1];
  }

  array[0] = *x;
  n++;
}

// Inserir fim
void inserirFim(Personagem *x) {

  // validar insercao
  if (n >= MAXTAM) {
    printf("Erro ao inserir!");
    exit(1);
  }

  array[n] = *x;
  n++;
}

// Inserir posicao
void inserir(Personagem *x, int pos) {
  int i;

  // validar insercao
  if (n >= MAXTAM || pos < 0 || pos > n) {
    printf("Erro ao inserir!");
    exit(1);
  }

  // levar elementos para o fim do array
  for (i = n; i > pos; i--) {
    array[i] = array[i - 1];
  }

  array[pos] = *x;
  n++;
}

// Remover inicio
Personagem removerInicio() {
  int i;
  Personagem resp;

  // validar remocao
  if (n == 0) {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[0];
  n--;
  printf("(R) %s\n", resp.nome);

  for (i = 0; i < n; i++) {
    array[i] = array[i + 1];
  }

  return resp;
}

// Remover fim
Personagem removerFim() {

  // validar remocao
  if (n == 0) {
    printf("Erro ao remover!");
    exit(1);
  }
  printf("(R) %s\n", array[n - 1].nome);
  return array[--n];
}

// Remover posicao
Personagem remover(int pos) {
  int i;
  Personagem resp;

  // validar remocao
  if (n == 0 || pos < 0 || pos >= n) {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[pos];
  n--;
  printf("(R) %s\n", resp.nome);

  for (i = pos; i < n; i++) {
    array[i] = array[i + 1];
  }

  return resp;
}

// Mostrar lista
void mostrar() {
  int i;

  for (i = 0; i < k; i++) {
    Imprime(&array[i]);
  }
}

// Heapsort parcial
void sort() {
  Personagem arrayTmp[n + 1];
  for (int i = 0; i < n; i++) {
    arrayTmp[i + 1] = array[i];
  }

  // Contrucao do heap
  for (int tamHeap = 2; tamHeap <= k; tamHeap++) {
    construir(arrayTmp, tamHeap);
  }

  for (int i = k + 1; i <= n; i++)
    if (arrayTmp[i].altura < arrayTmp[1].altura || arrayTmp[i].altura == arrayTmp[1].altura && strcmp(arrayTmp[i].nome, arrayTmp[1].nome) < 0) {
      swap(i, 1);
      reconstruir(arrayTmp, k);
    }

  // Ordenacao propriamente dita
  int tamHeap = k;
  while (tamHeap > 1) {
    swap(1, tamHeap);
    mov += 3;
    tamHeap--;
    reconstruir(arrayTmp, tamHeap);
  }

  // Alterar o vetor para voltar a posicao zero
  for (int i = 0; i < n; i++) {
    array[i] = arrayTmp[i + 1];
  }
}

void construir(Personagem *arrayTmp, int tamHeap) {
  for (int i = tamHeap; i > 1 && arrayTmp[i].altura > arrayTmp[i / 2].altura || arrayTmp[i].altura == arrayTmp[i / 2].altura && strcmp(arrayTmp[i].nome, arrayTmp[i/2].nome) > 0; i /= 2) {
    comp++;
    swap(i, i / 2);
    mov += 3;
  }
}

void reconstruir(Personagem *arrayTmp, int tamHeap) {
  int i = 1;
  while (i <= (tamHeap / 2)) {
    int filho = getMaiorFilho(arrayTmp, i, tamHeap);
    comp++;
    if (arrayTmp[i].altura < arrayTmp[filho].altura || arrayTmp[i].altura == arrayTmp[filho].altura && strcmp(arrayTmp[i].nome, arrayTmp[filho].nome) < 0) {
      swap(i, filho);
      mov += 3;
      i = filho;
    } else {
      i = tamHeap;
    }
  }
}

int getMaiorFilho(Personagem *arrayTmp, int i, int tamHeap) {
  int filho;
  if (2 * i == tamHeap || arrayTmp[2 * i].altura > arrayTmp[2 * i + 1].altura) {
    comp++;
    filho = 2 * i;
  } else {
    filho = 2 * i + 1;
  }
  return filho;
}

void swap(int i, int j) {
  Personagem tmp = array[i];
  array[i] = array[j];
  array[j] = tmp;
}