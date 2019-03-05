package com.aojiaoo.core.security;

import java.io.Serializable;
import java.security.Principal;

public class KissPlanPrincipal implements Principal, Serializable {


    private Integer id;
    private String name;

    public KissPlanPrincipal(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }


    public Integer getId() {
        return this.id;
    }
}
