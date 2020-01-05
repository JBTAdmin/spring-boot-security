package com.javabeginnerstutorial.bootdemo.config.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Collections;

@Configuration
@ConditionalOnProperty(name="appsecurity.method", havingValue = "LDAP")
public class LdapSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LdapProperties ldapProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordAttribute("userPassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/person").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().httpBasic();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/h2/**");
    }


    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:389"), "dc=springframework,dc=org");
    }
}
