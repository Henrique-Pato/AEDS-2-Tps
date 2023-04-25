import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Henrique Pato Magalhães
 * @since 11/04/2023
 * @version 11/04/2023
 */

class Ex5Tp3 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rep;
    }

    // Celula
    // ----------------------------------------------------------------------------------------------------------------
    class Celula {
        public int elemento;
        public Celula inf, sup, esq, dir;

        public Celula() {
            this(0);
        }

        public Celula(int elemento) {
            this.elemento = elemento;
            this.dir = this.esq = this.sup = this.inf = null;
        }
        
    }
    
    // Matriz
    // -------------------------------------------------------------------------------------------------------------
    class Matriz {
        public Celula inicio;
        public int linha, coluna;

        public Matriz() {
            this(3, 3);
        }

        public Matriz(int linha, int coluna) {
            if (linha == 0 || coluna == 0) {
                // erro
            } else {
                this.linha = linha;
                this.coluna = coluna;
                this.inicio = new Celula();
                Celula tmp = inicio;
                for (int i = 1; i < linha; i++) {
                    tmp.dir = new Celula();
                    tmp.dir.esq = tmp;
                    tmp = tmp.dir;
                }
                tmp = inicio;
                for (int i = 1; i < coluna; i++) {
                    tmp.inf = new Celula();
                    tmp.inf.sup = tmp;
                    tmp = tmp.inf;
                }
                Celula T1, T2;
                T1 = inicio;
                for (int i = 1; i < coluna; i++) {
                    T1 = T1.inf;
                    T2 = T1;
                    for (int j = 1; j < linha; j++) {
                        T2.dir = new Celula();
                        T2.dir.esq = T2;
                        T2.dir.sup = T2.sup.dir;
                        T2.dir.sup.inf = T2.dir;
                        T2 = T2.dir;
                    }
                }

            }
        }

        public void inserir(int linhas, int colunas, int x){
            if(linhas <= 0 || colunas <= 0 || linhas > this.linha || colunas > this.coluna){
                //Erro
            } else {
                Celula tmp = inicio;
                for(int i = 1; i < linhas; i++){
                    tmp = tmp.dir;
                }
                for(int i = 1; i < colunas; i++){
                    tmp = tmp.inf;
                }
                tmp.elemento = x;
            }
        }

        public Matriz soma(Matriz m) {
            Matriz resp = null;

            if (this.linha == m.linha && this.coluna == m.coluna) {
                resp = new Matriz(this.linha, this.coluna);
                Celula tr1, tr2, tt1, tt2, tm1, tm2;
                tr1 = resp.inicio;
                tt1 = this.inicio;
                tm1 = m.inicio;
                for (int i = 1; i < coluna; i++) {
                    tr2 = tr1;
                    tt2 = tt1;
                    tm2 = tm1;
                    tr1 = tr1.inf;
                    tt1 = tt1.inf;
                    tm1 = tm1.inf;
                    for (int j = 1; j < linha; j++) {
                        tr2.elemento = tt2.elemento + tm2.elemento;
                        tr2 = tr2.dir;
                        tt2 = tt2.dir;
                        tm2 = tm2.dir;
                    }
                }
            }

            return resp;
        }

        public Matriz multiplicacao(Matriz m) {
            Matriz resp = null;

            if (this.coluna == m.linha) {
                resp = new Matriz(this.linha, this.coluna);
                Celula tr1, tr2, tt1, tt2, tm1, tm2;
                tr1 = resp.inicio;
                tt1 = this.inicio;
                tm1 = m.inicio;
                for (int i = 1; i < coluna; i++) {
                    tr2 = tr1;
                    tt2 = tt1;
                    tm2 = tm1;
                    tr1 = tr1.inf;
                    tt1 = tt1.inf;
                    tm1 = tm1.inf;
                    for (int j = 1; j < linha; j++) {
                        tr2.elemento = (tt2.elemento * tm2.elemento) + (tt2.elemento * tm2.inf.elemento);
                        tr2 = tr2.dir;
                        tt2 = tt2.dir;
                        tm2 = tm2.dir;
                    }
                }
            }

            return resp;
        }

        public boolean isQuadrada() {
            boolean resp = false;
            if (this.linha == this.coluna) {
                resp = true;
            }
            return resp;
        }

        public void mostrarDiagonalPrincipal() {
            if (isQuadrada() == true) {
                Celula tmp = inicio;
                for (int i = 1; i < linha; i++) {
                    System.out.println(tmp.elemento + " ");
                    tmp = tmp.dir;
                    tmp = tmp.inf;
                }
            }
        }

        public void mostrarDiagonalSecundaria() {
            if (isQuadrada() == true) {
                Celula tmp = inicio;
                for (int i = 1; i < linha; i++) {
                    tmp = tmp.dir;
                }
                for (int i = 1; i < linha; i++) {
                    System.out.println(tmp.elemento + " ");
                    tmp = tmp.esq;
                    tmp = tmp.inf;
                }
            }
        }
    }
}
