<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.weather.WeatherService" %>
<%
    // 设置请求编码为UTF-8，解决中文乱码问题
    request.setCharacterEncoding("UTF-8");
    
    // 获取城市参数
    String city = request.getParameter("city");
    if (city == null || city.trim().isEmpty()) {
        city = "北京"; // 默认城市
    }
%>
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
        .btn {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 20px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #34495e;
        }
        .btn-refresh {
            background-color: #3498db;
        }
        .btn-refresh:hover {
            background-color: #2980b9;
        }
        .btn-container {
            text-align: center;
            margin: 20px 0;
        }
        .weather-main {
            text-align: center;
            padding: 20px 0;
        }
        .weather-city {
            font-size: 24px;
            margin-bottom: 10px;
        }
        .weather-condition {
            font-size: 36px;
            font-weight: bold;
            color: #f39c12;
            margin-bottom: 10px;
        }
        .weather-temp {
            font-size: 48px;
            font-weight: bold;
            color: #e74c3c;
        }
        .weather-temp-unit {
            font-size: 24px;
            color: #e74c3c;
        }
        .weather-update {
            font-size: 12px;
            color: #95a5a6;
            margin-top: 20px;
        }
        .weather-note {
            font-size: 14px;
            color: #7f8c8d;
            text-align: center;
            margin-top: 20px;
            font-style: italic;
        }
        @media (max-width: 600px) {
            .weather-item {
                flex-basis: 100%;
            }
        }
    </style>
    <script>
        // 页面加载完成后执行
        window.onload = function() {
            // 启动时钟
            updateClock();
            // 每秒更新一次时钟
            setInterval(updateClock, 1000);
        };
        
        // 更新时钟函数
        function updateClock() {
            // 获取当前时间
            var now = new Date();
            
            // 格式化时间
            var year = now.getFullYear();
            var month = padZero(now.getMonth() + 1);
            var day = padZero(now.getDate());
            var hours = padZero(now.getHours());
            var minutes = padZero(now.getMinutes());
            var seconds = padZero(now.getSeconds());
            
            // 组合成中文格式的日期时间
            var formattedTime = year + '年' + month + '月' + day + '日 ' + hours + ':' + minutes + ':' + seconds;
            
            // 更新页面上的时间显示
            document.getElementById('current-time').innerHTML = formattedTime;
        }
        
        // 数字补零函数
        function padZero(num) {
            return (num < 10 ? '0' : '') + num;
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>时间和天气信息</h1>
        
        <div class="section time-section">
            <h2>当前时间</h2>
            <div class="current-time" id="current-time">
                <%
                    // 获取当前时间（初始值，之后会被JavaScript更新）
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String formattedDate = dateFormat.format(now);
                    out.println(formattedDate);
                %>
            </div>
            <div class="btn-container">
                <a href="index.jsp" class="btn btn-refresh">刷新页面</a>
            </div>
        </div>
        
        <div class="section weather-section">
            <h2>天气预报</h2>
            <%
                try {
                    // 从WeatherService获取天气信息
                    WeatherService weatherService = new WeatherService();
                    
                    // 获取天气数据
                    WeatherService.WeatherData weatherData = weatherService.getWeatherData(city);
                    
                    if (weatherData != null) {
            %>
                    <div class="weather-main">
                        <div class="weather-city"><%= weatherData.getCity() %></div>
                        <div class="weather-condition"><%= weatherData.getWeather() %></div>
                        <div class="weather-temp"><%= weatherData.getTemperature() %><span class="weather-temp-unit">°C</span></div>
                        <div class="weather-update">更新时间: <%= weatherData.getUpdateTime() %></div>
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
        
        <form action="index.jsp" method="post" accept-charset="UTF-8">
            <div style="text-align: center; margin-top: 20px;">
                <label for="city">输入城市名称:</label>
                <input type="text" id="city" name="city" placeholder="例如: 北京" 
                       value="<%= city %>">
                <button type="submit" class="btn">查询天气</button>
            </div>
        </form>
        
        <div class="footer">
            <p>© <%= new SimpleDateFormat("yyyy").format(new Date()) %> 时间和天气 Web 应用程序</p>
        </div>
    </div>
</body>
</html> 