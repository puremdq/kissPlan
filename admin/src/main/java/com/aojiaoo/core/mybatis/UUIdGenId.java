package com.aojiaoo.core.mybatis;

import com.aojiaoo.utils.IdGenerator;
import tk.mybatis.mapper.genid.GenId;

public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return IdGenerator.uuid();
    }
}