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

        System.out.println("Programa Teste");
        System.out.println("Digite A");
        a = _key.nextDouble();
        System.out.println("Digite B");
        b = _key.nextDouble();
        bol = _key.nextBoolean();
        bool = true ;
        if (a > b) {
            c = a + b;
        } else {
            c = (a - b) / (2 + 5.6);
        }

        if (a != b) {
            c = a + b;
        }
        t = _key.nextLine();
        t = "hello" + " " + ("world");
        f = (a + c) / (2 + 5.6);
        f = (-10.525) / ((+10));
        f = 10 + ((-10)) + (-1);
        f = (-10.05) - (+10);
        f = a + b;
        frase = "ello world";
        f = a - 215.454;
        bol = f < 10 || (true && false) || !(false) && f <= 10 / 2;
        bol = a + 1 < b && f <= 10 / 2;
        if (bol ) {
            t = "e verdadeiro";
        }
        /* Condicao */
        if (a == b) {
            t = "igual";
        } else {
            t = "diferente";
        }

        do {
            a = _key.nextDouble();
            System.out.println(a);
        } while (a != 0 || ( f == 0) && false);

        System.out.println("C e igual a ");
        System.out.println(c);
        d = c * (a + b);
        System.out.println("D e igual a ");
        System.out.println(d);
        while (a + 1 < b && true) {
            a = _key.nextDouble();
            System.out.println(a);
            System.out.println("TESTE 1");
            a = a + 1;
        }
        /* comentario de 
          		varias linhas  */
        /**/
        for (i=0.2; i < 2 * (5 + f); i=i+1) {
            System.out.println(a);
            System.out.println(i);
            a = a + 2;
        }
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
        switch (bool) {
            case true : 
                System.out.println("VERDADEIRO");
            break;
            default : 
                System.out.println("acabou");
        }
    }
}