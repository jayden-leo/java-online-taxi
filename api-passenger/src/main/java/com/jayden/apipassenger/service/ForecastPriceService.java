package com.jayden.apipassenger.service;

import com.jayden.apipassenger.remote.ServicePriceClient;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.ForecastPriceDTO;
import com.jayden.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    ServicePriceClient servicePriceClient;

    /**
     * 根据 出发地和目的地经纬度 计算预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);

        return ResponseResult.success(forecast.getData());
    }
}
