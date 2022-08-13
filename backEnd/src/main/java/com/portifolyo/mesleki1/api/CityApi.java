package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.repository.projections.projeciton.CityInfo;
import com.portifolyo.mesleki1.services.CityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/cities")
@RestController
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*",methods = {RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class CityApi {

    private final CityServices cityServices;

    @GetMapping("/findCities")
    public List<CityInfo> findCities() {
        return this.cityServices.findCities();
    }

}
