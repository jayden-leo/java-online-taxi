package com.jayden.servicemap.service;


import com.jayden.internalcommon.constant.AmapConfigConstants;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.dto.DicDistrict;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicemap.mapper.DicDistrictMapper;
import com.jayden.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {

        // 请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);

        // 解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getMessage());
        }
        JSONArray dicJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        String countryParentAddressCode = "0";
        insertDicDistrictFromJSON(dicJsonArray, countryParentAddressCode);

        return ResponseResult.success("");
    }

    private void insertDicDistrictFromJSON(JSONArray dicJsonArray, String countryParentAddressCode) {
        for (int i = 0; i < dicJsonArray.size(); i++){
            JSONObject dicJsonObject = dicJsonArray.getJSONObject(i);
            String dicAddressCode = dicJsonObject.getString(AmapConfigConstants.ADCODE);
            String dicAddressName = dicJsonObject.getString(AmapConfigConstants.NAME);
            String dicLevel = dicJsonObject.getString(AmapConfigConstants.LEVEL);
            if (dicLevel.trim().equals("street")){
                continue;
            }
            if (dicLevel.trim().equals("district")){
                insertDicDistrict(dicAddressCode,dicAddressName,dicLevel,countryParentAddressCode);
            }else{
                insertDicDistrict(dicAddressCode,dicAddressName,dicLevel,countryParentAddressCode);
                JSONArray subJsonArray = dicJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                insertDicDistrictFromJSON(subJsonArray,dicAddressCode);
            }
        }
    }

    public void insertDicDistrict(String addressCode, String addressName, String level, String parentAddressCode) {
        // 数据库对象
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        int levelInt = generateLevel(level);
        district.setLevel(levelInt);

        district.setParentAddressCode(parentAddressCode);

        // 插入数据库
        dicDistrictMapper.insert(district);
    }

    public int generateLevel(String level) {
        int levelInt = 0;
        if (level.trim().equals("country")) {
            levelInt = 0;
        } else if (level.trim().equals("province")) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        }
        return levelInt;
    }
}
