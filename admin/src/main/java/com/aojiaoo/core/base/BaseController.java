package com.aojiaoo.core.base;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<S extends BaseService> {
    @Autowired
    protected S service;

}
