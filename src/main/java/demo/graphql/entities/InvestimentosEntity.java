package demo.graphql.entities;


import demo.graphql.enuns.TiposInvestimentos;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

@Introspected
public class InvestimentosEntity {

    @Getter
    private final String id;

    @Getter
    private final TiposInvestimentos tiposInvestimentos;

    public InvestimentosEntity(String id, TiposInvestimentos tiposInvestimentos) {
        this.id = id;
        this.tiposInvestimentos = tiposInvestimentos;
    }
}
