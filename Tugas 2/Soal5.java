import java.util.Scanner;

public class Soal5{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan kata A: ");
        String A = sc.nextLine();
        System.out.print("Masukkan kata B: ");
        String B = sc.nextLine();

        System.out.println(A.length() + B.length());
        if (A.compareTo(B) > 0) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        String kataA = (A.substring(0, 1).toUpperCase() + A.substring(1));
        String kataB = (B.substring(0, 1).toUpperCase() + B.substring(1));
        System.out.print(kataA + " " + kataB);

        sc.close();
    }
}
