package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

/**
 * @author Anton Kharenko
 */
@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = LogInRequest.class)
@JsonDeserialize(as = LogInRequest.class)
public interface ILogInRequest {

    String getLogin();

    String getPassword();

}
