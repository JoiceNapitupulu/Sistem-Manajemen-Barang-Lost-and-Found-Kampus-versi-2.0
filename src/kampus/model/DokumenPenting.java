package kampus.model;

public class DokumenPenting extends Barang {
    private String namaPemilikDokumen;

    public DokumenPenting(String idBarang, String namaBarang, String namaPemilikDokumen) {
        super(idBarang, namaBarang);
        this.namaPemilikDokumen = namaPemilikDokumen;
    }

    public String getNamaPemilikDokumen() {
        return namaPemilikDokumen;
    }

    public void setNamaPemilikDokumen(String namaPemilikDokumen) {
        this.namaPemilikDokumen = namaPemilikDokumen;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("---- Dokumen Penting ----");
        System.out.println("ID Barang     : " + getIdBarang());
        System.out.println("Nama Dokumen  : " + getNamaBarang());
        System.out.println("Nama Pemilik  : " + namaPemilikDokumen);
        System.out.println("-------------------------");
    }
}
