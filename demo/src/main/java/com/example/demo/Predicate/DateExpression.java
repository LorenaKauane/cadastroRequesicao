package com.example.demo.Predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DateExpression  implements Expression  {

    @Override
    public BooleanExpression getExpression(PathBuilder<?> pathBuilder, Class tipo, String key, String operation, String val) {
        DatePath path = pathBuilder.getDate(key, Date.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
        if (operation.equalsIgnoreCase(":")){

            try {
                Date data = sdf.parse(val);
                return path.eq(data);

            } catch (ParseException e ) {
                e.getMessage();
                //throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}
