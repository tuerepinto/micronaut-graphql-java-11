package demo.graphql.mutation;

import demo.graphql.entities.CategoriaInvestimentoEntity;
import demo.graphql.entities.InvestimentosEntity;
import demo.graphql.enuns.SubcategoriaInvestimento;
import demo.graphql.enuns.TiposInvestimentos;
import demo.graphql.repository.InvestimentoRespository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetcher;

import javax.inject.Singleton;

@Singleton
@SuppressWarnings("Duplicates")
public class MutationResolver implements GraphQLMutationResolver {

    private final InvestimentoRespository investimentoRespository;

    public MutationResolver(InvestimentoRespository investimentoRespository) {
        this.investimentoRespository = investimentoRespository;
    }

    public DataFetcher<Boolean> create() {
        return dataFetchingEnvironment -> {
            CategoriaInvestimentoEntity categoriaInvestimento = dataFetchingEnvironment.getSource();

            String sigla = dataFetchingEnvironment.getArgument("sigla");
            SubcategoriaInvestimento subcategoriaInvestimento = categoriaInvestimento.getSubcategoriaInvestimento();
            String decricao = dataFetchingEnvironment.getArgument("decricao");
            TiposInvestimentos tiposInvestimentos = categoriaInvestimento.getInvestimentosEntity().getTiposInvestimentos();

            return investimentoRespository.create(new CategoriaInvestimentoEntity("5",
                    SubcategoriaInvestimento.valueOf(subcategoriaInvestimento.toString()),
                    sigla,
                    decricao,
                    new InvestimentosEntity("5", TiposInvestimentos.valueOf(tiposInvestimentos.toString()))
            ));
        };
    }
}
