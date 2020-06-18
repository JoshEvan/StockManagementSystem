package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import com.joshua.StockManagementSystem.joseph_api.model.Production;

import java.util.List;

public class IndexProductionRequestPayload {
  List<Production> productions;

  public List<Production> getProductions() {
    return productions;
  }

  public IndexProductionRequestPayload setProductions(List<Production> productions) {
    this.productions = productions;return this;
  }
}
