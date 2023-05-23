package mk.ukim.finki.emt.productcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.Category;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ManufacturerId;
import mk.ukim.finki.emt.productcatalog.xport.rest.ProductResource;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
        Product p1 = Product.build("Bakers", "Hrana za kuche", 10, Money.valueOf(Currency.MKD, 500), new Category(), new ManufacturerId());
        Product p2 = Product.build("CatToysUS", "Igrachka za mache", 5, Money.valueOf(Currency.MKD, 100), new Category(), new ManufacturerId());

        if(productRepository.findAll().isEmpty()){
            productRepository.saveAll(Arrays.asList(p1, p2));
        }
    }
}
