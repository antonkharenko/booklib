package com.antonkharenko.booklib;

import com.google.common.cache.CacheBuilderSpec;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;

/**
 * @author Anton Kharenko
 */
public class BooklibConfiguration extends Configuration {

    @NotEmpty
    private String springConfigLocation;

    @NotNull
    private CacheBuilderSpec authenticationCachePolicy;

    public String getSpringConfigLocation() {
        return springConfigLocation;
    }

    public void setSpringConfigLocation(String springConfigLocation) {
        this.springConfigLocation = springConfigLocation;
    }

    public CacheBuilderSpec getAuthenticationCachePolicy() {
        return authenticationCachePolicy;
    }

    public void setAuthenticationCachePolicy(CacheBuilderSpec authenticationCachePolicy) {
        this.authenticationCachePolicy = authenticationCachePolicy;
    }
}
