package io.davis.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

/**
 * Created by Davis on 16/12/27.
 */
public class BraintreeFactory {

  private static BraintreeGateway gateway;

  public static BraintreeGateway getBraintreeGateway() {
    if (gateway == null) {
      gateway = new BraintreeGateway(
          Environment.SANDBOX,
          BraintreeConfig.MERCHANT_ID,
          BraintreeConfig.PUBLIC_KEY,
          BraintreeConfig.PRIVATE_KEY
      );
    }
    return gateway;
  }
}
