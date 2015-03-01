package com.chang.im.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 서블릿 설정 (web.xml과 유사함)
 * @author cheochangwon
 *
 */
@Configuration
public class Initializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		servletContext
		.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
		.addMappingForUrlPatterns(null, false, "/*");
	}
}

/**
 * 사용한 XML
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">

	<display-name>RESTful Spring Security Demo</display-name>

	<!-- This points to root application context config files (not to MVC!) -->
	<context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<!--<param-value>classpath:spring-security.xml</param-value>-->
		<param-value>com.github.virgo47.respsec.main.AppConfig</param-value>
	</context-param>

	<!-- This loads the root application context (i.e. service layer and under) -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
		<dispatcher>REQUEST</dispatcher>
		<dispatcher>ERROR</dispatcher>
	</filter-mapping>

	<!-- Spring MVC servlet with customized configuration location -->
	<servlet>
		<servlet-name>mvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/spring-mvc.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>mvc</servlet-name>
		<!--
		This cannot be /*, it must be bound to "default" servlet (/).
		Otherwise JSPs are not displayed correctly, e.g. http://localhost:8080/respsec/
		-->
		<url-pattern>/</url-pattern>
	</servlet-mapping>
</web-app>
 */