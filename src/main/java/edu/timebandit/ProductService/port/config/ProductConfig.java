package edu.timebandit.ProductService.port.config;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ProductConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("host.docker.internal", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    CommandLineRunner commandLineRunner(IProductRepository productRepository) {
        Map<String, String> housingInfo = Map.of("Diameter", "40mm", "Thickness", "12mm", "Material",
                "Stainless Steel", "Water Resistance", "100m", "Crystal", "Sapphire", "Bezel", "Stainless Steel");
        Map<String, String> generalInfo = Map.of("Movement", "Automatic", "Band Width", "20mm", "Band Material",
                "Stainless Steel", "Band Color", "Silver", "Dial Color", "Black", "Display Type", "Analog", "Clasp",
                "Invisible Double-locking Clasp", "Country of Origin", "Switzerland");
        List<String> features = new ArrayList<>();
        features.add("Date");
        features.add("Luminous Hands");
        features.add("Luminous Markers");
        features.add("Self Winding");


        List<Watch> newWatches = new ArrayList<>();
        newWatches.add(new Watch(UUID.fromString("58b74e3e-8e46-4419-bfc6-a8c43dfa694a"),
                "Rolex Submariner", "Rolex Submariner", 12913.00, generalInfo, housingInfo,
                features, 25, 1, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("3aba5511-18fd-4ba2-bb2c-ac90d6963dcc"),
                "Rolex GMT Master II", "Rolex GMT Master II", 15889.00, generalInfo,
                housingInfo, features, 6, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("da42d764-bcaf-48e6-a87f-8964354a9db0"),
                "Rolex Daytona", "Rolex Daytona", 14800.00, generalInfo, housingInfo, features,
                2, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("a697b980-9a6e-4964-a76a-9f0824d008ce"),
                "Rolex Explorer", "Rolex Explorer", 8499.00, generalInfo, housingInfo, features
                , 3, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("456ee8f1-ddce-4791-8aa1-d671438bce33"),
                "Rolex Yacht-Master", "Rolex Yacht-Master", 9885.00, generalInfo, housingInfo,
                features, 7, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("9febab40-4a2f-4e80-94bb-57477a045bef"),
                "Rolex Datejust", "Rolex Datejust", 7280.00, generalInfo, housingInfo, features
                , 0, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("5bcaf4b4-29b7-483d-abea-e813f66ee63e"),
                "Rolex Sea-Dweller", "Rolex Sea-Dweller", 14150.00, generalInfo, housingInfo
                , features, 14, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("1f15e18b-b74c-458e-8eb2-e074640ac181"),
                "Rolex Milgauss", "Rolex Milgauss", 10600.00, generalInfo, housingInfo, features
                , 35, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("7a0de738-cfde-40ea-ba45-34bd4a057398"),
                "Rolex Sky-Dweller", "Rolex Sky-Dweller", 49936.00, generalInfo, housingInfo
                , features, 1, 0, null, "Thumbnail Link", "Rolex"));
        newWatches.add(new Watch(UUID.fromString("82412ae4-70a7-4e91-b9bf-cadbe4ef55f5"),
                "Rolex Cellini", "Rolex Cellini", 3722.00, generalInfo, housingInfo, features,
                9, 0, null, "Thumbnail Link", "Rolex"));

        return args -> productRepository.saveAll(newWatches);
    }
}
