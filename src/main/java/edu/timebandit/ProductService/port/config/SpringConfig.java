package edu.timebandit.ProductService.port.config;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import edu.timebandit.ProductService.core.domain.model.Watch;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper BasketModelMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Watch, BasketWatchDTO> propertyMap = mapper.createTypeMap(Watch.class, BasketWatchDTO.class);
        propertyMap.addMapping(src -> src.getId().toString(), BasketWatchDTO::setId);
        return mapper;
    }
}
