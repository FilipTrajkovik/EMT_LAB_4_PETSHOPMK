package mk.ukim.finki.emt.productcatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ManufacturerId extends DomainObjectId {

    public ManufacturerId() {
        super(ManufacturerId.randomId(ManufacturerId.class).getId());
    }

    public ManufacturerId(String uuid) {
        super(uuid);
    }
}
