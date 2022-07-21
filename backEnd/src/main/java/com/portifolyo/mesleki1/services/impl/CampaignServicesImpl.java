package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddCampaignMapper;
import com.portifolyo.mesleki1.repository.CampaignRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampaignServicesImpl extends BaseServicesImpl<Campaign> implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final ProductService productService;
    private final AddCampaignMapper addCampaignMapper;

    public CampaignServicesImpl(CampaignRepository campaignRepository, ProductService productService, AddCampaignMapper addCampaignMapper) {
        super(campaignRepository);
        this.campaignRepository = campaignRepository;
        this.productService = productService;
        this.addCampaignMapper = addCampaignMapper;
    }

    @Override
    public CampaignInfo findCampaignProductId(String product) {
        Optional<CampaignInfo> o = this.campaignRepository.findCampainForProduct(product);
        return o.orElse(null);
    }

    @Override
    public void addCampaign(AddCampaignDto dto) throws SqlExceptionCustom {
        Product p = productService.findProductForShopper(dto.getProductId(), dto.getShopperId());
        if (Objects.nonNull(p)) {
           Campaign c = addCampaignMapper.toEntity(dto);
           c.setActive(true);
           c.setProduct(p);
           save(c);

        } else throw new NotFoundException();
    }

    @Override
    public void updateCampaign(AddCampaignDto dto) {
        Optional<Campaign> c = this.campaignRepository.findByProduct_IdEqualsAndProduct_Shopper_IdEquals(dto.getProductId(), dto.getShopperId());
        c.ifPresentOrElse(i -> {
            if (Objects.nonNull(dto.getStartDate())) {
                i.setStartDate(dto.getStartDate());
            }
            if (Objects.nonNull(dto.getEndDate())) {
                i.setEndDate(dto.getEndDate());
            }
            if (Objects.nonNull(dto.getDescription()) || Strings.isEmpty(dto.getDescription()) || Strings.isBlank(dto.getDescription())) {
                i.setDescription(dto.getDescription());
            }
            if (Objects.nonNull(dto.getDiscountRate())) {
                i.setDiscountRate(dto.getDiscountRate().doubleValue());
            }
            try {
                update(i);
            } catch (SqlExceptionCustom e) {
                e.printStackTrace();
            }
        }, NotFoundException::new);

    }

    @Override
    public void deleteCampaign(String productId) throws SqlExceptionCustom {
        Optional<CampaignInfo> o =this.campaignRepository.findCampainForProduct(productId);
        if (o.isPresent()) {
            delete(o.get().getId());
        } else throw new NotFoundException();
    }

    @Override
    public List<CampaignInfo> findCampaigns() {
        return this.campaignRepository.findCampaigns();
    }
}