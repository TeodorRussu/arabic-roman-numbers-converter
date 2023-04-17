// RomanToArabicDataFetcher.java
package numbers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class RomanToArabicDataFetcher implements DataFetcher<Integer> {

    private final RomanToArabicConverter converter = new RomanToArabicConverter();

    @Override
    public Integer get(DataFetchingEnvironment environment) {
        String roman = environment.getArgument("roman");
        return converter.convert(roman);
    }
}
