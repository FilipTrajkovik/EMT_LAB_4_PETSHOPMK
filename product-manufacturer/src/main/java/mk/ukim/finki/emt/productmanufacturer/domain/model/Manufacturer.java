package mk.ukim.finki.emt.productmanufacturer.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="product_manufacturer")
public class Manufacturer extends AbstractEntity<ManufacturerId> {

    private String manufacturerName;

    private double manufacturerRating;

    private String manufacturerCountry;

    private String manufacturerCity;
}
