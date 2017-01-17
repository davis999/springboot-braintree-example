package io.davis.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Davis on 16/12/27.
 */
@Service
public class PaymentService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

  @Autowired
  private BraintreeGateway gateway;

  public String getClientToken(String customerId) {
    ClientTokenRequest customerRequest = new ClientTokenRequest();
    customerRequest.customerId(customerId);
    String clientToken = gateway.clientToken().generate(customerRequest);
    return clientToken;
  }

  public List<CreditCard> getCreditCarts(String customerId) {
    Customer customer = gateway.customer().find(customerId);
    if (customer == null) {
      LOG.debug("can not find customer by id : {}", customerId);
      return null;
    }
    List<CreditCard> creditCards = customer.getCreditCards();
    return creditCards;
  }

  public List<CreditCard> updateCustomer(String customerId, String nonce) {
    CustomerRequest request = new CustomerRequest()
        .paymentMethodNonce(nonce);
    Result<Customer> result = gateway.customer().update(customerId, request);
    Customer customer = result.getTarget();
    if (customer == null) {
      LOG.debug("can not find customer by id : {}", customerId);
      return null;
    }
    List<CreditCard> creditCards = customer.getCreditCards();
    return creditCards;
  }

  public Transaction checkout(BigDecimal decimalAmount, String token) {

    TransactionRequest request = new TransactionRequest()
        .amount(decimalAmount)
        .paymentMethodToken(token)
        .options()
        .submitForSettlement(true)
        .done();
    Result<Transaction> result = gateway.transaction().sale(request);

    return result.getTarget();
  }
}
