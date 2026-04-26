package kampus.driver;

import kampus.model.BarangElektronik;
import kampus.model.DokumenPenting;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PusatLostAndFound pusat = new PusatLostAndFound();
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Sistem Manajemen Barang Hilang & Ditemukan Kampus");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Lapor Barang Ditemukan");
            System.out.println("2. Lihat Gudang Barang Ditemukan");
            System.out.println("3. Klaim Barang Hilang");
            System.out.println("4. Lihat Log Riwayat Klaim");
            System.out.println("5. Cari Barang Spesifik");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi (1-6): ");
            
            String opsi = sc.nextLine();
            
            if (opsi.equals("1")) {
                System.out.println("\nJenis Barang Ditemukan:");
                System.out.println("A. Elektronik");
                System.out.println("B. Dokumen");
                System.out.print("Pilih jenis (A/B): ");
                String jenis = sc.nextLine();
                
                System.out.print("Masukkan ID Barang      : ");
                String idBarang = sc.nextLine();
                System.out.print("Masukkan Nama Barang    : ");
                String namaBarang = sc.nextLine();
                
                if (jenis.equalsIgnoreCase("A")) {
                    System.out.print("Masukkan Jenis Port Charger : ");
                    String port = sc.nextLine();
                    BarangElektronik be = new BarangElektronik(idBarang, namaBarang, port);
                    pusat.laporBarangDitemukan(be);
                } else if (jenis.equalsIgnoreCase("B")) {
                    System.out.print("Masukkan Nama Pemilik Dok   : ");
                    String pemilik = sc.nextLine();
                    DokumenPenting dp = new DokumenPenting(idBarang, namaBarang, pemilik);
                    pusat.laporBarangDitemukan(dp);
                } else {
                    System.out.println("Jenis barang tidak valid.");
                }

            } else if (opsi.equals("2")) {
                pusat.lihatGudang();
                
            } else if (opsi.equals("3")) {
                System.out.println("\n--- Klaim Barang Hilang ---");
                System.out.print("Masukkan ID Laporan Klaim   : ");
                String idLaporan = sc.nextLine();
                System.out.print("Masukkan ID Barang          : ");
                String idBarang = sc.nextLine();
                System.out.print("Masukkan NIM Penemu (opsional, tekan enter jika tidak tahu/isi '-'): ");
                String nimPenemu = sc.nextLine();
                if (nimPenemu.trim().isEmpty() || nimPenemu.equals("-")) {
                    nimPenemu = "Admin";
                }
                System.out.print("Masukkan NIM Pemilik Baru/Klaimer : ");
                String nimPemilik = sc.nextLine();
                
                pusat.prosesKlaimBarang(idLaporan, idBarang, nimPenemu, nimPemilik);
                
            } else if (opsi.equals("4")) {
                System.out.println("\n=== RIWAYAT LOG KLAIM ===");
                pusat.tampilkanRiwayatKlaim();
                
            } else if (opsi.equals("5")) {
                System.out.print("\nMasukkan keyword nama barang yang dicari: ");
                String keyword = sc.nextLine();
                
                System.out.println("\n--- Hasil Pencarian di Gudang Elektronik ---");
                pusat.getGudangElektronik().tampilkanDenganFilter(new kampus.model.FilterGudang<BarangElektronik>() {
                    @Override
                    public boolean cekKriteria(BarangElektronik barang) {
                        return barang.getNamaBarang().toLowerCase().contains(keyword.toLowerCase());
                    }
                });

                System.out.println("\n--- Hasil Pencarian di Gudang Dokumen ---");
                pusat.getGudangDokumen().tampilkanDenganFilter(new kampus.model.FilterGudang<DokumenPenting>() {
                    @Override
                    public boolean cekKriteria(DokumenPenting barang) {
                        return barang.getNamaBarang().toLowerCase().contains(keyword.toLowerCase());
                    }
                });

            } else if (opsi.equals("6")) {
                System.out.println("Berhasil keluar dari sistem. Terima kasih!");
                break;
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        
        sc.close();
    }
}
