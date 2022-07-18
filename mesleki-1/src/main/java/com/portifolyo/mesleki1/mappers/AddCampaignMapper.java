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
        c.setStartDate(dto.getStartDate());
        c.setEndDate(dto.getEndDate());
        c.setDiscountRate(dto.getDiscountRate().doubleValue());
        c.setCode(randomString.nextString());
        c.setDescription(dto.getDescription());
        return c;
    }

}
