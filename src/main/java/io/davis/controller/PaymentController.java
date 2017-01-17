package io.davis.controller;

import com.braintreegateway.CreditCard;
import com.braintreegateway.Transaction;

import io.davis.service.PaymentService;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Davis on 16/12/27.
 */
@RestController
public class PaymentController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

  @Autowired
  private PaymentService paymentService;

  @ApiOperation(value = "get client token by customer id")
  @GetMapping("/token")
  public String getToken(@RequestParam String customerId) {
    LOG.debug("enter getToken, customer id is : {}", customerId);
    String token = paymentService.getClientToken(customerId);
    LOG.debug("end getToken, token is : {}", token);
    return token;
  }

  @ApiOperation("get credit carts by customer id")
  @GetMapping("/credit-cards")
  public List<CreditCard> getCreditCarts(@RequestParam String customerId) {
    LOG.debug("enter getCreditCarts, customer id is : {}", customerId);
    List<CreditCard> result = paymentService.getCreditCarts(customerId);
    LOG.debug("end getCreditCarts, result size is : {}", result.size());
    return result;
  }

  @ApiOperation("update customer credit cart")
  @PutMapping("/credit-cards")
  public List<CreditCard> updateCustomerCreditCart(@RequestParam String customerId,
                                                   @RequestParam String nonce) {
    LOG.debug("enter updateCustomerCreditCart, customer id is : {}, nonce is : {}", customerId,
        nonce);

    List<CreditCard> result = paymentService.updateCustomer(customerId, nonce);

    LOG.debug("end updateCustomerCreditCart, result size is : {}", result.size());

    return result;
  }

  @ApiOperation("checkout")
  @PostMapping("/payment")
  public Transaction checkout(@RequestParam String amount, @RequestParam
      String token) {
    LOG.debug("enter checkout, amount is : {}, token is : {}", amount, token);
    BigDecimal decimalAmount = null;
    try {
      decimalAmount = new BigDecimal(amount);
    } catch (NumberFormatException e) {
      LOG.debug("some thing wrong");
    }
    Transaction result = paymentService.checkout(decimalAmount, token);
    LOG.debug("end checkout, result is : {}", result);
    return result;
  }
}
