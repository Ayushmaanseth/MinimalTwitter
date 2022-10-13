package com.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("user1")
                .password("$2a$12$dWgO5TzNUH6NkgEYPRYtr.Qv34RKJCUvoPkgUnci9MLRhimponyxq")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("user2")
                .password("$2a$12$ZxNz52./MaID6oGQvU7RcOJXqFOHdKr1sgg5X5.p4h5LEClNE7cq2")
                .roles("USER")
                .build();
        UserDetails user3 = User
                .withUsername("user3")
                .password("$2a$12$JfZbBUWqbrlWjEFlVk2LkePmL6FOuT7OG5kKPovf66k0VOlJxShcO")
                .roles("USER")
                .build();
        UserDetails user4 = User
                .withUsername("user4")
                .password("$2a$12$4DS3P2xXYNNTypqACBqsRuXasLlyUDu7aSjCfHiEXl6aaTpigtxka")
                .roles("USER")
                .build();
        UserDetails user5 = User
                .withUsername("user5")
                .password("$2a$12$YoOhmVSElJ6u4UN2vPcrAOH8oDaUf8EGBGWaMuKsqwtaJGIRQ063a")
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("u").passwordParameter("p")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> httpServletResponse.sendRedirect("/"))
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}