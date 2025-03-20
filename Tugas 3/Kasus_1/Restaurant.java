import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Menu> menuList;

    public Restaurant() {
        menuList = new ArrayList<>();
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {
        menuList.add(new Menu(nama, harga, stok));
    }

    public void tampilMenuMakanan() {
        System.out.println("=== MENU MAKANAN ===");
        for (Menu item : menuList) {
            if (!item.isOutOfStock()) {
                System.out.println(item);
            }
        }
    }

    public boolean pesanMakanan(String nama, int jumlah) {
        for (Menu item : menuList) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                if (item.getStok() >= jumlah) {
                    item.kurangiStok(jumlah);
                    System.out.println("Pesanan " + jumlah + " " + nama + " berhasil!");
                    return true;
                } else {
                    System.out.println("Stok tidak mencukupi untuk " + nama);
                    return false;
                }
            }
        }
        System.out.println("Menu tidak ditemukan: " + nama);
        return false;
    }
}
