<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.weather.WeatherService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>时间和天气</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }
        .section {
            margin-bottom: 30px;
            padding: 15px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .time-section {
            background-color: #e8f4f8;
            border-left: 5px solid #3498db;
        }
        .weather-section {
            background-color: #f8f6e8;
            border-left: 5px solid #f1c40f;
        }
        .current-time {
            font-size: 24px;
            text-align: center;
            color: #3498db;
            font-weight: bold;
        }
        .weather-info {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .weather-item {
            flex-basis: 48%;
            margin-bottom: 10px;
        }
        .weather-label {
            font-weight: bold;
            margin-right: 5px;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            color: #7f8c8d;
            font-size: 14px;
        }
        .refresh-btn {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .refresh-btn:hover {
            background-color: #34495e;
        }
        @media (max-width: 600px) {
            .weather-item {
                flex-basis: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>时间和天气信息</h1>
        
        <div class="section time-section">
            <h2>当前时间</h2>
            <div class="current-time">
                <%
                    // 获取当前时间
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String formattedDate = dateFormat.format(now);
                    out.println(formattedDate);
                %>
            </div>
        </div>
        
        <div class="section weather-section">
            <h2>天气预报</h2>
            <div class="weather-info">
                <%
                    try {
                        // 从WeatherService获取天气信息
                        WeatherService weatherService = new WeatherService();
                        String city = request.getParameter("city");
                        if (city == null || city.trim().isEmpty()) {
                            city = "北京"; // 默认城市
                        }
                        
                        // 获取天气数据
                        WeatherService.WeatherData weatherData = weatherService.getWeatherData(city);
                        
                        if (weatherData != null) {
                %>
                            <div class="weather-item">
                                <span class="weather-label">城市:</span>
                                <span><%= weatherData.getCity() %></span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">天气:</span>
                                <span><%= weatherData.getWeather() %></span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">温度:</span>
                                <span><%= weatherData.getTemperature() %>°C</span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">湿度:</span>
                                <span><%= weatherData.getHumidity() %>%</span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">风向:</span>
                                <span><%= weatherData.getWindDirection() %></span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">风力:</span>
                                <span><%= weatherData.getWindPower() %></span>
                            </div>
                            <div class="weather-item">
                                <span class="weather-label">更新时间:</span>
                                <span><%= weatherData.getUpdateTime() %></span>
                            </div>
                <%
                        } else {
                %>
                            <div style="color: red; text-align: center;">
                                无法获取天气数据，请稍后再试。
                            </div>
                <%
                        }
                    } catch (Exception e) {
                %>
                        <div style="color: red; text-align: center;">
                            获取天气数据时出错: <%= e.getMessage() %>
                        </div>
                <%
                    }
                %>
            </div>
        </div>
        
        <form action="index.jsp" method="get">
            <div style="text-align: center; margin-top: 20px;">
                <label for="city">输入城市名称:</label>
                <input type="text" id="city" name="city" placeholder="例如: 北京" 
                       value="<%= request.getParameter("city") != null ? request.getParameter("city") : "北京" %>">
                <button type="submit" class="refresh-btn">查询天气</button>
            </div>
        </form>
        
        <div class="footer">
            <p>© <%= new SimpleDateFormat("yyyy").format(new Date()) %> 时间和天气 Web 应用程序</p>
        </div>
    </div>
    
    <script>
        // 每60秒自动刷新页面以更新时间
        setTimeout(function() {
            location.reload();
        }, 60000);
    </script>
</body>
</html> 