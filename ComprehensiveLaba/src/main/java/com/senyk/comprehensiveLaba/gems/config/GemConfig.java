package com.senyk.comprehensiveLaba.gems.config;
import com.senyk.comprehensiveLaba.gems.dao.GemRepository;
import com.senyk.comprehensiveLaba.gems.entity.Gem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
//public class GemConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(GemRepository repository) {
//        return args -> {
//            Gem gem1 =new Gem("Diamond",12.3,1200.2,"ef");
//            Gem gem2 =new Gem("Amber",12.3,1200.2,"ef");
//            repository.saveAll(List.of(gem1,gem2));
//        };
//    }
//}
