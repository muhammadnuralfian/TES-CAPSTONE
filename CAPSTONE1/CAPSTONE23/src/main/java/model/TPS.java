package model;

public class TPS {
    private int idTps;
    private String namaTps;
    private String lokasi;
    private double kapasitasM3;
    private String status;
    private int idStakeholder;

    public TPS() {}

    public int getIdTps() {
        return idTps;
    }

    public void setIdTps(int idTps) {
        this.idTps = idTps;
    }

    public String getNamaTps() {
        return namaTps;
    }

    public void setNamaTps(String namaTps) {
        this.namaTps = namaTps;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public double getKapasitasM3() {
        return kapasitasM3;
    }

    public void setKapasitasM3(double kapasitasM3) {
        this.kapasitasM3 = kapasitasM3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdStakeholder() {
        return idStakeholder;
    }

    public void setIdStakeholder(int idStakeholder) {
        this.idStakeholder = idStakeholder;
    }
}
