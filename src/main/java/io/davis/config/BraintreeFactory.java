package io.davis.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by Davis on 16/12/27.
 */
@Configuration
public class BraintreeFactory {
  @Value("#{environment.BRAINTREE_MERCHANT_ID}")
  public String MERCHANT_ID;

  @Value("#{environment.BRAINTREE_PUBLIC_KEY}")
  public String PUBLIC_KEY;

  @Value("#{environment.BRAINTREE_PRIVATE_KEY}")
  public String PRIVATE_KEY;

  public Environment ENVIRONMENT;

  @Bean
  public BraintreeGateway getBraintreeGateway() {
    return new BraintreeGateway(
            ENVIRONMENT,
            MERCHANT_ID,
            PUBLIC_KEY,
            PRIVATE_KEY
        );
  }

  @Value("${braintree.environment}")
  public void setEnvironment(String environment) {
    switch (environment) {
      case "sanbox":
        ENVIRONMENT = Environment.SANDBOX;
        break;
      case "production":
        ENVIRONMENT = Environment.PRODUCTION;
        break;
      case "development":
        ENVIRONMENT = Environment.DEVELOPMENT;
        break;
      default:
        ENVIRONMENT = Environment.SANDBOX;
        break;
    }
  }
}
