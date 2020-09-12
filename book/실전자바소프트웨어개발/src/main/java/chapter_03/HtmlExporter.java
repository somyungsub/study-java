package chapter_03;

public class HtmlExporter implements Exporter {

  @Override
  public String export(final SummaryStatistics summaryStatistics) {
    return "<!doctype html>" +
      "<html lang='en'>" +
      "<head><title>Bank Transaction Report</title></head>" +
      "<body>" +
      "<ul>" +
      "<li><strong>The sum is</strong>:" + summaryStatistics.getSum() + "</li>" +
      "<li><strong>The average is</strong>:" + summaryStatistics.getAverage() + "</li>" +
      "<li><strong>The max is</strong>:" + summaryStatistics.getMax() + "</li>" +
      "<li><strong>The min is</strong>:" + summaryStatistics.getMin() + "</li>" +
      "</ul>" +
      "</body>" +
      "</html>";
  }
}
