package demo.graphql.data;

import demo.graphql.entities.CategoriaInvestimentoEntity;
import demo.graphql.entities.InvestimentosEntity;
import demo.graphql.enuns.TiposInvestimentos;
import demo.graphql.repository.InvestimentoRespository;
import graphql.schema.DataFetcher;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GraphQLDataFetchers {

    private final InvestimentoRespository investimentoRespository;

    public GraphQLDataFetchers(InvestimentoRespository investimentoRespository) {
        this.investimentoRespository = investimentoRespository;
    }

    public DataFetcher<CategoriaInvestimentoEntity> getCategoriaInvestimentoBySigla() {
        return dataFetchingEnvironment -> {
            String sigla = dataFetchingEnvironment.getArgument("sigla");
            return investimentoRespository.findAll().stream()
                    .filter(investimento -> investimento.getSigla().equals(sigla))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<List<CategoriaInvestimentoEntity>> getCategoriaInvestimentoByTipoInvestimento() {
        return dataFetchingEnvironment -> {
            String tipo = dataFetchingEnvironment.getArgument("tipo");
            TiposInvestimentos tiposInvestimentos = TiposInvestimentos.valueOf(tipo);
            return investimentoRespository.findAll().stream()
                    .filter(investimento -> investimento.getInvestimentosEntity().getTiposInvestimentos().equals(tiposInvestimentos))
                    .collect(Collectors.toList());
        };
    }

    public DataFetcher<InvestimentosEntity> getInvestimento() {
        return dataFetchingEnvironment -> {
            CategoriaInvestimentoEntity categoriaInvestimento = dataFetchingEnvironment.getSource();
            InvestimentosEntity investimento = categoriaInvestimento.getInvestimentosEntity();
            return investimentoRespository.findInvestimentos().stream()
                    .filter(investimentos -> investimentos.getId().equals(investimento.getId()))
                    .findFirst()
                    .orElse(null);
        };
    }
}
