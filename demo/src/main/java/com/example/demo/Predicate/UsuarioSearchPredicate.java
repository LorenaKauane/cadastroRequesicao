package com.example.demo.Predicate;

import com.example.demo.entidade.Requesicao;
import com.example.demo.entidade.Usuario;

import com.example.demo.repositorios.RequesicaoRepositorio;
import com.example.demo.repositorios.UsuarioRepositorio;


import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UsuarioSearchPredicate<T> {
    private String search;
    private Class<T> classe;

    public UsuarioSearchPredicate(Class<T> classe,String search){
        this.search = search;
        this.classe = classe;
    }


    private static Map<Class, Expression> expressions = new HashMap<>();
    {
        expressions.put(Integer.class, new NumberExpression());
        expressions.put(String.class, new StringExpression());
    }

    static Map<String, OperacaoLogica> operacoes = new HashMap<>();
    {
        operacoes.put("START", new OperacaoStart());
        operacoes.put("OR", new OperacaoOR());
    }

    private static Set<Class> tiposNumericos = new HashSet<>();
    {
        tiposNumericos.add(Integer.class);
        tiposNumericos.add(Long.class);
        tiposNumericos.add(Double.class);
        tiposNumericos.add(Float.class);
    }

    public BooleanExpression getExpression(){

        Pattern pattern = Pattern.compile("(OR|START )(\\w+)(:|<|>|<:|>:|=)([\\w\\.\\-\\///@]+)");

        //Pattern pattern = Pattern.compile("(OR\\s|START\\s|,)(\\w+?)(:|<|>|<:|>:)|([\\w\\.\\-\\/]+)|(\\w+)");
        Matcher matcher = pattern.matcher(search);
        BooleanExpression exp = null;
        System.out.println(search);


        while (matcher.find()) {
            String key = matcher.group(2);
            String valor = matcher.group(4);

            if (!(isString(valor) && isTipoNumeric(getTypeField(key, classe)))) {
                SearchCriteria criteria = new SearchCriteria(key, matcher.group(3).trim(), valor);
                OperacaoLogica operacaoLogica = operacoes.get(matcher.group(1).trim());
                exp = operacaoLogica.getOperacao(exp, criteria);
            }

        }

        return exp;
    }

    private BooleanExpression getPredicate(SearchCriteria criteria){
        String nomeDaClasse = tornarPrimeiraLetraMinuscula(classe.getSimpleName());
        PathBuilder<T> entityPath = new PathBuilder<T>(classe,nomeDaClasse);
        String key = criteria.getKey();
        PathBuilder<?> entityPathGeneric = entityPath;
        Class<?> classe = this.classe;

        Class tipo = getTypeField(key, classe);

        Expression expression = tipo.isEnum() ? expressions.get(String.class) : expressions.get(tipo);

        String valor = criteria.getValue();
        return expression.getExpression(entityPathGeneric, tipo, key, criteria.getOperation(), valor);
    }

    private Class getTypeField(String nomeCampo, Class classe) {
        Class<T> classeTemp = classe;
        while (classeTemp != null) {
            for (Field field : classeTemp.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(nomeCampo))
                    return field.getType();
            }
            classeTemp = (Class<T>) classeTemp.getSuperclass();
        }
        return null;
    }

    private String tornarPrimeiraLetraMinuscula(String nomeClasse) {
        String primeiraLetra = String.valueOf(nomeClasse.charAt(0));

        char primeiraLetraMinuscula = primeiraLetra.toLowerCase().charAt(0);

        StringBuffer nomeCorrigido = new StringBuffer(nomeClasse);
        nomeCorrigido.setCharAt(0, primeiraLetraMinuscula);

        return nomeCorrigido.toString();
    }

    interface OperacaoLogica {
        BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria);
    }

    class OperacaoStart implements OperacaoLogica {
        @Override
        public BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria) {
            return getPredicate(criteria);
        }
    }


    class OperacaoOR implements OperacaoLogica {
        @Override
        public BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria) {
            return exp.or(getPredicate(criteria));
        }
    }

    private boolean isTipoNumeric(Class classe) {
        return tiposNumericos.contains(classe);
    }

    private boolean isString(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
}
