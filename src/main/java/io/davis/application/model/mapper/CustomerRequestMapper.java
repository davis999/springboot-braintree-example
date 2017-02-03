package io.davis.application.model.mapper;

import com.braintreegateway.CustomerRequest;

/**
 * Created by Davis on 17/2/3.
 */
public final class CustomerRequestMapper {
  public static CustomerRequest of(CreditCart creditCart) {
    CustomerRequest result = new CustomerRequest()
        .creditCard()
        .number(creditCart.getCardholderName())
        .expirationYear(creditCart.getExpirationYear())
        .expirationMonth(creditCart.getExpirationMonth())
        .cvv(creditCart.getCvv())
        .options()
        .verifyCard(true)
        .done()
        .done();

    return result;
  }
}
