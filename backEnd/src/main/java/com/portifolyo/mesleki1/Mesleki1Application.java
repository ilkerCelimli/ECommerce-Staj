package com.portifolyo.mesleki1;

import com.portifolyo.mesleki1.security.jwt.AuthProvider;
import org.aspectj.bridge.ReflectionFactory;
import org.aspectj.util.Reflection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaAuditing


public class Mesleki1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mesleki1Application.class, args);
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(List.of(new AuthProvider()));
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
