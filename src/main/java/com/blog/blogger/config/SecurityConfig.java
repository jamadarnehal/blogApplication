package com.blog.blogger.config;


import com.blog.blogger.security.CustomUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @SneakyThrows
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        //object's consisting of username and password
//
//        UserDetails user = User.builder().username("nehal").password(passwordEncoder.
//                encode("password")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder.
//                encode("Admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
     auth.userDetailsService(userDetailsService).passwordEncoder(getEncodedPassword());

    }

    @Bean
    public PasswordEncoder getEncodedPassword(){
        return new BCryptPasswordEncoder();

    }
//    @Bean
//    public User user() {
//        return new User(); // You may need to customize the creation of the User instance.
//    }
}

