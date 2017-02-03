package io.davis.application.model.action;

import io.davis.application.model.CreditCart;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 17/2/3.
 */
@Getter
@Setter
public class AddCreditCartAction {
  String action;
  String customerId;
  CreditCart creditCart;
}
