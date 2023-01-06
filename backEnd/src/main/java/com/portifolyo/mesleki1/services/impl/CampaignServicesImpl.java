package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddCampaignMapper;
import com.portifolyo.mesleki1.repository.CampaignRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void addCampaign(AddCampaignDto dto) throws SqlExceptionCustom {
        Product p = productService.findProductForShopper(dto.productId(), dto.shopperId());
        if (Objects.nonNull(p)) {
           Campaign c = addCampaignMapper.toEntity(dto);
           c.setActive(true);
           c.setProduct(p);
           save(c);

        } else throw new NotFoundException();
    }

    @Override
    @Transactional
    public void updateCampaign(AddCampaignDto dto) {
        Optional<Campaign> c = this.campaignRepository.findByProduct_IdEqualsAndProduct_Shopper_IdEquals(dto.productId(), dto.shopperId());
        c.ifPresentOrElse(i -> {
            if (Objects.nonNull(dto.startDate())) {
                i.setStartDate(dto.startDate());
            }
            if (Objects.nonNull(dto.endDate())) {
                i.setEndDate(dto.endDate());
            }
            if (Objects.nonNull(dto.description()) || Strings.isEmpty(dto.description()) || Strings.isBlank(dto.description())) {
                i.setDescription(dto.description());
            }
            if (Objects.nonNull(dto.discountRate())) {
                i.setDiscountRate(dto.discountRate().doubleValue());
            }
            try {
                update(i);
            } catch (SqlExceptionCustom e) {
                e.printStackTrace();
            }
        }, NotFoundException::new);

    }

    @Override
    @Transactional
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
