package demo.graphql.mutation;

import demo.graphql.entities.CategoriaInvestimentoEntity;
import demo.graphql.entities.InvestimentosEntity;
import demo.graphql.enuns.SubcategoriaInvestimento;
import demo.graphql.enuns.TiposInvestimentos;
import demo.graphql.repository.InvestimentoRespository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetcher;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MutationResolver implements GraphQLMutationResolver {

    private final InvestimentoRespository investimentoRespository;

    public MutationResolver(InvestimentoRespository investimentoRespository) {
        this.investimentoRespository = investimentoRespository;
    }

    public DataFetcher<List<CategoriaInvestimentoEntity>> create() {
        return dataFetchingEnvironment -> {
            CategoriaInvestimentoEntity categoriaInvestimento = dataFetchingEnvironment.getSource();

            String id = dataFetchingEnvironment.getArgument("id");
            String sigla = dataFetchingEnvironment.getArgument("sigla");
            String subcategoriaInvestimento = dataFetchingEnvironment.getArgument("subcategoriaInvestimento");
            String decricao = dataFetchingEnvironment.getArgument("decricao");
            String tiposInvestimentos = dataFetchingEnvironment.getArgument("investimentosEntity");

            return investimentoRespository.create(new CategoriaInvestimentoEntity(id,
                    SubcategoriaInvestimento.valueOf(subcategoriaInvestimento),
                    sigla,
                    decricao,
                    new InvestimentosEntity(id, TiposInvestimentos.valueOf(tiposInvestimentos))
            ));
        };
    }
}
