package annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "annot")
public class AppConfig {
  /*  @Bean
    public Samsung getPhone(){
        return new Samsung();
    }
   @Bean
    public MobileProcessor getProcessor(){
        return new SnapDragon();
   } if we  dont want to use bean we can use configuration and componentscan*/
}
