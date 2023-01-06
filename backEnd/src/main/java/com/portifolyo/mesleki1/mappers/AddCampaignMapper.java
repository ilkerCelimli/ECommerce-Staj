package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.utils.RandomString;
import org.springframework.stereotype.Component;


@Component
public class AddCampaignMapper {


    public Campaign toEntity(AddCampaignDto dto) {
        RandomString randomString = new RandomString();
        Campaign c = new Campaign();
        c.setStartDate(dto.startDate());
        c.setEndDate(dto.endDate());
        c.setDiscountRate(dto.discountRate().doubleValue());
        c.setCode(randomString.nextString());
        c.setDescription(dto.description());
        return c;
    }

}
