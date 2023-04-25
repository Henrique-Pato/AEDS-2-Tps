/**
 * @author Henrique Pato Magalhães
 * @since 23/03/2023
 * @version 23/03/2023
 */

// Bibliotecas
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Definicoes
#define MAX_STRING 500
#define MAX_ATRIBUTO 50
#define MAXTAM    5
#define bool      short
#define true      1
#define false     0
//----------------------------------------------------------------------------------------------------------------------------------------------
// Struct personagem
typedef struct 
{
    int altura;
    float peso;
    char nome[MAX_ATRIBUTO],
        corDoCabelo[MAX_ATRIBUTO],
        corDaPele[MAX_ATRIBUTO],
        corDosOlhos[MAX_ATRIBUTO],
        anoNascimento[MAX_ATRIBUTO],
        genero[MAX_ATRIBUTO],
        homeworld[MAX_ATRIBUTO];

} Personagem;
//----------------------------------------------------------------------------------------------------------------------------------------------
// Cabecalhos
int fim(char *pal);
void new_Personagem(Personagem *per);
Personagem clonar(Personagem *per);
void Imprime(Personagem *per);
void ler(Personagem *per, char *ender);
void salvaAtributo(int j, char* atributo, Personagem* per);
void substring(char *resp, char *str, int start, int length);
int contains(char *str, char compared);
void removeVirgula(char *str);
int equals(char *str, char *compared);
void start();
void inserir(Personagem* x);
Personagem remover();
void mostrar ();
int media();
bool isVazia();

//----------------------------------------------------------------------------------------------------------------------------------------------
// Main
int main()
{

    char ender[MAX_STRING];
    Personagem per;
    int rep;
    scanf(" %s", ender);

    while (fim(ender) == 0)
    {
        new_Personagem(&per);
        ler(&per, ender);
        inserir(&per);
        printf("%d\n", media());
        scanf(" %s", ender);
    }
   
    scanf("%d", &rep);

    for(int i = 0; i < rep; i++){
         scanf(" %[^\n]", ender);

        if(ender[0] == 'R'){
            Personagem per2 = new_Personagem();
            per2 = remover();
            printf("(R) %s\n", per2.nome);
        } else if(ender[0] == 'I'){
                char resp[MAX_ATRIBUTO];
                substring(resp, ender, 2, strlen(ender));
                ler(&per, resp);
                inserir(&per);
                printf("%d\n", media());
            } 
        }
    return 0;
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Funcao Fim
int fim(char *pal)
{
    if (pal[0] == 'F' && pal[1] == 'I' && pal[2] == 'M')
    {
        return 1;
    }
    else
    {
        return 0;
    }
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Contrutor vazio
void new_Personagem(Personagem *per)
{
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
Personagem clonar(Personagem *per)
{
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
void Imprime(Personagem *per)
{
    printf(" ## %s ## %d ## ", per->nome, per->altura);
    if ( (per->peso - (int)per->peso)  == 0 )
    {
        printf("%.0f", per->peso);
    }
    else
    {
        printf("%.1f", per->peso);
    }
    printf(" ## %s ## %s ## %s ## %s ## %s ## %s ## \n", per->corDoCabelo, per->corDaPele, per->corDosOlhos, per->anoNascimento, per->genero, per->homeworld);
}

// Ler arquivo
void ler(Personagem *per, char *ender)
{
    // Abre o arquivo para leitura
    FILE *arq = fopen(ender, "r");
    char texto[MAX_STRING];
    char atributo[MAX_ATRIBUTO][9];
    int aspas = 0;
    int j = 0;

    // Salva o conteudo do arquivo em um vetor de char
    fgets(texto, MAX_STRING, arq);

    for (int i = 0; j < 9; i++)
    { // Percorre pela linha c/atributos
        int length, startIndex;

        if (aspas == 3)
        { // Inicio do valor de um atributo
            startIndex = i;

            while (texto[i] != '\'')
            { // Procura aspas q fecha valor
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
void salvaAtributo(int j, char* atributo, Personagem* per){
    switch(j) {
        case 0:
            strcpy(per->nome, atributo);
            break;
        case 1:{
            if (equals(atributo, "unknown") == 1)
                per->altura = 0;
            else
                per->altura = atoi(atributo);
        
            break;
        } 
        case 2:{
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
void substring(char *resp, char *str, int start, int length)
{
    char sub[MAX_ATRIBUTO] =  "";
    int j = 0;

    for (int i = start; i < length; i++)
    {
        sub[j] = str[i];
        j++;
    }

    strcpy(resp, sub);
}

int contains(char *str, char compared)
{
    int resp = 0;
    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == compared)
        {
            i = strlen(str);
            resp = 1;
        }
    }
    return resp;
}

void removeVirgula(char *str)
{
    int j = 0;

    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] != ',')
        {
            str[j] = str[i];
            j++;
        }
    } 

    str[strlen(str)-1] = '\0';
}

int equals(char *str, char *compared)
{
    int resp = 1;
    int i = 0;
    while (i < strlen(str) && resp == 1)
    {
        if (str[i] != compared[i])
        {
            resp = 0;
        }
        i++;
    }
    return resp;
}
//----------------------------------------------------------------------------------------------------------------------------------------------
// Fila
Personagem array[MAXTAM+1];   // Elementos da pilha 
int primeiro;          // Remove do indice "primeiro".
int ultimo;            // Insere no indice "ultimo".
int soma=0, count=0;

 // Inicializacoes
void start(){
   primeiro = ultimo = 0;
}
// Inserir
void inserir(Personagem* x) {
   //validar insercao
   if (((ultimo + 1) % MAXTAM) == primeiro) {
      remover();
   }
   soma = soma + x->altura;
   count++;
   array[ultimo] = *x;
   ultimo = (ultimo + 1) % MAXTAM;
}

// Remover
Personagem remover() {

   //validar remocao
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
      exit(1);
   }
   count--;
   Personagem resp = array[primeiro];
   soma = soma - resp.altura;
   primeiro = (primeiro + 1) % MAXTAM;
   return resp;
}

// Mostrar fila
void mostrar (){
   int i;

   for(i = 0; i < n; i++){
      printf("[%d] ", i);
      Imprime(&array[i]);
    }

}
// Retorna a média da altura dos personagens inseridos
int media(){
    return soma/count;
}

// Verifica se está vazia
bool isVazia() {
   return (primeiro == ultimo); 
}
