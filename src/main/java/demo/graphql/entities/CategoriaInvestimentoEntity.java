package demo.graphql.entities;

import demo.graphql.enuns.SubcategoriaInvestimento;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;


@Introspected
public class CategoriaInvestimentoEntity {

    @Getter
    private final String id;

    @Getter
    private final SubcategoriaInvestimento subcategoriaInvestimento;

    @Getter
    private final String sigla;

    @Getter
    private final String descricao;

    @Getter
    private final InvestimentosEntity investimentosEntity;

    public CategoriaInvestimentoEntity(String id,
                                       SubcategoriaInvestimento subcategoriaInvestimento,
                                       String sigla,
                                       String descricao,
                                       InvestimentosEntity investimentosEntity) {
        this.id = id;
        this.subcategoriaInvestimento = subcategoriaInvestimento;
        this.sigla = sigla;
        this.descricao = descricao;
        this.investimentosEntity = investimentosEntity;
    }
}
