package kampus.driver;

import kampus.model.Barang;
import kampus.model.BarangElektronik;
import kampus.model.DokumenPenting;
import kampus.model.GudangPenyimpanan;
import kampus.model.LogKlaim;

import java.util.ArrayList;

public class PusatLostAndFound {
    private GudangPenyimpanan<BarangElektronik> gudangElektronik;
    private GudangPenyimpanan<DokumenPenting> gudangDokumen;
    private ArrayList<LogKlaim> riwayatKlaim;

    public PusatLostAndFound() {
        gudangElektronik = new GudangPenyimpanan<>();
        gudangDokumen = new GudangPenyimpanan<>();
        riwayatKlaim = new ArrayList<>();
    }

    private class MesinPencari {
        public Barang cariBarang(String idBarangDicari) {
            // Mencari di Gudang Elektronik
            for (int i = 0; i < gudangElektronik.getJumlahBarang(); i++) {
                BarangElektronik b = gudangElektronik.getBarang(i);
                if (b.getIdBarang().equals(idBarangDicari)) {
                    return b;
                }
            }
            // Mencari di Gudang Dokumen
            for (int i = 0; i < gudangDokumen.getJumlahBarang(); i++) {
                DokumenPenting d = gudangDokumen.getBarang(i);
                if (d.getIdBarang().equals(idBarangDicari)) {
                    return d;
                }
            }
            return null;
        }
    }

    public void laporBarangDitemukan(Barang barang) {
        if (barang instanceof BarangElektronik) {
            gudangElektronik.tambahBarang((BarangElektronik) barang);
            System.out.println("Berhasil menambahkan Barang Elektronik ke gudang.");
        } else if (barang instanceof DokumenPenting) {
            gudangDokumen.tambahBarang((DokumenPenting) barang);
            System.out.println("Berhasil menambahkan Dokumen Penting ke gudang.");
        }
    }

    public void lihatGudang() {
        System.out.println("\n=== GUDANG BARANG ELEKTRONIK ===");
        gudangElektronik.tampilkanSemuaBarang();
        
        System.out.println("\n=== GUDANG DOKUMEN PENTING ===");
        gudangDokumen.tampilkanSemuaBarang();
    }

    public void prosesKlaimBarang(String idLaporan, String idBarang, String nimPenemu, String nimPemilik) {
        class ValidatorKlaim {
            public boolean isKlaimValid() {
                return nimPemilik != null && !nimPemilik.trim().isEmpty() && nimPemilik.length() == 10;
            }
        }

        ValidatorKlaim validator = new ValidatorKlaim();
        if (!validator.isKlaimValid()) {
            System.out.println("Klaim gagal! NIM pemilik harus persis 10 karakter dan tidak boleh kosong.");
            return;
        }

        MesinPencari mesin = new MesinPencari();
        Barang barangDitemukan = mesin.cariBarang(idBarang);

        if (barangDitemukan != null) {
            // Menghapus barang dari gudang asalnya
            if (barangDitemukan instanceof BarangElektronik) {
                // Cari index lalu hapus dengan ambilBarang
                for (int i = 0; i < gudangElektronik.getJumlahBarang(); i++) {
                    if (gudangElektronik.getBarang(i).getIdBarang().equals(idBarang)) {
                        gudangElektronik.ambilBarang(i);
                        break;
                    }
                }
            } else if (barangDitemukan instanceof DokumenPenting) {
                // Cari index lalu hapus dengan ambilBarang
                for (int i = 0; i < gudangDokumen.getJumlahBarang(); i++) {
                    if (gudangDokumen.getBarang(i).getIdBarang().equals(idBarang)) {
                        gudangDokumen.ambilBarang(i);
                        break;
                    }
                }
            }

            LogKlaim log = new LogKlaim(idLaporan, idBarang, nimPenemu, nimPemilik);
            riwayatKlaim.add(log);
            System.out.println("Klaim berhasil disetujui untuk barang: " + barangDitemukan.getNamaBarang());
        } else {
            System.out.println("Klaim gagal! Barang dengan ID " + idBarang + " tidak ditemukan di gudang.");
        }
    }

    public void tampilkanRiwayatKlaim() {
        if (riwayatKlaim.isEmpty()) {
            System.out.println("Belum ada riwayat klaim.");
            return;
        }
        for (int i = 0; i < riwayatKlaim.size(); i++) {
            System.out.println(riwayatKlaim.get(i).toString());
        }
    }

    public GudangPenyimpanan<BarangElektronik> getGudangElektronik() {
        return gudangElektronik;
    }

    public GudangPenyimpanan<DokumenPenting> getGudangDokumen() {
        return gudangDokumen;
    }
}
