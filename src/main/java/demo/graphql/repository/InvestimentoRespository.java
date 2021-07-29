package demo.graphql.repository;

import demo.graphql.entities.CategoriaInvestimentoEntity;
import demo.graphql.entities.InvestimentosEntity;
import demo.graphql.enuns.SubcategoriaInvestimento;
import demo.graphql.enuns.TiposInvestimentos;
import graphql.com.google.common.collect.Lists;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class InvestimentoRespository {

    private List<CategoriaInvestimentoEntity> categoriaInvestimento = Arrays.asList(
            new CategoriaInvestimentoEntity("01",
                    SubcategoriaInvestimento.valueOf("CERTIFICADO_DEPOSITO_BANCARIO"),
                    "CDB",
                    "A sigla CDB vem de Certificado de Depósito Bancário, que é um título de renda fixa emitido por bancos para captar dinheiro e financiar suas atividades. Em troca deste empréstimo de recursos ao banco, o mesmo irá devolver ao investidor a quantia aplicada mais o juro acordado no momento do investimento.",
                    new InvestimentosEntity("01", TiposInvestimentos.RENDAFIXA)),
            new CategoriaInvestimentoEntity("02",
                    SubcategoriaInvestimento.valueOf("CERTIFICADO_RECEBIVEIS_AGRONEGOCIO"),
                    "CRA",
                    "Os Certificados de Recebíveis do Agronegócio estão vinculados a direitos creditórios originários de negócios realizados, em sua maioria, por produtores rurais ou suas cooperativas, relacionados ao financiamento da atividade agropecuária.",
                    new InvestimentosEntity("02", TiposInvestimentos.RENDAFIXA)),
            new CategoriaInvestimentoEntity("03",
                    SubcategoriaInvestimento.valueOf("CERTIFICADO_RECEBIVEIS_IMOBILIARIOS"),
                    "CRI",
                    "Os Certificados de Recebíveis Imobiliários são títulos lastreados em créditos imobiliários, representativos de parcelas de um direito creditório.",
                    new InvestimentosEntity("03", TiposInvestimentos.RENDAFIXA)),
            new CategoriaInvestimentoEntity("04",
                    SubcategoriaInvestimento.valueOf("DEBENTURES"),
                    "Debêntures",
                    "As debêntures são valores mobiliários que representam dívidas de médio e longo prazos de Sociedades Anônimas (emissoras)",
                    new InvestimentosEntity("04", TiposInvestimentos.RENDAFIXA))
    );

    public List<CategoriaInvestimentoEntity> findAll(){
        return categoriaInvestimento;
    }

   public List<InvestimentosEntity> findInvestimentos(){
        return categoriaInvestimento.stream()
                .map(CategoriaInvestimentoEntity::getInvestimentosEntity)
                .collect(Collectors.toList());
    }

    public List<CategoriaInvestimentoEntity> create(CategoriaInvestimentoEntity categoriaInvestimentoEntity){
        List<CategoriaInvestimentoEntity> newCategoriaInvestimentoEntities = Arrays.asList(categoriaInvestimentoEntity);

        Stream<CategoriaInvestimentoEntity> combinedStream = Stream.of(categoriaInvestimento, newCategoriaInvestimentoEntities)
                .flatMap(Collection::stream);

        Collection<CategoriaInvestimentoEntity> collectionCombined =
                combinedStream.collect(Collectors.toList());

        categoriaInvestimento = Lists.newArrayList(collectionCombined);

        return categoriaInvestimento;
    }
}
