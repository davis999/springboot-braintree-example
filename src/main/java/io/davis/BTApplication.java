package io.davis;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.MerchantAccount;
import com.braintreegateway.MerchantAccountRequest;
import com.braintreegateway.PaymentMethodNonce;
import com.braintreegateway.Result;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 17/1/17.
 */
public class BTApplication {
//
//  private static BraintreeGateway gateway = new BraintreeGateway(
//      Environment.SANDBOX,
//      "zd4ykzzngrhgnbdv",
//      "j55k9vx4y7kp48yw",
//      "e993538bfa75c350dac4f3c95b377e26"
//  );
//
//  private static Customer getCustomer(String customerId) {
//    Customer customer = gateway.customer().find(customerId);
//    return customer;
//  }
//
//  private static String getPaymentMethodNonce(String paymentMethodToken) {
//    Result<PaymentMethodNonce> result = gateway.paymentMethodNonce().create(paymentMethodToken);
//    String nonce = result.getTarget().getNonce();
//    return nonce;
//  }
//
//  /**
//   * 每个不同customerId 有不同的token
//   *
//   * @param customerId
//   * @return
//   */
//  private static String getTokenByCustomerId(String customerId) {
//    ClientTokenRequest request = new ClientTokenRequest();
//    request.customerId(customerId);
//    String token = gateway.clientToken().generate(request);
//    return token;
//  }
//
//  private static String getTokenWithoutCustomerId() {
//    return gateway.clientToken().generate();
//  }
//
//  public static void main(String[] args) {
////    validateCustomerCreditCart();
////    validateTokenByCustomerId();
////    createNewCustomerAndCreditCart();
////    updateCustomerCreditCart();
//    createNewCustomerAndCreditCart();
//  }
//
//  private static void createNewCustomerAndCreditCart() {
//    CustomerRequest request = new CustomerRequest()
//        .id("customer007")
//        .firstName("Mark");
//
//    Result<Customer> result = gateway.customer().create(request);
//  }
//
//  private static void updateCustomerCreditCart() {
//    CustomerRequest request = new CustomerRequest()
//        .creditCard()
//        .number("4111111111111111")
//        .expirationYear("2018")
//        .cvv("123")
//        .expirationMonth("09")
//        .done();
//
//    Result<Customer> result = gateway.customer().update("customer_123", request);
//    Customer customer = result.getTarget();
//
//    List<CreditCard> creditCards = customer.getCreditCards();
//
//    creditCards.parallelStream().forEach(
//        creditCard -> {
//          System.out.println("credit cart type : " + creditCard.getCardType());
//          System.out.println("credit cart last4 : " + creditCard.getLast4());
//          System.out.println("credit cart token : " + creditCard.getToken());
//        }
//    );
//  }
//
//  private static void validateCustomerCreditCart() {
////    String customerId = "21214352";
//    String customerId = "customer_123";
//    Customer customer = getCustomer(customerId);
//    List<CreditCard> creditCards = customer.getCreditCards();
//
//    creditCards.parallelStream().forEach(
//        creditCard -> {
//          System.out.println("credit cart type : " + creditCard.getCardType());
//          System.out.println("credit cart last4 : " + creditCard.getLast4());
//          System.out.println("credit cart token : " + creditCard.getToken());
//        }
//    );
//  }
//
//  private static void validateTokenByCustomerId() {
//    String customerId = "54429878";
//    String token1 = getTokenByCustomerId(customerId);
//    System.out.println("customer 1 token : " + token1);
//
//    customerId = "21214352";
//    String token2 = getTokenByCustomerId(customerId);
//    System.out.println("customer 2 token : " + token2);
//
//    System.out.println("customer 1 and 2 token is equal ? :" + token1.equals(token2));
//
//    String defaultToken = getTokenWithoutCustomerId();
//    System.out.println("token without customer id : " + defaultToken);
//
//    System.out.println("default token is equal to customer 1 ? :" + token1.equals(defaultToken));
//    System.out.println("default token is equal to customer 2 ? :" + token2.equals(defaultToken));
//  }
}
