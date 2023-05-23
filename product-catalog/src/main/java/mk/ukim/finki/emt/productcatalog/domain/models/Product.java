package mk.ukim.finki.emt.productcatalog.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.ManufacturerNotFound;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.ProductPriceNotValid;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.Category;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ManufacturerId;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
public class Product extends AbstractEntity<ProductId> {

    private String productName;

    private String productDescription;

    private int sales = 0;

    private Category category;

    @AttributeOverride(name="id", column = @Column(name="manufacturerId"))
    private ManufacturerId manufacturerId;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money productPrice;

    public Product() {
        super(ProductId.randomId(ProductId.class));
    }

    public static Product build(String productName,
                                String productDescription,
                                int sales,
                                Money productPrice,
                                Category category,
                                ManufacturerId manufacturerId){
        Product p = new Product();
        p.productName = productName;
        p.productDescription = productDescription;
        p.sales = sales;
        if (productPrice.getAmount()<0)
            throw new ProductPriceNotValid();
        p.productPrice = productPrice;
        p.category = category;
        if(manufacturerId==null)
            throw new ManufacturerNotFound();
        p.manufacturerId = manufacturerId;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }
}
