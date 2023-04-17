package numbers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class GraphQLHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    private static final GraphQL GRAPHQL;

    static {
        InputStream schemaInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("schema.graphqls");
        InputStreamReader schemaReader = new InputStreamReader(schemaInputStream);
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaReader);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("arabicToRoman", new ArabicToRomanDataFetcher())
                        .dataFetcher("romanToArabic", new RomanToArabicDataFetcher()))
                .build();

        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        GRAPHQL = GraphQL.newGraphQL(schema).build();
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context) {
        System.out.println(event);
        String requestBody = event.getBody();
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(requestBody)
                .build();

//        GRAPHQL.execute(ExecutionInput.newExecutionInput().query("{arabicToRoman(arabic: 42)}").build())
        ExecutionResult executionResult = GRAPHQL.execute(executionInput);
        Map<String, Object> response = executionResult.toSpecification();

        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(200)
                .withHeaders(Map.of("Content-Type", "application/json"))
                .withBody(response.toString())
                .build();
    }
}
