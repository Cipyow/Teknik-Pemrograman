import java.util.Scanner;

public class Soal1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah kasus yang ingin dibuat: ");
        int T = 0;

        while (!sc.hasNextInt()) {
            System.out.println("Input tidak valid!");
            sc.next();
        }

        T = sc.nextInt();

        for (int i = 0; i < T; i++){
            try{
                System.out.print("Masukkan angka ke-" + (i + 1) + ": ");
                long n = sc.nextLong();
                System.out.println(n + " Bisa dimuat ke dalam:");
                if (n > Byte.MIN_VALUE && n < Byte.MAX_VALUE) {
                    System.out.println("* Byte");
                }
                if (n > Short.MIN_VALUE && n < Short.MAX_VALUE) {
                    System.out.println("* Short");
                }
                if (n > Integer.MIN_VALUE && n < Integer.MAX_VALUE) {
                    System.out.println("* Integer");
                }
                if (n > Long.MIN_VALUE && n < Long.MAX_VALUE) {
                    System.out.println("* Long");
                }
            } catch (Exception e) {
                System.out.println("Tidak dapat dimuat dimanapun!");
                sc.next();
                }
        }
        sc.close();
    }
}
