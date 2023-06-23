package org.anakrimba.club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "barcelona")
public class Barcelona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "posisi")
    private String posisi;

    @Column(name = "negara")
    private String negara;

    public Barcelona(){

    }

    public Barcelona(String nama, String posisi, String negara) {
        this.nama = nama;
        this.posisi = posisi;
        this.negara = negara;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    @Override
    public String toString() {
        return "Barcelona{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", posisi='" + posisi + '\'' +
                ", negara='" + negara + '\'' +
                '}';
    }
}
