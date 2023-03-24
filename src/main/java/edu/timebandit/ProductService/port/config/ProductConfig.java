package edu.timebandit.ProductService.port.config;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ProductConfig {

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
        newWatches.add(new Watch(UUID.randomUUID(), "Rolex Submariner", "Rolex Submariner", "12,913.00", generalInfo,
                housingInfo,
                features, 25, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(), "Rolex GMT Master II", "Rolex GMT Master II", "15,889.00",
                generalInfo,
                housingInfo, features, 6, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Daytona", "Rolex Daytona", "14,800.00", generalInfo,
                housingInfo, features,
                2, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Explorer", "Rolex Explorer", "8,499.00", generalInfo,
                housingInfo, features
                , 3, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Yacht-Master", "Rolex Yacht-Master", "9,885.00", generalInfo,
                null, null, 7, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Datejust", "Rolex Datejust", "7,280.00", generalInfo,
                housingInfo, features
                , 0, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Sea-Dweller", "Rolex Sea-Dweller", "14,150.00", generalInfo,
                housingInfo
                , features, 14, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Milgauss", "Rolex Milgauss", "10,600.00", generalInfo,
                housingInfo, features
                , 35, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Sky-Dweller", "Rolex Sky-Dweller", "49,936.00", generalInfo,
                housingInfo
                , features, 1, 0, null, "Rolex"));
        newWatches.add(new Watch(UUID.randomUUID(),"Rolex Cellini", "Rolex Cellini", "3,722.00", generalInfo,
                housingInfo, features,
                9, 0, null, "Rolex"));

        return args -> productRepository.saveAll(newWatches);
    }
}
