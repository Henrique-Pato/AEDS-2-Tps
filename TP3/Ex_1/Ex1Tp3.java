import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

/**
 * @author Henrique Pato Magalhães
 * @since 11/04/2023
 * @version 11/04/2023
 */

// ----------------------------------------------------------------------------------------------------------------------------------------------
class Ex1Tp3 {

    // Main
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ender, Tex;
        int rep;
        Lista perLista = new Lista();

        ender = br.readLine();

        // Repeticao
        while (Fim(ender) == false) {
            Personagens per = new Personagens();
            per.Ler(ender);
            perLista.inserirFim(per);
            ender = br.readLine();
        }

        rep = Integer.parseInt(br.readLine());

        for (int i = 0; i < rep; i++) {
            Tex = br.readLine();

            if (Tex.charAt(0) == 'R' && Tex.charAt(1) == 'I') {
                Personagens per2 = new Personagens();
                per2 = perLista.removerInicio();
                System.out.println("(R) " + per2.getNome());
            } else if (Tex.charAt(0) == 'R' && Tex.charAt(1) == 'F') {
                Personagens per2 = new Personagens();
                per2 = perLista.removerFim();
                System.out.println("(R) " + per2.getNome());
            } else if (Tex.charAt(0) == 'R' && Tex.charAt(1) == '*') {
                Personagens per2 = new Personagens();
                per2 = perLista.remover(Integer.parseInt(Tex.substring(3)));
                System.out.println("(R) " + per2.getNome());
            } else if (Tex.charAt(0) == 'I' && Tex.charAt(1) == 'I') {
                Personagens per2 = new Personagens();
                per2.Ler(Tex.substring(3));
                perLista.inserirInicio(per2);
            } else if (Tex.charAt(0) == 'I' && Tex.charAt(1) == 'F') {
                Personagens per2 = new Personagens();
                per2.Ler(Tex.substring(3));
                perLista.inserirFim(per2);
            }else if (Tex.charAt(0) == 'I' && Tex.charAt(1) == '*') {
                Personagens per2 = new Personagens();
                per2.Ler(Tex.substring(6));
                perLista.inserir(per2, Integer.parseInt(Tex.substring(3, 5)));
            }

        }

        perLista.mostrar();

        br.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------
    // Verifica se foi digitado FIM
    private static boolean Fim(String x) {
        boolean resp = false;

        if (x.equals("FIM")) {
            resp = true;
        }

        return resp;
    }
}

// Classe personagens
// -----------------------------------------------------------------------------------------------------------------------------------------------
class Personagens {
    // Atributos
    private int altura;
    private double peso;
    private String nome, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld;

    // Contrutor vazio
    public Personagens() {
        this.nome = this.corDoCabelo = this.corDaPele = this.corDosOlhos = this.anoNascimento = this.genero = this.homeworld = null;
        this.altura = 0;
        this.peso = 0;
    }

    // Construtor com valores informados
    public Personagens(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos,
            String anoDeNascimento, String genero, String homeworld) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = corDoCabelo;
        this.corDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoDeNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    // Sets
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    // Gets
    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public String getCorDoCabelo() {
        return corDoCabelo;
    }

    public String getCorDaPele() {
        return corDaPele;
    }

    public String getCorDosOlhos() {
        return corDosOlhos;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public String getHomeworld() {
        return homeworld;
    }

    // Clone
    public Personagens Clone() {
        // Cria um novo objeto personagens
        Personagens clonado = new Personagens();

        // Copia seus atributos
        clonado.nome = this.nome;
        clonado.altura = this.altura;
        clonado.peso = this.peso;
        clonado.corDoCabelo = this.corDoCabelo;
        clonado.corDaPele = this.corDaPele;
        clonado.corDosOlhos = this.corDosOlhos;
        clonado.anoNascimento = this.anoNascimento;
        clonado.genero = this.genero;
        clonado.homeworld = this.homeworld;

        // Retorna o Clone
        return clonado;
    }

    // Imprime
    public void Imprimir() {
        System.out.print(" ## " + nome + " ## " + altura + " ## ");

        if ((peso % 1) == 0) {
            System.out.print((int) peso);
        } else {
            System.out.printf(Locale.ENGLISH, "%.1f", peso);
        }

        System.out.println(" ## " + corDoCabelo + " ## " + corDaPele + " ## " + corDosOlhos + " ## " + anoNascimento
                + " ## " + genero + " ## " + homeworld + " ## ");
    }

    // Ler
    public void Ler(String ender) {

        try {
            FileReader fr = new FileReader(ender);
            BufferedReader br = new BufferedReader(fr);
            String texto, tAlt, tPeso;
            String[] linhas;

            texto = br.readLine();

            // Divide a string em varias strings com os atributos esperados
            linhas = texto.split("',");

            // Retira as partes desnecessarias das strings
            this.nome = linhas[0].substring(10, linhas[0].length());
            tAlt = linhas[1].substring(12, linhas[1].length());
            tPeso = linhas[2].substring(10, linhas[2].length());
            this.corDoCabelo = linhas[3].substring(16, linhas[3].length());
            this.corDaPele = linhas[4].substring(16, linhas[4].length());
            this.corDosOlhos = linhas[5].substring(15, linhas[5].length());
            this.anoNascimento = linhas[6].substring(16, linhas[6].length());
            this.genero = linhas[7].substring(12, linhas[7].length());
            this.homeworld = linhas[8].substring(15, linhas[8].length());

            if (tPeso.contains(",")) {
                tPeso = tPeso.replace(",", "");
            }

            // Transforma as strings em int/double
            if (tAlt.equals("unknown")) {
                this.altura = 0;
            } else {
                this.altura = Integer.parseInt(tAlt);
            }

            if (tPeso.equals("unknown")) {
                this.peso = 0;
            } else {
                this.peso = Double.parseDouble(tPeso);
            }

            br.close();
        }

        catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

// Celula
// ----------------------------------------------------------------------------------------------------------------------------------------------
class Celula {
    public Personagens elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    // Construtor da classe
    public Celula() {
        this.elemento = null;
        this.prox = null;
    }

    // Construtor da classe.
    public Celula(Personagens x) {
        this.elemento = x;
        this.prox = null;
    }

}

// Lista
// --------------------------------------------------------------------------------------------------------------
class Lista {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma fila sem elementos.
     */
    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    // Inserir personagens no inicio
    public void inserirInicio(Personagens x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    // Inserir personagens no fim
    public void inserirFim(Personagens x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    // Isnserir em qualquer posicao
    public void inserir(Personagens x, int pos) throws Exception {

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    // Remover no inicio
    public Personagens removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagens resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    // Remover no fim
    public Personagens removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        // Caminhar ate a penultima celula:
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Personagens resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    // Remover em qualquer posicao
    public Personagens remover(int pos) throws Exception {
        Personagens resp;
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho - 1) {
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return resp;
    }

    // Mostrar elementos
    public void mostrar() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print("[" + j + "] ");
            i.elemento.Imprimir();
            j++;
        }
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

}