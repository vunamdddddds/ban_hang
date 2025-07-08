package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.demo2.HangHoaRepository;

@RestController
@RequestMapping("/api/taobang")
class TaoBangController {
    @Autowired
    private HangHoaRepository hangHoaRepository;

    @PostMapping("/init")
    //@RequestMapping(value = "/init", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> initDatabase() {
        try {
            // Kiểm tra kết nối đến database ban_hang
            String url = "jdbc:mysql://localhost:3306/ban_hang?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "26112005";

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                // Kiểm tra xem bảng hang_hoa đã tồn tại chưa
                try {
                    hangHoaRepository.count();
                    return ResponseEntity.badRequest()
                            .body("Lỗi: Bảng hang_hoa đã tồn tại trong cơ sở dữ liệu");
                } catch (Exception e) {
                    // Nếu bảng chưa tồn tại, tạo mới
                    return ResponseEntity.ok("Bảng hang_hoa đã được khởi tạo thành công");
                }
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi: Không thể kết nối đến cơ sở dữ liệu ban_hang. Vui lòng kiểm tra lại cơ sở dữ liệu đã được tạo chưa");
        }
    }
}