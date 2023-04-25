/** 
 * @author Henrique Pato Magalhães
 * @since 15/02/2023
 * @version 22/02/2023
 */

import java.util.*;
import java.io.*;
import java.net.*;

class Tp1Ex8 {
  //Usada para salvar o conteudo do html em uma string
  public static String getHtml(String endereco) {
    URL url;
    InputStream is = null;
    BufferedReader br;
    String resp = "", line;

    try {
      url = new URL(endereco);
      is = url.openStream(); // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        resp += line + "\n";
      }
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    try {
      is.close();
    } catch (IOException ioe) {
      // nothing to see here

    }

    return resp;
  }
  
  //Checa se o algoritimo deve ser encerrado
  public static boolean fim(String x) {
    boolean resp = false;
    if (x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
      resp = true;
    }
    return resp;
  }

  //Main
  public static void main(String[] args) {
    //iniciacao das strings
    String nome, endereco, html;
    Scanner scr = new Scanner(System.in);
    nome = scr.nextLine();
    while (fim(nome) == false) {
      //cria variaveis para salvar a quantidade de vogais/consoantes/etc
      int a = 0, e = 0, i = 0, o = 0, u = 0, a2 = 0, e2 = 0, i2 = 0, o2 = 0, u2 = 0, a3 = 0, e3 = 0, i3 = 0, o3 = 0, u3 = 0, a4 = 0,
          o4 = 0, a5 = 0, e4 = 0, i4 = 0, o5 = 0, u4 = 0, cons = 0, br = 0, table = 0;
      endereco = scr.nextLine();
      html = getHtml(endereco);
      //Repeticao que passa pela string html e conta vogais/etc
      for (int x = 0; x < html.length(); x++) {
        if (html.charAt(x) == 'a') {
          a++;
        }
        if (html.charAt(x) == 'e') {
          e++;
        }
        if (html.charAt(x) == 'i') {
          i++;
        }
        if (html.charAt(x) == 'o') {
          o++;
        }
        if (html.charAt(x) == 'u') {
          u++;
        }
        if (html.charAt(x) == (char)225) {
          a2++;
        }
        if (html.charAt(x) == (char)233) {
          e2++;
        }
        if (html.charAt(x) == (char)237) {
          i2++;
        }
        if (html.charAt(x) == (char)243) {
          o2++;
        }
        if (html.charAt(x) == (char)250) {
          u2++;
        }
        if (html.charAt(x) == (char)224) {
          a3++;
        }
        if (html.charAt(x) == (char)232) {
          e3++;
        }
        if (html.charAt(x) == (char)236) {
          i3++;
        }
        if (html.charAt(x) == (char)242) {
          o3++;
        }
        if (html.charAt(x) == (char)249) {
          u3++;
        }
        if (html.charAt(x) == (char)227) {
          a4++;
        }
        if (html.charAt(x) == (char)245) {
          o4++;
        }
        if (html.charAt(x) == (char)226) {
          a5++;
        }
        if (html.charAt(x) == (char)234) {
          e4++;
        }
        if (html.charAt(x) == (char)238) {
          i4++;
        }
        if (html.charAt(x) == (char)244) {
          o5++;
        }
        if (html.charAt(x) == (char)251) {
          u4++;
        }
        if ((html.charAt(x) == 'q') || (html.charAt(x) == 'w') || (html.charAt(x) == 'r') || 
            (html.charAt(x) == 't') || (html.charAt(x) == 'y') || (html.charAt(x) == 'p') || 
            (html.charAt(x) == 's') || (html.charAt(x) == 'd') || (html.charAt(x) == 'f') || 
            (html.charAt(x) == 'g') || (html.charAt(x) == 'h') || (html.charAt(x) == 'j') || 
            (html.charAt(x) == 'k') || (html.charAt(x) == 'l') || (html.charAt(x) == 'z') || 
            (html.charAt(x) == 'x') || (html.charAt(x) == 'c') || (html.charAt(x)  == 'v')|| 
            (html.charAt(x)  == 'b') || (html.charAt(x) == 'n') || (html.charAt(x)  == 'm')){
          cons++;
        }
        if (html.charAt(x) == '<' && html.charAt(x + 1) == 'b' && html.charAt(x + 2) == 'r'
            && html.charAt(x + 3) == '>') {
          br++;
        }
        if (html.charAt(x) == '<' && html.charAt(x + 1) == 't' && html.charAt(x + 2) == 'a' && html.charAt(x + 3) == 'b'
            && html.charAt(x + 4) == 'l' && html.charAt(x + 5) == 'e' && html.charAt(x + 6) == '>') {
          table++;
        }

      }
      //Retira as letras existentes em table e br
      a=a-table;
      e=e-table;
      cons=cons-(2*br)-(3*table);
      //Imprime da forma expecificada
      System.out.print("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") "+(char)225+"(" + a2 + ") "+(char)233+"(" + e2 + ") "+(char)237+"("
          + i2 + ") "+(char)243+"(" + o2 + ") "+(char)250+"(" + u2 + ") "+(char)224+"(" + a3 + ") "+(char)232+"(" + e3 + ") "+(char)236+"(" + i3 + ") "+(char)242+"(" + o3 + ") "+(char)249+"(" + u3 + ") "+(char)227+"(" + a4
          + ") "+(char)245+"(" + o4 + ") "+(char)226+"(" + a5 + ") "+(char)234+"(" + e4 + ") "+(char)238+"(" + i4 + ") "+(char)244+"(" + o5 + ") "+(char)251+"(" + u4 + ") consoante(" + cons
          + ") <br>(" + br + ") <table>(" + table + ") " + nome + "\n");
      nome = scr.nextLine();
    }
    scr.close();
  }
}