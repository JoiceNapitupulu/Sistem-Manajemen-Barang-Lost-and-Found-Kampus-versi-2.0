package kampus.model;

import java.util.ArrayList;

public class GudangPenyimpanan<T extends Barang> {
    private ArrayList<T> daftarBarang;

    public GudangPenyimpanan() {
        this.daftarBarang = new ArrayList<>();
    }

    public void tambahBarang(T barang) {
        this.daftarBarang.add(barang);
    }

    public void tampilkanSemuaBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Gudang kosong.");
            return;
        }
        for (int i = 0; i < daftarBarang.size(); i++) {
            daftarBarang.get(i).tampilkanDetail();
        }
    }

    public T ambilBarang(int index) {
        if (index >= 0 && index < daftarBarang.size()) {
            return daftarBarang.remove(index);
        }
        return null;
    }

    public int getJumlahBarang() {
        return daftarBarang.size();
    }

    public T getBarang(int index) {
        if (index >= 0 && index < daftarBarang.size()) {
            return daftarBarang.get(index);
        }
        return null;
    }

    public void tampilkanDenganFilter(FilterGudang<T> filter) {
        boolean ada = false;
        for (T barang : daftarBarang) {
            if (filter.cekKriteria(barang)) {
                barang.tampilkanDetail();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("- Tidak ada barang yang sesuai filter -");
        }
    }
}
