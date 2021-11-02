package com.rishabh.lendingengine.domain.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Balance {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    @MapKeyClass(Currency.class)
    @OneToMany(targetEntity = Money.class,cascade = CascadeType.ALL)
    public Map<Currency,Money> moneyMap = new HashMap<>();

    public void topUp(final Money money){
        if(moneyMap.get(money.getCurrency())==null){
            moneyMap.put(money.getCurrency(),money);
        }else{
            moneyMap.put(money.getCurrency(),
                    moneyMap.get(money.getCurrency()).add(money));
        }
    }

    public void withdraw(final Money money){
        final Money moneyInBalance = moneyMap.get(money.getCurrency());
        if(moneyInBalance==null){
            throw new IllegalArgumentException();
        }else{
            moneyMap.put(money.getCurrency(),moneyMap.get(money.getCurrency()).minus(money));
        }
    }

    public Map<Currency, Money> getMoneyMap() {
        return moneyMap;
    }
}
