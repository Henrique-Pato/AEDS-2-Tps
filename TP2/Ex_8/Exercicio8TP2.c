/**
 * @author Henrique Pato Magalhães
 * @since 21/03/2023
 * @version 22/03/2023
 */

// Bibliotecas
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

// Definicoes
#define MAX_STRING 500
#define MAX_ATRIBUTO 50
#define MAXTAM 100
#define bool short
#define true 1
#define false 0
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
// Variaveis globais da Lista
Personagem array[MAXTAM]; // Elementos da pilha
int n;                    // Quantidade de array.
int comp = 0;
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
int pesquisaBin(char* tex);
//----------------------------------------------------------------------------------------------------------------------------------------------
// Main
int main()
{
    double time = 0.0;
    clock_t begin = clock(); // tempo de inicio
    FILE *logFile = fopen("matricula_binaria.txt", "w");
    char ender[MAX_STRING];
    Personagem per;
    int resp;
    scanf(" %s", ender);

    while (fim(ender) == 0)
    {
        new_Personagem(&per);
        ler(&per, ender);
        inserirFim(&per);
        scanf(" %s", ender);
    }
    scanf(" %[^\n]", ender);

    while (fim(ender) == 0)
    {
        resp = pesquisaBin(ender);
        if (resp == 1)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        scanf(" %[^\n]", ender);
    }
    clock_t end = clock();                          // tempo de fim
    time += (double)(end - begin) / CLOCKS_PER_SEC; // calcula tempo de execucao
    fprintf(logFile, "747534\t%i\t%lf", comp, time);
    fclose(logFile);
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
    if ((per->peso - (int)per->peso) == 0)
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
void salvaAtributo(int j, char *atributo, Personagem *per)
{
    switch (j)
    {
    case 0:
        strcpy(per->nome, atributo);
        break;
    case 1:
    {
        if (equals(atributo, "unknown") == 1)
            per->altura = 0;
        else
            per->altura = atoi(atributo);

        break;
    }
    case 2:
    {
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
    char sub[MAX_ATRIBUTO] = "";
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

    str[strlen(str) - 1] = '\0';
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
//Lista
// Inicializacoes

void start()
{
    for (int i = 0; i < MAXTAM; i++)
    {
        new_Personagem(&array[i]);
    }
    n = 0;
}

// Inserir inicio
void inserirInicio(Personagem *x)
{
    int i;

    // Validar insercao
    if (n >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    // levar elementos para o fim do array
    for (i = n; i > 0; i--)
    {
        array[i] = array[i - 1];
    }

    array[0] = *x;
    n++;
}

// Inserir fim
void inserirFim(Personagem *x)
{

    // validar insercao
    if (n >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    array[n] = *x;
    n++;
}

// Inserir posicao
void inserir(Personagem *x, int pos)
{
    int i;

    // validar insercao
    if (n >= MAXTAM || pos < 0 || pos > n)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    // levar elementos para o fim do array
    for (i = n; i > pos; i--)
    {
        array[i] = array[i - 1];
    }

    array[pos] = *x;
    n++;
}

// Remover inicio
Personagem removerInicio()
{
    int i;
    Personagem resp;

    // validar remocao
    if (n == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = array[0];
    n--;
    printf("(R) %s\n", resp.nome);

    for (i = 0; i < n; i++)
    {
        array[i] = array[i + 1];
    }

    return resp;
}

// Remover fim
Personagem removerFim()
{

    // validar remocao
    if (n == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }
    printf("(R) %s\n", array[n - 1].nome);
    return array[--n];
}

// Remover posicao
Personagem remover(int pos)
{
    int i;
    Personagem resp;

    // validar remocao
    if (n == 0 || pos < 0 || pos >= n)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = array[pos];
    n--;
    printf("(R) %s\n", resp.nome);

    for (i = pos; i < n; i++)
    {
        array[i] = array[i + 1];
    }

    return resp;
}

// Mostrar lista
void mostrar()
{
    int i;

    for (i = 0; i < n; i++)
    {
        printf("[%d] ", i);
        Imprime(&array[i]);
    }
}

int pesquisaBin(char* tex)
{
    int resp = 0;
    int dir = n, esq = 0, meio;

    while (esq <= dir)
    {
        meio = (esq + dir) / 2;
        comp++;
        if (equals(tex, array[meio].nome))
        {
            resp = 1;
            esq = dir + 1;
        }
        else
        {
            comp++;
            if (strcmp(tex, array[meio].nome) > 0)
            {
                esq = meio + 1;
            }
            else
            {
                dir = meio - 1;
            }
        }
    }
        return resp;
}
