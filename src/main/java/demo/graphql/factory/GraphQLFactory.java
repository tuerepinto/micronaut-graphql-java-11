package demo.graphql.factory;

import demo.graphql.data.GraphQLDataFetchers;
import demo.graphql.mutation.MutationResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Factory
public class GraphQLFactory {
    private static final Logger LOG = LoggerFactory.getLogger(GraphQLFactory.class);

    @Bean
    @Singleton
    public GraphQL graphQL(ResourceResolver resourceResolver,
                           GraphQLDataFetchers graphQLDataFetchers,
                           MutationResolver mutationResolver) {
        SchemaParser schemaParser = new SchemaParser();

        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        Optional<InputStream> graphqlSchema = resourceResolver.getResourceAsStream("classpath:schema.graphqls");

        if (graphqlSchema.isPresent()) {
            typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(graphqlSchema.get()))));

            // TODO.: Ponto de atenção.
            // Schema vs Classe
            RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                    .type(newTypeWiring("Query")
                            .dataFetcher("categoriaInvestimentoBySigla", graphQLDataFetchers.getCategoriaInvestimentoBySigla()))
                    .type(newTypeWiring("CategoriaInvestimentoEntity")
                            .dataFetcher("investimentosEntity", graphQLDataFetchers.getInvestimento()))
                    .type(newTypeWiring("Mutation")
                            .dataFetcher("create", mutationResolver.create()))
                    .build();

            SchemaGenerator schemaGenerator = new SchemaGenerator();

            GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

            return GraphQL.newGraphQL(graphQLSchema).build();

        } else {
            LOG.debug("No GraphQL services found, returning empty schema");
            return new GraphQL.Builder(GraphQLSchema.newSchema().build()).build();
        }
    }
}
