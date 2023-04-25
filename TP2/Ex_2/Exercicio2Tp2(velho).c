/**
 * @author Henrique Pato Magalhães
 * @since 12/03/2023
 * @version 12/03/2023
 */

// Bibliotecas
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>


// Definicoes
#define MAX_STRING 500
#define MAX_ATRIBUTO 50

// Struct personagem
typedef struct personagem
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

} personagem;

// Prototipo
int fim(char *pal);
void new_Personagem(personagem *per);
personagem clonar(personagem *per);
void Imprime(personagem *per);
void ler(personagem *per, char *ender);
char *substring(char *str, int start, int end);
int contains(char *str, char compared);
char *removeVirgula(char *str);
int equals(char *str, char *compared);


// Main
int main()
{

    char ender[MAX_STRING];
    personagem per;
    scanf(" %s", ender);

    while (fim(ender) == 0)
    {
        new_Personagem();
        ler(&per, ender);
        Imprime(&per);
        scanf(" %s", ender);
    }

    return 0;
}

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

// Contrutor vazio
void new_Personagem(personagem *per)
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
personagem clonar(personagem *per)
{
    personagem clone;

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
void Imprime(personagem *per)
{
    printf(" ## %s ## %d ## ", per->nome, per->altura);
    if (ceilf(per->peso) == per->peso)
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
void ler(personagem *per, char *ender)
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

            length = i ;
            // Pega o valor do atributo
            substring(artibuto[j], texto, startIndex, length);

            j++;
            i++;
            aspas = 0;
        }

        if (texto[i] == '\'') // conta aspas
            aspas++;
    }

    // Atribuindo valores
    strcpy(per->nome, atributo[0]);
    strcpy(per->corDoCabelo, atributo[3]);
    strcpy(per->corDaPele, atributo[4]);
    strcpy(per->corDosOlhos, atributo[5]);
    strcpy(per->anoNascimento, atributo[6]);
    strcpy(per->genero, atributo[7]);
    strcpy(per->homeworld, atributo[8]);

    if (contains(atributo[2], ',') == 1)
        tPeso = removeVirgula(tPeso);

    if (equals(atributo[1], "unknown") == 1)
        per->altura = 0;
    else
        per->altura = atoi(atributo[1]);

    if (equals(atributo[2], "unknown") == 1)
        per->peso = 0;
    else
        per->peso = atof(atributo[2]);

    fclose(arq);
}

void substring(char *resp char *str, int start, int end)
{
    int j = 0;

    for (int i = start; i < end; i++)
    {
        resp[j] = str[i];
        j++;
    }

    return &resp;
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

char *removeVirgula(char *str)
{
    char resp[MAX_ATRIBUTO];
    int j = 0;

    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] != ',')
        {
            resp[j] = str[i];
            j++;
        }
    }
    return &resp;
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
