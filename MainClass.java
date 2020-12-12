import java.util.Scanner;

public class MainClass{ 
    public static void main(String args[]){
        Scanner _key = new Scanner(System.in);
        double  a;
        double  b;
        double  c;
        double  d;
        String  t;
        double  f;
        double  i;
        boolean  bol;
        System.out.println("Programa Teste");
        System.out.println("Digite A");
        a = _key.nextDouble();
        System.out.println("Digite B");
        b = _key.nextDouble();
        if (a > b) {
            c = a+b;
        } else {
            c = (a-b)/(2+5.6);
        }

        if (a != b) {
            c = a+b;
        }
        t = _key.nextLine();
        t = "hello"+" "+("world");
        f = (a+c)/(2+5.6);
        f = -10.525/(+10);
        f = 10+(-10)+-1;
        f = -10.05-+10;
        f = a+b;
        bol = f < 10 || (true && false) || !(false) && f <= 10 / 2;
        bol = a + 1 < b && f <= 10 / 2;
        if (bol ) {
            t = "e verdadeiro";
        }
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
        d = c*(a+b);
        System.out.println("D e igual a ");
        System.out.println(d);
        while (a + 1 < b && false) {
            a = _key.nextDouble();
            System.out.println(a);
            System.out.println("TESTE 1");
            a = a+1;
        }
        /* comentario de 
          		varias linhas  */ 

        for (i=0.2; i<5; i=i+1) {
            System.out.println(a);
            System.out.println(i);
        }
    }
}