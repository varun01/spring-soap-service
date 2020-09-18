package com.poc.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/service/*");
	}

	@Bean(name = "userProfileWsdl")
	public DefaultWsdl11Definition defaultWsdl11Definition(@Autowired @Qualifier("userProfile") XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserDetailsPortA");
		wsdl11Definition.setLocationUri("/service/user-profile");
		wsdl11Definition.setTargetNamespace("http://www.example.com/xml/newuser");
		wsdl11Definition.setSchema(userProfileSchema());
		return wsdl11Definition;
	}

	@Bean(name="userProfile")
	public XsdSchema userProfileSchema() {
		return new SimpleXsdSchema(new ClassPathResource("userprofile.xsd"));
	}
	
	@Bean(name = "getUserProfileWsdl")
	public DefaultWsdl11Definition defaultWsdl11DefinitionGetUser(@Autowired @Qualifier("getUserProfile") XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserDetailsPortB");
		wsdl11Definition.setLocationUri("/service/get-user-profile");
		wsdl11Definition.setTargetNamespace("http://www.example.com/xml/getuser");
		wsdl11Definition.setSchema(getUserProfileSchema());
		return wsdl11Definition;
	}
	
	@Bean(name="getUserProfile")
	public XsdSchema getUserProfileSchema() {
		return new SimpleXsdSchema(new ClassPathResource("getUserProfile.xsd"));
	}
	
}