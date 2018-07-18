package my.pro.ject.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/home",
            "/",
            "/auth",
            "/js/**",
            "/css/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/myproject/book/**").hasAnyAuthority("USER")
                .antMatchers("myproject/index/**").hasAnyAuthority("USER")
                .antMatchers("/**").authenticated()
                .and()
                .sessionManagement();
    }
}