package edu.timebandit.ProductService.port.config;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import edu.timebandit.ProductService.core.domain.model.Watch;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;


@Configuration
public class SpringConfig {

    @Bean
    @Qualifier("ModelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Qualifier("BasketModelMapper")
    public ModelMapper BasketModelMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Watch, BasketWatchDTO> typeMap = mapper.createTypeMap(Watch.class, BasketWatchDTO.class);
        Converter<UUID, String> toString = ctx -> ctx.getSource() == null ? null : ctx.getSource().toString();
        typeMap.addMappings(m -> m.using(toString).map(Watch::getId, BasketWatchDTO::setId));
        return mapper;
    }
}
