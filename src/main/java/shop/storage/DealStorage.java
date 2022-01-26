package shop.storage;


import shop.data.DataClass;
import shop.model.Deal;

import java.util.LinkedList;
import java.util.List;

public class DealStorage {
    private List<Deal> listdeals = new LinkedList<>();


    public void add(Deal deal) {
        listdeals.add(deal);
        serial();
    }


    public void printAlldeals() {
        for (Deal listdeal : listdeals) {
            System.out.println(listdeal);
        }
    }


    public void printWithTaxes() {
        double net = 0.0;
        for (Deal listdeal : listdeals) {
            net = net + listdeal.getDeal_amount();
        }
        System.out.println(net);

    }

    public void serial() {
        DataClass.serializeDeal(listdeals);
    }

    public void initData() {
        List<Deal> deals = DataClass.deSerializeDeals();
        if (deals != null) {
            listdeals = deals;
        }
    }

}
