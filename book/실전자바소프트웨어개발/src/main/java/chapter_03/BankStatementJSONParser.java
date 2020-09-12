package chapter_03;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BankStatementJSONParser implements BankStatementParser {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Override
  public BankTransaction parseFrom(String line) {
    // JSON 파싱
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) new JSONParser().parse(line);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    String date1 = jsonObject.get("date").toString();

    LocalDate date = LocalDate.parse(date1, dateTimeFormatter);
    double amount = Double.parseDouble(jsonObject.get("amount").toString());
    String description = (String) jsonObject.get("description");

    return new BankTransaction(date, amount, description);
  }

  @Override
  public List<BankTransaction> parseLinesFrom(List<String> lines) {
    String jsonString = String.join("", lines);
    JSONParser jsonParser = new JSONParser();
    try {
      JSONArray array = (JSONArray)jsonParser.parse(jsonString);
      return (List<BankTransaction>) array.stream()
        .map(o -> parseFrom(o.toString()))
        .collect(toList());

    } catch (ParseException e) {
      e.printStackTrace();
    }

    return Collections.EMPTY_LIST;
  }
}
