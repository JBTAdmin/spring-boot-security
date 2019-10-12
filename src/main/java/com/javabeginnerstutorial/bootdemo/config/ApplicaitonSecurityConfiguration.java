package com.javabeginnerstutorial.bootdemo.config;

import com.javabeginnerstutorial.bootdemo.config.db.DatabaseSecurityConfiguration;
import com.javabeginnerstutorial.bootdemo.config.ldap.LdapSecurityConfiguration;
import com.javabeginnerstutorial.bootdemo.config.oauth2.OAuth2SecurityConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity(debug = true)
public class ApplicaitonSecurityConfiguration {

    @Bean
    @ConditionalOnProperty(name="appsecurity.method", havingValue = "DB")
    public DatabaseSecurityConfiguration databaseSecurityConfiguration(){
        return new DatabaseSecurityConfiguration();
    }

    @Bean
    @ConditionalOnProperty(name="appsecurity.method", havingValue = "LDAP")
    public LdapSecurityConfiguration ldapSecurityConfiguration(){
        return new LdapSecurityConfiguration();
    }

    @Bean
    @ConditionalOnProperty(name="appsecurity.method", havingValue = "OAuth")
    public OAuth2SecurityConfiguration oAuth2SecurityConfiguration(){
        return new OAuth2SecurityConfiguration();
    }
}
