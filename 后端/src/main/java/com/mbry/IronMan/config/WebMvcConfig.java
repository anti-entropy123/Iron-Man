package com.mbry.IronMan.Config;

// import java.io.File;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebMvcConfig implements WebMvcConfigurer {
//     /**
//      * 静态资源处理
//      **/
//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         File file = new File("/IronMan/dist/");
//         if (!file.exists()) {
//             file.mkdirs();
//         }
//         // registry.addResourceHandler("/").addResourceLocations("file:/IronMan/dist/index.html");
//         registry.addResourceHandler("/*").addResourceLocations("/IronMan/dist/");
//         registry.addResourceHandler("/static/**").addResourceLocations("/IronMan/dist/static/");
//     }
// }