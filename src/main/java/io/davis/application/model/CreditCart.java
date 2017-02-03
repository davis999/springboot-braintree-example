package io.davis.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

/**
 * Created by Davis on 17/2/3.
 */
@Getter
@Setter
public class CreditCart {
  private String bin;
  private String cardholderName;
  private String cardType;
  private String customerId;
  private String expirationMonth;
  private String expirationYear;
  private boolean isExpired;
  private String last4;
  private String commercial;
  private String token;
  private Calendar updatedAt;
  private String cvv;
}
