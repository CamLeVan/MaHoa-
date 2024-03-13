package com.levancam.demo6.models;

public class NpDatabase {
    private String DTNPma;
    private String DTNPsophepduocnghi;
    private String DTNPsophepconlai;
    private String DTNPngaybatdau;
    private String DTNPngayketthuc;

    public NpDatabase(String DTNPma, String DTNPsophepduocnghi, String DTNPsophepconlai, String DTNPngaybatdau, String DTNPngayketthuc) {
        this.DTNPma = DTNPma;
        this.DTNPsophepduocnghi = DTNPsophepduocnghi;
        this.DTNPsophepconlai = DTNPsophepconlai;
        this.DTNPngaybatdau = DTNPngaybatdau;
        this.DTNPngayketthuc = DTNPngayketthuc;
    }

    public String getDTNPma() {
        return DTNPma;
    }

    public void setDTNPma(String DTNPma) {
        this.DTNPma = DTNPma;
    }

    public String getDTNPsophepduocnghi() {
        return DTNPsophepduocnghi;
    }

    public void setDTNPsophepduocnghi(String DTNPsophepduocnghi) {
        this.DTNPsophepduocnghi = DTNPsophepduocnghi;
    }

    public String getDTNPsophepconlai() {
        return DTNPsophepconlai;
    }

    public void setDTNPsophepconlai(String DTNPsophepconlai) {
        this.DTNPsophepconlai = DTNPsophepconlai;
    }

    public String getDTNPngaybatdau() {
        return DTNPngaybatdau;
    }

    public void setDTNPngaybatdau(String DTNPngaybatdau) {
        this.DTNPngaybatdau = DTNPngaybatdau;
    }

    public String getDTNPngayketthuc() {
        return DTNPngayketthuc;
    }

    public void setDTNPngayketthuc(String DTNPngayketthuc) {
        this.DTNPngayketthuc = DTNPngayketthuc;
    }
}
