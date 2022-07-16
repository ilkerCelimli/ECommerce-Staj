package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.CampaignInfo;
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

    @GetMapping("/findCampaignsByProduct/{product}/{shopper}")
    public ResponseEntity<CampaignInfo> findCampaignsByProduct(@PathVariable("product") String product, @PathVariable("shopper") String shopperId){
       return ResponseEntity.ok().body( this.campaignService.findCampaignProductId(product,shopperId));
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

    @DeleteMapping("/deleteCampaign/{productId}/{shopperId}")
    public ResponseEntity deleteCapaign(@PathVariable("/productId") String productId,@PathVariable("shopperId") String shopperId) throws SqlExceptionCustom {
        this.campaignService.deleteCampaign(productId,shopperId);
        return ResponseEntity.ok().build();
    }


}
