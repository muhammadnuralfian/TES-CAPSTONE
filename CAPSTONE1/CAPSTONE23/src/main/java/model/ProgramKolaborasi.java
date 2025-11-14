/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class ProgramKolaborasi {
    private int idProgram;
    private String namaProgram;
    private String deskripsi;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private String statusProgram;
    private int idAkun;

    public ProgramKolaborasi() {}

    public ProgramKolaborasi(String namaProgram, String deskripsi, Date tanggalMulai, Date tanggalSelesai, String statusProgram, int idAkun) {
        this.namaProgram = namaProgram;
        this.deskripsi = deskripsi;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.statusProgram = statusProgram;
        this.idAkun = idAkun;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getDeskripsi() {
       return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatusProgram() {
        return statusProgram;
    }

    public void setStatusProgram(String statusProgram) {
        this.statusProgram = statusProgram;
    }

    public int getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(int idAkun) {
        this.idAkun = idAkun;
    }
} 

