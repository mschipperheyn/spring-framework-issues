package org.springframework.issues.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@ComponentScan(basePackages="org.springframework.issues")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {	
//		registry.addInterceptor(new FreemarkerNativeRequestFilter());
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setDefaultEncoding("UTF-8");
		configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
		Properties properties = new Properties();
		properties.put("whitespace_stripping","true");
		properties.put("url_escaping_charset","UTF-8");
		configurer.setFreemarkerSettings(properties);
		return configurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		FreeMarkerViewResolver freemarker = new FreeMarkerViewResolver();
		freemarker.setCache(false);
		freemarker.setOrder(1);
		freemarker.setPrefix("/");
		freemarker.setSuffix(".ftl");
		freemarker.setContentType("text/html;charset=UTF-8");
		freemarker.setExposeSpringMacroHelpers(true);
		freemarker.setRequestContextAttribute("rc");
		freemarker.setExposeSessionAttributes(false);
		
		//In order to make sure that https doesn't switch back to http on redirect
		freemarker.setRedirectHttp10Compatible(false);
		registry.viewResolver(freemarker);
		
	}

}
