package demo.graphql.entities;

import demo.graphql.enuns.SubcategoriaInvestimento;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

@Introspected
public class CategoriaInvestimentoEntity {

    @Getter
    @Setter
    private final String id;

    @Getter
    @Setter
    private final SubcategoriaInvestimento subcategoriaInvestimento;

    @Getter
    @Setter
    private final String sigla;

    @Getter
    @Setter
    private final String descricao;

    @Getter
    @Setter
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
