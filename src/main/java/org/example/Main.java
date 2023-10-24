package org.example;

import org.example.utils.DbConnect;
import org.example.service.CountryService;
import org.example.service.DistrictService;
import org.example.service.QuarterService;
import org.example.service.RegionsService;

public class Main {
    public static void main(String[] args)  {

            DbConnect.initTables();
            CountryService countryService = new CountryService();
            RegionsService regionsService = new RegionsService();
            QuarterService quarterService = new QuarterService();
            DistrictService districtService = new DistrictService();
            System.out.println(countryService.save());
            System.out.println(regionsService.save());
            System.out.println(districtService.save());
            System.out.println(quarterService.save());
        }


}