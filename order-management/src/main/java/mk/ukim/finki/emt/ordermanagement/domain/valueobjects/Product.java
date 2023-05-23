package mk.ukim.finki.emt.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Product implements ValueObject {

    private final ProductId id;
    private String productName;
    private String productDescription;
    private int sales = 0;
    private Money productPrice;
    private Category category;

    private Product() {
        this.id=ProductId.randomId(ProductId.class);
        this.productName= "";
        this.productDescription= "";
        this.sales = 0;
        this.productPrice = Money.valueOf(Currency.MKD,0);
        this.category=new Category();
    }

    @JsonCreator
    public Product(@JsonProperty("id") ProductId id,
                   @JsonProperty("productName") String productName,
                   @JsonProperty("productDescription") String productDescription,
                   @JsonProperty("sales") int sales,
                   @JsonProperty("productPrice") Money productPrice,
                   @JsonProperty("category") Category category) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.sales = sales;
        this.productPrice = productPrice;
        this.category = category;
    }
}
