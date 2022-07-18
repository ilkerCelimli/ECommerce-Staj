package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.entity.Orders;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.enums.OrderStatus;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.repository.OrderRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductSellService;
import com.portifolyo.mesleki1.services.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ProductSellServiceImpl extends BaseServicesImpl<Orders> implements ProductSellService {

    private final OrderRepository orderRepository;
    private final CampaignService campaignService;
    private final ProductService productService;
    private final AdressDtoMapper adressDtoMapper;

    public ProductSellServiceImpl(OrderRepository orderRepository, CampaignService campaignService,  ProductService productService, AdressDtoMapper adressDtoMapper) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.campaignService = campaignService;
        this.productService = productService;
        this.adressDtoMapper = adressDtoMapper;
    }

    public Orders orderChange(SellProductsDto dto) {
        CampaignInfo c = checkCampaign(dto.getProductId());

        Product p = productService.findProductForShopper(dto.getProductId(), dto.getShopperId());
        if (Objects.nonNull(c)) {
            if (c.getStartDate().before(new Date()) && c.getEndDate().after(new Date())) {
                BigDecimal discount = p.getPrice().divide(BigDecimal.valueOf(100),3, RoundingMode.CEILING).multiply(BigDecimal.valueOf(c.getDiscountRate()));
                Orders orders = new Orders();
                orders.setPrice(discount);
                orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
                orders.setActive(true);
                orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
                orders.setProduct(p);
                return orders;
            } else return null;
        } else {
            Orders orders = new Orders();
            orders.setProduct(p);
            orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
            orders.setActive(true);
            orders.setPrice(p.getPrice());

            orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
            return orders;
        }
    }

    @Override
    public boolean sellProducts(SellProductsDto dto) throws SqlExceptionCustom {
        Orders o = orderChange(dto);
        if (Objects.nonNull(o)) {
            save(o);
            return true;
        } else throw new SqlExceptionCustom();
    }

    @Override
    public CampaignInfo checkCampaign(String product) {
        return this.campaignService.findCampaignProductId(product);
    }

    @Override
    public void ChangeOrderStatus(String id, OrderStatus orderStatus) throws SqlExceptionCustom {
        Orders o = findById(id);
        o.setOrderStatus(orderStatus);
        update(o);

    }

    @Override
    @Transactional(rollbackFor = {SqlExceptionCustom.class})
    public void sellProductList(List<SellProductsDto> dto) throws SqlExceptionCustom {
        List<Orders> list = new ArrayList<>();

        for (SellProductsDto i : dto) {
            Orders o = orderChange(i);
            if (Objects.nonNull(o)) {
                list.add(o);
            } else throw new SqlExceptionCustom();

        }

        List<Orders> order = orderRepository.saveAll(list);

        for (Orders o : order) {
            if (Objects.isNull(o.getId()) || Strings.isEmpty(o.getId()) || Strings.isBlank(o.getId())) {
                throw new SqlExceptionCustom();
            }
        }


    }
}
