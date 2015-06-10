package com.antonkharenko.booklib;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Anton Kharenko
 */
public class BooklibConfiguration extends Configuration {

    @NotEmpty
    private String springConfigLocation;

    public String getSpringConfigLocation() {
        return springConfigLocation;
    }

    public void setSpringConfigLocation(String springConfigLocation) {
        this.springConfigLocation = springConfigLocation;
    }
}
