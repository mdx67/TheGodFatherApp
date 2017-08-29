package com.br.god.father.utils;

import com.br.god.father.model.CustomerApp;

public class Utils {

    public static CustomerApp convertStringToCustomer(String value) {
        String id = value.substring(value.indexOf("id") + 3, value.indexOf(","));
        String name = value.substring(value.indexOf("name") + 5, value.indexOf("}"));

        return new CustomerApp(id, name);
    }

}
