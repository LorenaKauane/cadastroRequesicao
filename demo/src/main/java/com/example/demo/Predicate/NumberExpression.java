package com.example.demo.Predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberOperation;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.HashMap;
import java.util.Map;

public class NumberExpression  implements Expression {

    static Map<String, NumberOperation> operacoes = new HashMap<>();
    {
        operacoes.put(":", new NumberOperationIgual());
    }
    @Override
    public BooleanExpression getExpression(PathBuilder<?> pathBuilder, Class tipo, String key, String operation, String val) {
        NumberPath path = pathBuilder.getNumber(key, tipo);
        NumberOperation numberOperation = operacoes.get(operation);
        return numberOperation.getNumberExpression(path, val);
    }

    interface NumberOperation {
        public BooleanExpression getNumberExpression(NumberPath path, String valor);
    }

    class NumberOperationIgual implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (val.equals("null")) {
                return path.isNull();
            }

            if(valorIsDecimal(val)){
                double value = Double.parseDouble(val);
                return path.eq(value);
            }else{
                Integer value = Integer.parseInt(val);
                return path.eq(value);
            }

        }
    }

    private boolean valorIsDecimal(String val){
        boolean contains = val.contains(".") || val.contains(",");
        return contains;
    }

}
