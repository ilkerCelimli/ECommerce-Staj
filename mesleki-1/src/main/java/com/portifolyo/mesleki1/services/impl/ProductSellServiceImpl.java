package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Orders;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.repository.OrderRepository;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductSellService;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.ShopperService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductSellServiceImpl extends BaseServicesImpl<Orders> implements ProductSellService {

    private final OrderRepository orderRepository;
    private final CampaignService campaignService;
    private final ShopperService shopperService;
    private final ProductService productService;
    private final AdressDtoMapper adressDtoMapper;
    public ProductSellServiceImpl(OrderRepository orderRepository, CampaignService campaignService, ShopperService shopperService, ProductService productService, AdressDtoMapper adressDtoMapper) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.campaignService = campaignService;
        this.shopperService = shopperService;
        this.productService = productService;
        this.adressDtoMapper = adressDtoMapper;
    }

    @Override
    public boolean sellProducts(SellProductsDto dto) throws SqlExceptionCustom {
        Campaign c = checkCampaign(dto.getProductId(),dto.getShopperId());

        Product p = productService.findProductForShopper(dto.getProductId(), dto.getShopperId());
        if(Objects.nonNull(c)) {
          if(c.getStartDate().before(new Date()) && c.getEndDate().after(new Date())) {
              BigDecimal discount = p.getPrice().divide(BigDecimal.valueOf(c.getDiscountRate()));
              p.setPrice(discount);
              Orders orders = new Orders();
              orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
              orders.setActive(true);
              orders.setProduct(p);
              save(orders);
              return true;
          }
          throw new NotFoundException();
        }
        else {
            Orders orders = new Orders();
            orders.setProduct(p);
            orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
            save(orders);
            return true;
        }

    }

    @Override
    public Campaign checkCampaign(String product,String shopper ) {
        Optional<Campaign> o = this.campaignService.findCampaignProductId(product,shopper);
        if(o.isPresent()) {
            return o.get();
        }
        else return null;
    }
}
