package io.davis.domain.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import io.davis.application.model.CreditCart;
import io.davis.application.model.action.AddCreditCartAction;
import io.davis.application.model.mapper.CreditCartMapper;
import io.davis.application.model.mapper.CustomerRequestMapper;
import io.davis.application.model.mapper.TransactionRequestMapper;

import org.apache.commons.lang3.StringUtils;
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

  @Autowired
  private PaymentRestClient restClient;

  public String getClientToken(String customerId) {
    ClientTokenRequest customerRequest = new ClientTokenRequest();
    customerRequest.customerId(customerId);
    String clientToken = gateway.clientToken().generate(customerRequest);
    return clientToken;
  }

  public List<CreditCart> getCreditCarts(String customerId) {
    Customer customer = gateway.customer().find(restClient.getBraintreeCustomerId(customerId));
    if (customer == null) {
      LOG.debug("can not find customer by id : {}", customerId);
      return null;
    }
    return CreditCartMapper.entityToModel(customer.getCreditCards());
  }

  public List<CreditCart> addCreditCart(AddCreditCartAction addCreditCartAction) {

    String braintreeCustomerId = restClient.getBraintreeCustomerId(addCreditCartAction
        .getCustomerId());

    CustomerRequest request = CustomerRequestMapper.of(addCreditCartAction.getCreditCart());
    Result<Customer> result = null;
    if (StringUtils.isBlank(braintreeCustomerId)) {
      result = gateway.customer().create(request);
      restClient.saveBraintreeCustomerId(addCreditCartAction.getCustomerId(), result.getTarget()
          .getId());
    } else {
      result = gateway.customer().update(braintreeCustomerId, request);
    }

    return CreditCartMapper.entityToModel(result.getTarget().getCreditCards());
  }

  public Transaction checkout(BigDecimal decimalAmount, String token) {

    TransactionRequest request = TransactionRequestMapper.of(decimalAmount, token);
    Result<Transaction> result = gateway.transaction().sale(request);

    // TODO: 17/2/3 save transaction

    return result.getTarget();
  }
}
