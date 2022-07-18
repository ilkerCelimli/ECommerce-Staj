package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Orders;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.enums.OrderStatus;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.repository.OrderRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductSellService;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.ShopperService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
        CampaignInfo c = checkCampaign(dto.getProductId());

        Product p = productService.findProductForShopper(dto.getProductId(), dto.getShopperId());
        if (Objects.nonNull(c)) {
            if (c.getStartDate().before(new Date()) && c.getEndDate().after(new Date())) {
                BigDecimal discount = p.getPrice().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(c.getDiscountRate()));
                Orders orders = new Orders();
                orders.setPrice(discount);
                orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
                orders.setActive(true);
                orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
                orders.setProduct(p);
                save(orders);
                return true;
            } else return false;
        } else {
            Orders orders = new Orders();
            orders.setProduct(p);
            orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
            orders.setActive(true);
            orders.setPrice(p.getPrice());

            orders.setAdress(adressDtoMapper.toEntity(dto.getAdressDto()));
            save(orders);
            return true;
        }
    }

    @Override
    public CampaignInfo checkCampaign(String product) {
        CampaignInfo c = this.campaignService.findCampaignProductId(product);
        return c;
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
            CampaignInfo c = checkCampaign(i.getProductId());
            Product p = productService.findProductForShopper(i.getProductId(), i.getShopperId());
            if (Objects.nonNull(c)) {
                if (c.getStartDate().before(new Date()) && c.getEndDate().after(new Date())) {
                    BigDecimal discount = p.getPrice().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(c.getDiscountRate()));
                    Orders orders = new Orders();
                    orders.setPrice(discount);
                    orders.setAdress(adressDtoMapper.toEntity(i.getAdressDto()));
                    orders.setActive(true);
                    orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
                    orders.setProduct(p);
                    list.add(orders);
                }
            } else {
                Orders orders = new Orders();
                orders.setProduct(p);
                orders.setOrderStatus(OrderStatus.ORDER_TAKEN);
                orders.setActive(true);
                orders.setPrice(p.getPrice());

                orders.setAdress(adressDtoMapper.toEntity(i.getAdressDto()));
                list.add(orders);

            }
        }

       List<Orders> order =  orderRepository.saveAll(list);

        for(Orders o : order) {
            if(Objects.isNull(o.getId()) || Strings.isEmpty(o.getId()) || Strings.isBlank(o.getId())){
                throw new SqlExceptionCustom();
            }
        }


    }
}
