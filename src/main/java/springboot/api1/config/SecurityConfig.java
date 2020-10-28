package springboot.api1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springboot.api1.service.UserServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        /*
         * PasswordEncoder encoder = passwordEncoder(); UserBuilder users =
         * User.builder().passwordEncoder(password -> { return encoder.encode(password);
         * }); builder.inMemoryAuthentication()
         * .withUser(users.username("siritfelix").password("12345").roles("admin",
         * "user"));
         */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**", "/login/**").permitAll().anyRequest().authenticated().and()
                .csrf().disable().httpBasic().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
