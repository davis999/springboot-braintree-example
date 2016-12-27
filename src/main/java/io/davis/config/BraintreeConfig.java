package io.davis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Davis on 16/12/26.
 */
@Component
public class BraintreeConfig {

  public static String MERCHANT_ID;

  public static String PUBLIC_KEY;

  public static String PRIVATE_KEY;

  @Value("${braintree.merchant_id}")
  public void setMerchantId(String merchantId) {
    MERCHANT_ID = merchantId;
  }

  @Value("${braintree.public_key}")
  public void setPublicKey(String publicKey) {
    PUBLIC_KEY = publicKey;
  }

  @Value("${braintree.private_key}")
  public void setPrivateKey(String privateKey) {
    PRIVATE_KEY = privateKey;
  }
}
