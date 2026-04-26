package kampus.model;

public class BarangElektronik extends Barang {
    private String jenisPortCharger;

    public BarangElektronik(String idBarang, String namaBarang, String jenisPortCharger) {
        super(idBarang, namaBarang);
        this.jenisPortCharger = jenisPortCharger;
    }

    public String getJenisPortCharger() {
        return jenisPortCharger;
    }

    public void setJenisPortCharger(String jenisPortCharger) {
        this.jenisPortCharger = jenisPortCharger;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("---- Barang Elektronik ----");
        System.out.println("ID Barang     : " + getIdBarang());
        System.out.println("Nama Barang   : " + getNamaBarang());
        System.out.println("Port Charger  : " + jenisPortCharger);
        System.out.println("---------------------------");
    }
}
