package io.davis.application.model.mapper;

import com.braintreegateway.CreditCard;

import io.davis.application.model.CreditCart;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/2/3.
 */
public final class CreditCartMapper {
  public static List<CreditCart> entityToModel(List<CreditCard> models) {
    return models.parallelStream().map(
        m -> {
          return entityToModel(m);
        }
    ).collect(Collectors.toList());
  }

  public static CreditCart entityToModel(CreditCard model) {
    CreditCart entity = new CreditCart();
    entity.setBin(model.getBin());
    entity.setCardholderName(model.getCardholderName());
    entity.setCardType(model.getCardType());
    entity.setCommercial(model.getCommercial().toString());
    entity.setCustomerId(model.getCustomerId());
    entity.setExpirationMonth(model.getExpirationMonth());
    entity.setExpirationYear(model.getExpirationYear());
    entity.setExpired(model.isExpired());
    entity.setToken(model.getToken());
    entity.setLast4(model.getLast4());
    return null;
  }
}
