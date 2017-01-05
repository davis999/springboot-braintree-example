package io.davis.controller;

import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.ValidationError;

import io.davis.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Davis on 16/12/27.
 */
@Controller
public class CheckoutController {

  @Autowired
  private CheckoutService checkoutService;

  private Transaction.Status[] TRANSACTION_SUCCESS_STATUSES = new Transaction.Status[] {
      Transaction.Status.AUTHORIZED,
      Transaction.Status.AUTHORIZING,
      Transaction.Status.SETTLED,
      Transaction.Status.SETTLEMENT_CONFIRMED,
      Transaction.Status.SETTLEMENT_PENDING,
      Transaction.Status.SETTLING,
      Transaction.Status.SUBMITTED_FOR_SETTLEMENT
  };

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String root(Model model) {
    return "redirect:checkouts";
  }

  @RequestMapping(value = "/checkouts", method = RequestMethod.GET)
  public String checkout(Model model) {
    String clientToken = checkoutService.getClientToken();
    model.addAttribute("clientToken", clientToken);
    return "checkouts/new";
  }

  @RequestMapping(value = "/checkouts", method = RequestMethod.POST)
  public String postForm(@RequestParam("amount") String amount, @RequestParam
      ("payment_method_nonce") String nonce, Model model, final RedirectAttributes
                             redirectAttributes) {

    BigDecimal decimalAmount;
    try {
      decimalAmount = new BigDecimal(amount);
    } catch (NumberFormatException e) {
      redirectAttributes.addFlashAttribute("errorDetails", "Error: 81503: Amount is an invalid " +
          "format.");
      return "redirect:checkouts";
    }

    Result<Transaction> result = checkoutService.checkout(decimalAmount, nonce);

    if (result.isSuccess()) {
      Transaction transaction = result.getTarget();
      return "redirect:checkouts/" + transaction.getId();
    } else if (result.getTransaction() != null) {
      Transaction transaction = result.getTransaction();
      return "redirect:checkouts/" + transaction.getId();
    } else {
      String errorString = "";
      for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
        errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
      }
      redirectAttributes.addFlashAttribute("errorDetails", errorString);
      return "redirect:checkouts";
    }
  }

  @RequestMapping(value = "/checkouts/{transactionId}")
  public String getTransaction(@PathVariable String transactionId, Model model) {
    Transaction transaction;
    CreditCard creditCard;
    Customer customer;

    try {
      transaction = checkoutService.getTransactionById(transactionId);
      creditCard = transaction.getCreditCard();
      customer = transaction.getCustomer();
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      return "redirect:/checkouts";
    }

    model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains
        (transaction.getStatus()));
    model.addAttribute("transaction", transaction);
    model.addAttribute("creditCard", creditCard);
    model.addAttribute("customer", customer);

    return "checkouts/show";
  }
}
