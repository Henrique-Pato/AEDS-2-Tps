import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

/**
 * @author Henrique Pato Magalhães
 * @since 24/03/2023
 * @version 24/03/2023
 */

//----------------------------------------------------------------------------------------------------------------------------------------------    
class Exercicio11TP2 {
    
    // Main
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis(); // tempo de inicio
        FileWriter logFile = new FileWriter("matricula_insercao.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ender;
        Lista perList = new Lista(100);

        ender = br.readLine();

        // Repeticao
        while (Fim(ender) == false) {
            Personagens per = new Personagens();
            per.Ler(ender);
            perList.inserirFim(per);
            ender = br.readLine();
        }

        perList.sort();
        perList.mostrar();

        br.close();
        long end = System.currentTimeMillis(); // tempo de fim
        float time = (end - start); // calcula tempo de execucao
        int comp = perList.getComp();
        int mov = perList.getMov();
        logFile.write("747534\t"+comp+"\t"+mov+"\t"+time);
        logFile.close();
    }

//------------------------------------------------------------------------------------------------------------------------------------------------
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
//-----------------------------------------------------------------------------------------------------------------------------------------------
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

// Lista
//----------------------------------------------------------------------------------------------------------------------------------------------
class Lista {
    private Personagens[] array;
    private int n;
    private int comp=0, mov=0;
  
    // Construtores da classe.
    public Lista () {
       this(6);
    }

    public Lista (int tamanho){
       array = new Personagens[tamanho];
       for(int i = 0; i < tamanho; i++){
        array[i] = new Personagens();
       }
       n = 0;
    }

    public int getComp() {
        return comp;
    }
    
    public int getMov() {
        return mov;
    }

    // Inserir inicio
    public void inserirInicio(Personagens x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       } 
 
       //levar elementos para o fim do array
       for(int i = n; i > 0; i--){
          array[i] = array[i-1];
       }
 
       array[0] = x;
       n++;
    }
 
    // Inserir fim
    public void inserirFim(Personagens x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = x;
       n++;
    }
 
    // Inserir especifico
    public void inserir(Personagens x, int pos) throws Exception {
 
       //validar insercao
       if(n >= array.length || pos < 0 || pos > n){
          throw new Exception("Erro ao inserir!");
       }
 
       //levar elementos para o fim do array
       for(int i = n; i > pos; i--){
          array[i] = array[i-1];
       }
 
       array[pos] = x;
       n++;
    }
 
    // Remover inicio
    public Personagens removerInicio() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagens resp = array[0];
       n--;
 
       for(int i = 0; i < n; i++){
          array[i] = array[i+1];
       }
       System.out.println("(R) "+ resp.getNome());
       return resp;
    }
 
    // Remover fim
    public Personagens removerFim() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
       System.out.println("(R) "+ (array[n-1].getNome())); 
       return array[--n];
    }
 
    // Remover especifico
    public Personagens remover(int pos) throws Exception {
 
       //validar remocao
       if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagens resp = array[pos];
       n--;
 
       for(int i = pos; i < n; i++){
          array[i] = array[i+1];
       }
       System.out.println("(R) "+ resp.getNome());
       return resp;
    }
 
    // Imprimir lista
    public void mostrar (){
       for(int i = 0; i < n; i++){
          array[i].Imprimir();
       } 
    }
 
    // Pesquisa elemento
    public boolean pesquisar(String x) {
       boolean retorno = false;
       for (int i = 0; i < n && retorno == false; i++) {
          retorno = (array[i].getNome() == x);
       }
       return retorno;
    }
    // Insercao
    public void sort() {
		for (int i = 1; i < n; i++) {
		Personagens tmp = array[i];
        int j = i - 1;
        while ((j >= 0) && (array[j].getAnoNascimento().compareTo(tmp.getAnoNascimento()) > 0)) {
            mov++;
            array[j + 1] = array[j];
            j--;
            comp++;
        }  
        mov++;
        array[j + 1] = tmp;
      }
	}

  }
 