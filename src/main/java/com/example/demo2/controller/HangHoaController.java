package com.example.demo2.controller;

import com.example.demo2.HangHoa;
import com.example.demo2.HangHoaRepository;
import com.example.demo2.dto.HangHoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hanghoa")
public class HangHoaController {

    @Autowired
    private HangHoaRepository hangHoaRepository;

    // Create
    @PostMapping
    public ResponseEntity<HangHoaDTO> create(@RequestBody HangHoaDTO dto) {
        HangHoa hangHoa = new HangHoa();
        hangHoa.setMaHangHoa(dto.getMaHangHoa());
        hangHoa.setTenHangHoa(dto.getTenHangHoa());
        hangHoa.setSoLuong(dto.getSoLuong());

        HangHoa saved = hangHoaRepository.save(hangHoa);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    // Read - Method 1: Path Variable
    @GetMapping("/{maHangHoa}")
    public ResponseEntity<HangHoaDTO> getByPath(@PathVariable String maHangHoa) {
        return hangHoaRepository.findById(maHangHoa)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read - Method 2: Request Parameter
    @GetMapping("/search")
    public ResponseEntity<HangHoaDTO> getByParam(@RequestParam String maHangHoa) {
        return hangHoaRepository.findById(maHangHoa)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<HangHoaDTO>> getAll() {
        List<HangHoaDTO> dtos = hangHoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Update
    @PutMapping("/{maHangHoa}")
    public ResponseEntity<HangHoaDTO> update(@PathVariable String maHangHoa, @RequestBody HangHoaDTO dto) {
        if (!hangHoaRepository.existsById(maHangHoa)) {
            return ResponseEntity.notFound().build();
        }

        HangHoa hangHoa = new HangHoa();
        hangHoa.setMaHangHoa(maHangHoa);
        hangHoa.setTenHangHoa(dto.getTenHangHoa());
        hangHoa.setSoLuong(dto.getSoLuong());

        HangHoa updated = hangHoaRepository.save(hangHoa);
        return ResponseEntity.ok(convertToDTO(updated));
    }

    // Delete
    @DeleteMapping("/{maHangHoa}")
    public ResponseEntity<Void> delete(@PathVariable String maHangHoa) {
        if (!hangHoaRepository.existsById(maHangHoa)) {
            return ResponseEntity.notFound().build();
        }

        hangHoaRepository.deleteById(maHangHoa);
        return ResponseEntity.ok().build();
    }

    private HangHoaDTO convertToDTO(HangHoa hangHoa) {
        HangHoaDTO dto = new HangHoaDTO();
        dto.setMaHangHoa(hangHoa.getMaHangHoa());
        dto.setTenHangHoa(hangHoa.getTenHangHoa());
        dto.setSoLuong(hangHoa.getSoLuong());
        return dto;
    }
}