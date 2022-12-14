package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.167.161.205");
        System.out.println(geoIp);
    }

    @Test
    public void testInvalidIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("xxx.167.161.999");
        System.out.println(geoIp);
    }


}
