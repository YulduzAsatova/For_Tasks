package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.example.model.Regions;
import org.example.service.CountryService;
import org.example.service.DistrictService;
import org.example.service.QuarterService;
import org.example.service.RegionsService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            CountryService countryService = new CountryService();
            RegionsService regionsService = new RegionsService();
            QuarterService quarterService = new QuarterService();
            DistrictService districtService = new DistrictService();
            //System.out.println(countryService.save());
            //System.out.println(regionsService.save());
            //System.out.println(quarterService.save());
            System.out.println(districtService.save());
        }


}