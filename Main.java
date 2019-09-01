import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) {

        String test = "ala ma kota, kot koduje w Javie kota";

        mapPrettyPrint(letterOccurrenceInWords(test));
    }

    private static Map<String, List<String>> letterOccurrenceInWords(String inputString){
        return inputString.chars()
                .boxed()
                .map(s -> String.valueOf((char) (int) s).toLowerCase())
                .distinct()
                .filter(Pattern.compile("[a-z]").asPredicate())
                .collect(toMap(s -> s,
                        s -> Pattern.compile("\\w*" + s + "+\\w*")
                                .matcher(inputString.toLowerCase())
                                .results()
                                .map(MatchResult::group)
                                .collect(toList()))
                );
    }

    private static void mapPrettyPrint(Map<String, List<String>> mapToPrint){
        mapToPrint.forEach((k,v) -> {
            System.out.print(k + ": ");
            v.stream().flatMap((s) -> Stream.of(", ", s))
                    .skip(1).forEach(System.out::print);
            System.out.println();
        });
    }
}

