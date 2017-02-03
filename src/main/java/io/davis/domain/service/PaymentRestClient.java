package io.davis.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Davis on 17/2/3.
 */
public class PaymentRestClient {
  @Autowired
  @Qualifier("restTemplate")
  private transient RestTemplate restTemplate;

  public String getBraintreeCustomerId(String customerId) {
    // TODO: 17/2/3
    return "";
  }

  public void saveBraintreeCustomerId(String customerId, String braintreeCustomerId) {
    // TODO: 17/2/3
  }
}
