package com.example.demo.entidade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequesicao is a Querydsl query type for Requesicao
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRequesicao extends EntityPathBase<Requesicao> {

    private static final long serialVersionUID = 1908050629L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequesicao requesicao = new QRequesicao("requesicao");

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final NumberPath<Integer> idRequesicao = createNumber("idRequesicao", Integer.class);

    public final StringPath observacaoRequesicao = createString("observacaoRequesicao");

    public final StringPath tipoRequesicao = createString("tipoRequesicao");

    public final QUsuario usuario;

    public QRequesicao(String variable) {
        this(Requesicao.class, forVariable(variable), INITS);
    }

    public QRequesicao(Path<? extends Requesicao> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequesicao(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequesicao(PathMetadata metadata, PathInits inits) {
        this(Requesicao.class, metadata, inits);
    }

    public QRequesicao(Class<? extends Requesicao> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.usuario = inits.isInitialized("usuario") ? new QUsuario(forProperty("usuario")) : null;
    }

}

