import java.util.Scanner;

public class RestaurantMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant menu = new Restaurant();

        menu.tambahMenuMakanan("Bala-bala", 1000, 20);
        menu.tambahMenuMakanan("Gehu", 1000, 20);
        menu.tambahMenuMakanan("Tahu", 1000, 0);
        menu.tambahMenuMakanan("Molen", 1000, 20);

        while (true) {
            menu.tampilMenuMakanan();

            System.out.print("\nMasukkan nama makanan yang ingin dipesan (atau 'exit' untuk keluar): ");
            String namaMakanan = scanner.nextLine();

            if (namaMakanan.equalsIgnoreCase("exit")) {
                System.out.println("Terima kasih telah memesan!");
                break;
            }

            System.out.print("Masukkan jumlah pesanan: ");
            int jumlahPesanan;

            try {
                jumlahPesanan = Integer.parseInt(scanner.nextLine());
                if (jumlahPesanan <= 0) {
                    System.out.println("Jumlah pesanan harus lebih dari 0.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
                continue;
            }

            menu.pesanMakanan(namaMakanan, jumlahPesanan);
        }

        scanner.close();
    }
}
