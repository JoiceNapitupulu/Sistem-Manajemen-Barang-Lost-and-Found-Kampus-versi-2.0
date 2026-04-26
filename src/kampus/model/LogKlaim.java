package kampus.model;

public record LogKlaim(String idLaporan, String idBarang, String nimPenemu, String nimPemilik) {
    @Override
    public String toString() {
        return "Klaim [" + idLaporan + "]: Barang [" + idBarang + "] diserahkan dari [" + nimPenemu + "] ke [" + nimPemilik + "]";
    }
}
