package mk.ukim.finki.emt.productcatalog.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Manufacturer implements ValueObject {

    private final ManufacturerId id;

    private String manufacturerName;

    private double manufacturerRating;

    private String manufacturerCountry;

    private String manufacturerCity;

    public Manufacturer() {
        this.id=ManufacturerId.randomId(ManufacturerId.class);
        this.manufacturerName = "";
        this.manufacturerRating = 1.0;
        this.manufacturerCountry = "";
        this.manufacturerCity = "";
    }

    @JsonCreator
    public Manufacturer(@JsonProperty("id") ManufacturerId id,
                        @JsonProperty("manufacturerName") String manufacturerName,
                        @JsonProperty("manufacturerRating") double manufacturerRating,
                        @JsonProperty("manufacturerCountry") String manufacturerCountry,
                         @JsonProperty("manufacturerCity") String manufacturerCity) {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.manufacturerRating = manufacturerRating;
        this.manufacturerCountry = manufacturerCountry;
        this.manufacturerCity = manufacturerCity;
    }
}
