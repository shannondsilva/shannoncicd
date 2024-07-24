package com.example.utils;

import java.util.List;

public class DataArray {

    public List<CustomerInfo> getCustData() {
        return custData;
    }

    public void setCustData(List<CustomerInfo> custData) {
        this.custData = custData;
    }

    private List<CustomerInfo> custData;
}
