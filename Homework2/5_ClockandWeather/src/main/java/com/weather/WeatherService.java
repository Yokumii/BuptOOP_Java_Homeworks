package com.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 天气服务类，负责从天气API获取天气数据
 */
public class WeatherService {
    
    // 使用心知天气API，免费版本
    private static final String WEATHER_API_URL = "https://api.seniverse.com/v3/weather/now.json";
    private static final String API_KEY = "S8dxVBfr8UWlQHPya";
    
    /**
     * 获取指定城市的天气数据
     * 
     * @param city 城市名称
     * @return 天气数据对象
     * @throws Exception 若获取数据过程中出错
     */
    public WeatherData getWeatherData(String city) throws Exception {
        // 确保城市名称不为空
        if (city == null || city.trim().isEmpty()) {
            city = "北京";
        }
        
        // 实际调用API获取天气数据
        try {
            return getRealWeatherData(city);
        } catch (Exception e) {
            System.err.println("Error fetching weather data from API: " + e.getMessage());
            e.printStackTrace();
            // 如果API调用失败，回退到模拟数据
            return getSimulatedWeatherData(city);
        }
    }
    
    /**
     * 获取模拟的天气数据（作为API调用失败的后备方案）
     * 
     * @param city 城市名称
     * @return 模拟的天气数据对象
     */
    private WeatherData getSimulatedWeatherData(String city) {
        // 模拟数据
        WeatherData weatherData = new WeatherData();
        weatherData.setCity(city);
        
        // 根据不同城市模拟不同天气
        if (city.contains("北京")) {
            weatherData.setWeather("晴");
            weatherData.setTemperature("26");
        } else if (city.contains("上海")) {
            weatherData.setWeather("多云");
            weatherData.setTemperature("28");
        } else if (city.contains("广州")) {
            weatherData.setWeather("小雨");
            weatherData.setTemperature("30");
        } else if (city.contains("深圳")) {
            weatherData.setWeather("阵雨");
            weatherData.setTemperature("29");
        } else {
            // 默认数据
            weatherData.setWeather("晴");
            weatherData.setTemperature("25");
        }
        
        // 设置更新时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        weatherData.setUpdateTime(sdf.format(new Date()));
        
        return weatherData;
    }
    
    /**
     * 使用心知天气API获取天气数据
     * 
     * @param city 城市名称
     * @return 天气数据对象
     * @throws Exception 若获取数据过程中出错
     */
    public WeatherData getRealWeatherData(String city) throws Exception {
        // 确保城市名称使用UTF-8编码
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        
        // 构建API URL，添加城市名、API密钥和语言、单位参数
        String urlStr = WEATHER_API_URL + "?key=" + API_KEY + 
                "&location=" + encodedCity + 
                "&language=zh-Hans" + 
                "&unit=c";
        
        System.out.println("请求URL: " + urlStr);
        
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        
        int responseCode = conn.getResponseCode();
        System.out.println("响应代码: " + responseCode);
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        
        // 打印API响应以便调试
        String jsonResponse = response.toString();
        System.out.println("API响应: " + jsonResponse);
        
        // 解析JSON响应
        WeatherData weatherData = new WeatherData();
        weatherData.setCity(city);
        
        // 使用正则表达式从JSON中提取数据
        // 天气描述
        Pattern pattern = Pattern.compile("\"text\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            weatherData.setWeather(matcher.group(1));
        }
        
        // 温度
        pattern = Pattern.compile("\"temperature\":\"([^\"]+)\"");
        matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            weatherData.setTemperature(matcher.group(1));
        }
        
        // 尝试提取更新时间
        pattern = Pattern.compile("\"last_update\":\"([^\"]+)\"");
        matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            String lastUpdate = matcher.group(1);
            // 转换ISO 8601格式的时间为本地时间格式
            try {
                SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                Date date = isoFormat.parse(lastUpdate);
                SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                weatherData.setUpdateTime(localFormat.format(date));
            } catch (Exception e) {
                // 如果解析失败，使用当前时间
                weatherData.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        } else {
            // 如果没有找到更新时间，使用当前时间
            weatherData.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        
        return weatherData;
    }
    
    /**
     * 天气数据类，用于存储天气信息
     */
    public static class WeatherData {
        private String city;           // 城市
        private String weather;        // 天气状况
        private String temperature;    // 温度
        private String updateTime;     // 更新时间
        
        // Getters and Setters
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getWeather() {
            return weather;
        }
        
        public void setWeather(String weather) {
            this.weather = weather;
        }
        
        public String getTemperature() {
            return temperature;
        }
        
        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
        
        public String getUpdateTime() {
            return updateTime;
        }
        
        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
} 