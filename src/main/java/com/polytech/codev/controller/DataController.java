package com.polytech.codev.controller;

import com.polytech.codev.appels.AppelApi;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {


    @GetMapping("/getData")
    public Object findAllData()
    {
        Object data = null;

        try {
            AppelApi appelApi = new AppelApi();
            String str = appelApi.getData();
            Gson gson = new Gson();
            Type listType = new TypeToken<Object>(){}.getType();
            data = gson.fromJson(str, listType);
            //LinkedTreeMap<Object, Object> test = (LinkedTreeMap<Object, Object>) data;
            //data = test.get("records");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }


}
