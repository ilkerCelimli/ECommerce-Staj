package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesApiInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesInfo;
import com.portifolyo.mesleki1.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor

public class CategoriesApi {

    private final CategoriesService categoriesService;

    @GetMapping("/public/findCategoriesAndProducts")
    public ResponseEntity<List<CategoriesApiInfo>> findCategoriesAndProducts() {
        return ResponseEntity.ok().body(this.categoriesService.findCategoriesInfoList());
    }

    @GetMapping("/public/findCategorieAndProducts/{id}")
    public ResponseEntity<CategoriesInfo> findCategorieAndProducts(@PathVariable String id) {
        return ResponseEntity.ok().body(this.categoriesService.findCategoriesInfo(id));
    }

    @PostMapping("/AddCategories")
    public ResponseEntity<CategoriesApiInfo> addCategories(@RequestBody AddCategoriesDto dto) throws SqlExceptionCustom {
        this.categoriesService.addCategories(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateCategories/{id}")
    public ResponseEntity<CategoriesApiInfo> updateCategories(@PathVariable String id , @RequestBody AddCategoriesDto dto) throws SqlExceptionCustom {
        this.categoriesService.updateCategories(id,dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCategories/{id}")
    public ResponseEntity<CategoriesApiInfo> deleteCategories(@PathVariable String id) throws SQLException {
        this.categoriesService.delete(id);
        return ResponseEntity.ok().build();
    }




}
