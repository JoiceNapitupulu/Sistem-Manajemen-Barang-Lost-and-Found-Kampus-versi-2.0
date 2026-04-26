# Sistem Manajemen Barang Hilang & Ditemukan Kampus (Versi 2.0)

Aplikasi berbasis Java (*Console/CLI*) untuk mengelola pendataan barang dan dokumen yang hilang atau ditemukan di lingkungan kampus. Sistem ini dibangun dengan menerapkan prinsip *Object-Oriented Programming* (OOP) tingkat lanjut seperti Abstraksi, Polimorfisme, Generics, Records, Nested Class, serta Local dan Anonymous Class.

## 🚀 Apa yang Baru di Versi 2.0?

Versi 2.0 merupakan pengembangan langsung dari versi sebelumnya dengan penekanan pada penyempurnaan implementasi OOP tingkat lanjut:

*   **Anonymous Class untuk Filter Dinamis**: Implementasi pencarian barang spesifik dengan *Anonymous Class* (melalui interface `FilterGudang<T>`) untuk menyaring data secara kustom tanpa menggunakan *Lambda Expression* atau *Stream API*.
*   **Local Class untuk Validasi Klaim**: Penambahan proses validasi ketat (*rule-based*) saat mengeksekusi klaim. Fitur ini menggunakan *Local Class* (`ValidatorKlaim`) yang bersarang di dalam *method* untuk mengenkapsulasi fungsi validasi secara rapi.

## ✨ Fitur Utama

-   **Lapor Barang Ditemukan**: Mendata barang elektronik atau dokumen penting yang ditemukan beserta detail spesifiknya.
-   **Gudang Generik (`GudangPenyimpanan<T>`)**: Penyimpanan data yang dipisah secara spesifik (Gudang Elektronik & Dokumen) namun tetap menggunakan satu *class* terpusat (generik) untuk menjamin *Type Safety*.
-   **Klaim Barang Hilang**: Proses klaim barang dengan sistem pencarian otomatis (*Nested Class* `MesinPencari`) dan validasi NIM pemilik (*Local Class* `ValidatorKlaim`).
-   **Log Riwayat Klaim**: Mencatat riwayat setiap klaim yang berhasil disetujui dalam bentuk struktur data *immutable* yang efisien (`Record` Java).
-   **Cari Barang Spesifik**: Kemampuan filter lanjutan untuk menelusuri barang di seluruh gudang hanya dengan menginput *keyword* secara dinamis menggunakan *Anonymous Class*.

## 🛠️ Teknologi yang Digunakan

-   **Bahasa Pemrograman**: Java (JDK 17 atau lebih baru disarankan, untuk dukungan penuh `Record`).
-   **Paradigma**: Object-Oriented Programming (*Inheritance*, *Abstract Class*, *Polymorphism*, *Encapsulation*, *Generics*, *Inner/Nested Classes*, *Local/Anonymous Classes*).
-   **Antarmuka**: *Command Line Interface (CLI)* interaktif.

## 📋 Prasyarat Instalasi

1.  Pastikan Anda telah menginstal **Java Development Kit (JDK)** pada perangkat Anda (Disarankan versi JDK 17+).
2.  Telah mengatur *Environment Variables* agar perintah `java` dan `javac` dapat dieksekusi melalui terminal.

## 📂 Susunan Project

```text
src/
└── kampus/
    ├── driver/
    │   ├── Main.java               # Kelas utama untuk entry point & menu navigasi CLI
    │   └── PusatLostAndFound.java  # Kelas pengelola core business logic
    └── model/
        ├── Barang.java             # Abstract class dasar bagi semua entitas barang
        ├── BarangElektronik.java   # Sub-class untuk barang elektronik (menyimpan data port)
        ├── DokumenPenting.java     # Sub-class untuk dokumen (menyimpan nama pemilik dokumen)
        ├── FilterGudang.java       # Generic Interface untuk kerangka Anonymous Class
        ├── GudangPenyimpanan.java  # Generic class untuk array list gudang barang
        └── LogKlaim.java           # Record class untuk instansiasi riwayat klaim statis
```

## 🚀 Cara Menjalankan Program

1. Buka Terminal atau *Command Prompt*.
2. Arahkan *working directory* ke *root folder* proyek ini.
3. Lakukan kompilasi seluruh *source code* Java ke dalam direktori `bin`:
   ```bash
   javac -d bin src/kampus/model/*.java src/kampus/driver/*.java
   ```
4. Jalankan program menggunakan kelas `Main`:
   ```bash
   java -cp bin kampus.driver.Main
   ```

## 💻 Contoh Penggunaan (Input & Output)

**Skenario 1: Menambahkan Data & Mencari Barang Spesifik (Anonymous Class)**

```text
==================================================
Sistem Manajemen Barang Hilang & Ditemukan Kampus
==================================================

Menu Utama:
1. Lapor Barang Ditemukan
2. Lihat Gudang Barang Ditemukan
3. Klaim Barang Hilang
4. Lihat Log Riwayat Klaim
5. Cari Barang Spesifik
6. Keluar
Pilih opsi (1-6): 1

Jenis Barang Ditemukan:
A. Elektronik
B. Dokumen
Pilih jenis (A/B): A
Masukkan ID Barang      : E001
Masukkan Nama Barang    : Laptop Asus
Masukkan Jenis Port Charger : Type-C
Berhasil menambahkan Barang Elektronik ke gudang.

Menu Utama:
...
Pilih opsi (1-6): 5

Masukkan keyword nama barang yang dicari: asus

--- Hasil Pencarian di Gudang Elektronik ---
[Elektronik] ID: E001 | Nama: Laptop Asus | Port: Type-C

--- Hasil Pencarian di Gudang Dokumen ---
- Tidak ada barang yang sesuai filter -
```

**Skenario 2: Melakukan Klaim Barang (Validasi Local Class)**

```text
Menu Utama:
...
Pilih opsi (1-6): 3

--- Klaim Barang Hilang ---
Masukkan ID Laporan Klaim   : K-001
Masukkan ID Barang          : E001
Masukkan NIM Penemu (opsional, tekan enter jika tidak tahu/isi '-'): 13322001
Masukkan NIM Pemilik Baru/Klaimer : 1332210  
Klaim gagal! NIM pemilik harus persis 8 karakter dan tidak boleh kosong.

Menu Utama:
...
Pilih opsi (1-6): 3

--- Klaim Barang Hilang ---
Masukkan ID Laporan Klaim   : K-001
Masukkan ID Barang          : E001
Masukkan NIM Penemu (opsional, tekan enter jika tidak tahu/isi '-'): 13322001
Masukkan NIM Pemilik Baru/Klaimer : 13322100
Klaim berhasil disetujui untuk barang: Laptop Asus
```

