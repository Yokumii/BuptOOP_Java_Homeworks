# 时钟与天气 Web 应用

这是一个简单的 JSP 网站，用于显示当前时间并连接到心知天气 API 获取天气预报信息。

## 功能特点

- **实时时间显示**：显示当前系统时间，每秒自动更新
- **天气查询**：支持查询不同城市的天气情况，支持中文城市名
- **响应式设计**：适配不同屏幕尺寸的设备
- **数据可视化**：清晰展示天气数据（天气现象和温度）

## 技术栈

- **Java**：后端逻辑处理
- **JSP**：网页模板
- **JavaScript**：自动更新时间
- **HTML/CSS**：前端界面
- **Servlet**：处理 HTTP 请求
- **Maven**：项目管理
- **心知天气 API**：获取实时天气数据

## 如何运行

1. 确保已安装 Java 8+ 和 Maven
2. 在项目根目录执行：`mvn clean package`
3. 将生成的 WAR 文件部署到 Tomcat 或其他 Servlet 容器
4. 访问 `http://localhost:8080/ClockandWeather`

## 使用说明

1. 打开网页后，时间会自动每秒更新，无需手动刷新
2. 在输入框中输入城市名称（如"北京"、"上海"等）
3. 点击"查询天气"按钮获取该城市的天气信息
4. 点击"刷新页面"按钮可以刷新整个页面

## 编码处理

为了正确处理中文字符，本应用实现了以下措施：

1. 添加了 `CharacterEncodingFilter` 过滤器，确保所有请求和响应使用 UTF-8 编码
2. 表单提交使用 POST 方法，避免 URL 编码问题
3. 在 JSP 页面中设置了正确的编码声明
4. 在 HTTP 连接中设置了适当的字符集

## API 集成

本应用使用心知天气 API 获取实时天气数据。免费版 API 仅提供天气现象和温度数据。

### API 使用说明

1. 注册心知天气账号获取 API Key
2. 在 `WeatherService.java` 中替换 API_KEY 常量
3. API 调用频率有限制，请合理使用

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── weather/
│   │           ├── CharacterEncodingFilter.java  # 字符编码过滤器
│   │           └── WeatherService.java           # 天气服务
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                           # Web应用配置
│       └── index.jsp                             # 主页面
```

## 注意事项

- 免费版心知天气 API 仅提供有限的数据（天气现象和温度）
- 请确保网络连接正常，以便成功获取天气数据
- 如遇到中文乱码问题，请检查编码设置

## 许可证

MIT
