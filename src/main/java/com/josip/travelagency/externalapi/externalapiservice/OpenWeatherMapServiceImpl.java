package com.josip.travelagency.externalapi.externalapiservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josip.travelagency.externalapi.model.myip.MyIP;
import com.josip.travelagency.externalapi.model.openweathermap.OpenWeatherMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService{


    private static final String OPENWEATHERMAP_API_KEY = "c1599f9f7a5395b30ec105989953a5c8";

    private static final String OPENWEATHERMAP_ADDRESS = "http://api.openweathermap.org/data/2.5/";
    private static final String IPAPI_ADDRESS = "https://ipapi.co/";
    private static final String MYIP_ADDRESS = "https://api.myip.com";

    private static final String LOCALHOST_IP_V4 = "127.0.0.1";
    private static final String LOCALHOST_IP_V6 = "0:0:0:0:0:0:0:1";

    @Override
    public OpenWeatherMap getData(HttpServletRequest request) {

        String ip = getIpAddress(request);
        //String city = getCity(ip);  Due to very low limit of  https://ipapi.co/ratelimited/ I am using hardcoded value
        String hardcodedCity = "Osijek";
        // OpenWeatherMap openWeatherMap = getWeatherForCity(city);
        OpenWeatherMap openWeatherMap = getWeatherForCity(hardcodedCity);

        return openWeatherMap;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        if(ip.equals(LOCALHOST_IP_V4) || ip.equals(LOCALHOST_IP_V6)) {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(MYIP_ADDRESS, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MyIP myIP = new MyIP();

            try {
                myIP = mapper.readValue(result, MyIP.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            ip = myIP.getIp();
        }

        return ip;
    }

    private String getCity(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        String city = restTemplate.getForObject(IPAPI_ADDRESS + ip + "/city", String.class);
        return city;
    }

    private OpenWeatherMap getWeatherForCity(String city) {
        StringBuilder url = new StringBuilder();
        url.append(OPENWEATHERMAP_ADDRESS).append("weather?q=").append(city).append("&units=metric").append("&appid=").append(OPENWEATHERMAP_API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherMap openWeatherMap = new OpenWeatherMap();

        try {
            String response = restTemplate.getForObject(url.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            openWeatherMap = mapper.readValue(response, OpenWeatherMap.class);
        } catch (JsonProcessingException | HttpClientErrorException e) {
            return null;
        }

        return openWeatherMap;
    }
}
