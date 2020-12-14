import java.util.Scanner;

public class GeneratedCode{ 
    public static void main(String args[]){
        Scanner _key = new Scanner(System.in);
        double  a;
        double  b;
        double  c;
        double  d;
        String  t;
        boolean  bool;
        String  frase;
        double  f;
        double  i;
        boolean  bol;

        /* Comandos de leitura 
                       e escrita */
        System.out.println("Programa Teste");
        System.out.println("Digite A");
        a = _key.nextDouble();
        System.out.println("Digite B");
        b = _key.nextDouble();
        System.out.println("Digite bol");
        bol = _key.nextBoolean();
        /* Declaração de uma nova variável com valor atribuído */
        bool = true ;
        frase = "Hello world";
        /* Comando se - senao */
        if (a > b) {
            c = a + b;
        } else {
            c = (a - b) / (2 + 5.6);
        }

        if (a != b) {
            c = a + b;
        }
        if (bol ) {
            t = "e verdadeiro";
        }
        if (a == b) {
            t = "igual";
        } else {
            t = "diferente";
        }

        /* Concatenação de Strings */
        t = "hello" + " " + ("world");
        t = "Linguagem " + t;
        /* Operações numéricas */
        f = (a + c) / (2 + 5.6);
        f = (-10.525) / ((+10));
        f = 10 + ((-10)) + (-1);
        f = (-10.05) - (+10);
        f = a + b;
        f = a - 215.454;
        /* Operações booleanas */
        bol = f < 10 || (true && false) || !(false) && f <= 10 / 2;
        bol = a + 1 < b && f <= 10 / 2;
        /* Comando faca ... enquanto */
        do {
            a = _key.nextDouble();
            System.out.println(a);
        } while (a != 0 || ( f == 0) && false);

        /* Comando enquanto */
        while (a + 1 < b && true) {
            a = _key.nextDouble();
            System.out.println(a);
            System.out.println("TESTE 1");
            a = a + 1;
        }
        /* Comando para  */
        for (i=0.2; i < 2 * (5 + f); i=i+1) {
            System.out.println(a);
            System.out.println(i);
            a = a + 2;
        }
        /* Comando escolha */
        switch (t) {
            case "aa" : 
                System.out.println("aa");
            break;
            case "bb" : 
                System.out.println("teste2");
                System.out.println("bb");
            break;
            case "cc" : 
                System.out.println("cc");
            break;
            default : 
                System.out.println("acabou");
        }
        switch (t) {
            case "IsiLang" : 
                t = "Linguagem " + t;
                System.out.println(t);
            break;
        }
    }
}