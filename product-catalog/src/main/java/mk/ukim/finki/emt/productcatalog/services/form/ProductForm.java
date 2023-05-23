package mk.ukim.finki.emt.productcatalog.services.form;

import lombok.Data;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.Category;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ManufacturerId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class ProductForm {

    private String productName;

    private String productDescription;

    private int sales = 0;

    private Money productPrice;

    private Category category;

    private ManufacturerId manufacturerId;
}
