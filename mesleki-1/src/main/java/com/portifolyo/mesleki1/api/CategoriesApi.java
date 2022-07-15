package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.CategoriesAndProductsInfo;
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

    @GetMapping("/findCategoriesAndProducts")
    public ResponseEntity<List<CategoriesAndProductsInfo>> findCategoriesAndProducts() {
        return ResponseEntity.ok().body(this.categoriesService.findProductsInCategories());
    }

    @GetMapping("/findCategorieAndProducts/{id}")
    public ResponseEntity<CategoriesAndProductsInfo> findCategorieAndProducts(@PathVariable String id) {
        return ResponseEntity.ok().body(this.categoriesService.findCategorieAndProducts(id));
    }

    @PostMapping("/AddCategories")
    public ResponseEntity<CategoriesAndProductsInfo> addCategories(@RequestBody AddCategoriesDto dto) throws SqlExceptionCustom {
        this.categoriesService.AddCategories(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateCategories/{id}")
    public ResponseEntity<CategoriesAndProductsInfo> updateCategories(@PathVariable String id , @RequestBody AddCategoriesDto dto) throws SqlExceptionCustom {
        this.categoriesService.updateCategories(id,dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCategories/{id}")
    public ResponseEntity<CategoriesAndProductsInfo> deleteCategories(@PathVariable String id) throws SQLException {
        this.categoriesService.delete(id);
        return ResponseEntity.ok().build();
    }




}
