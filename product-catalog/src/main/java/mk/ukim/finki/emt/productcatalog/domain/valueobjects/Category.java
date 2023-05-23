package mk.ukim.finki.emt.productcatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Category implements ValueObject {

    private final String category;

    public Category() {
        this.category = "category";
    }
}
