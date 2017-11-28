package com.example.demo.Predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class StringExpression implements Expression {

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key, String operation, String val) {
        StringPath path = pathBuilder.getString(key);
        if (operation.equalsIgnoreCase(":")) {
            return path.equalsIgnoreCase(val);
        }
        return null;
    }

}
