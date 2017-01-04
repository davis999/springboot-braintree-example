package io.davis.config;

import com.braintreegateway.BraintreeGateway;

/**
 * Created by Davis on 16/12/27.
 */
public class BraintreeFactory {

  private static BraintreeGateway gateway;

  public static BraintreeGateway getBraintreeGateway() {
    if (gateway == null) {
      gateway = new BraintreeGateway(
          BraintreeConfig.ENVIRONMENT,
          BraintreeConfig.MERCHANT_ID,
          BraintreeConfig.PUBLIC_KEY,
          BraintreeConfig.PRIVATE_KEY
      );
    }
    return gateway;
  }
}
