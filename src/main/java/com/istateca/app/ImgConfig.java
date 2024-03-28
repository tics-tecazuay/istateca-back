package com.istateca.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {

    @Value("${data_folder}")
    private String carpeta;
    @Value("${data_directory}")
    private String directorio;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registro){
        WebMvcConfigurer.super.addResourceHandlers(registro);
        registro.addResourceHandler(carpeta).addResourceLocations("file:"+directorio);
    }

}
