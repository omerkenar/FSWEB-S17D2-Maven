package com.workintech.s17d2.tax;

import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{
    @Override
    public Double getSimpleTaxRate() {
        return 15D;
    }

    @Override
    public Double getMiddleTaxRate() {
        return 25D;
    }

    @Override
    public Double getUpperTaxRate() {
        return 35D;
    }
}
