package com.example.demo2;

import jakarta.persistence.*;

@Entity
@Table(name = "hang_hoa")
public class HangHoa {

    @Id
    @Column(name = "ma_hanghoa", length = 9)
    private String maHangHoa;

    @Column(name = "ten_hanghoa", nullable = false)
    private String tenHangHoa;

    @Column(name = "so_luong")
    private Integer soLuong;

    // Getters and Setters
    public String getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}