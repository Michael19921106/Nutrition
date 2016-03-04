package com.zxxk.demo.nutrition.api;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 17:48
 * Description:
 */
public class NutritionApi {
    public static final String API = "http://daily.daxingedu.cn/m/article";

    public static NutritionService nutritionService;
    public static NutritionService createApi(){
        if (nutritionService == null){
            synchronized (NutritionApi.class){
                if (nutritionService == null){
                    nutritionService = RetrofitUtils.createApi(NutritionService.class,API);
                }
            }
        }
        return nutritionService;
    }
}  