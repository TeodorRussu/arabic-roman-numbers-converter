// ArabicToRomanDataFetcher.java
package numbers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class ArabicToRomanDataFetcher implements DataFetcher<String> {

    private final ArabicToRomanConverter converter = new ArabicToRomanConverter();

    @Override
    public String get(DataFetchingEnvironment environment) {
        Integer arabic = environment.getArgument("arabic");
        return converter.convert(arabic);
    }
}
