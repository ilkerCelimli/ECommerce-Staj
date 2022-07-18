package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")
@RequiredArgsConstructor
public class CampaignApi {


    private final CampaignService campaignService;

    @GetMapping("/findCampaigns")
    public ResponseEntity<List<CampaignInfo>> findCampaigns() {
        return ResponseEntity.ok().body(campaignService.findCampaigns());
    }

    @GetMapping("/findCampaignsByProduct/{product}")
    public ResponseEntity<CampaignInfo> findCampaignsByProduct(@PathVariable("product") String product){
       return ResponseEntity.ok().body( this.campaignService.findCampaignProductId(product));
    }

    @PostMapping("/addCampaign")
    public ResponseEntity<Campaign> addCampaign(@RequestBody AddCampaignDto dto) throws SqlExceptionCustom {
        this.campaignService.addCampaign(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/updateCampaign")
    public ResponseEntity<Campaign> updateCampaign(@RequestBody AddCampaignDto dto){
        this.campaignService.updateCampaign(dto);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCampaign/{productId}")
    public ResponseEntity<Product> deleteCapaign(@PathVariable("productId") String productId) throws SqlExceptionCustom {
        this.campaignService.deleteCampaign(productId);
        return ResponseEntity.ok().build();
    }


}
