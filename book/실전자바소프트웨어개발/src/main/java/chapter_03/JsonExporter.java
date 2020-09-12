package chapter_03;

public class JsonExporter implements Exporter {

  private static final String CL_RF = System.lineSeparator();

  @Override
  public String export(SummaryStatistics summaryStatistics) {
    StringBuilder sb = new StringBuilder();
    sb.append("{").append(CL_RF)
      .append("\"sum\" : ").append(summaryStatistics.getSum()).append(CL_RF)
      .append("\"avg\" : ").append(summaryStatistics.getAverage()).append(CL_RF)
      .append("\"max\" : ").append(summaryStatistics.getMax()).append(CL_RF)
      .append("\"min\" : ").append(summaryStatistics.getMin()).append(CL_RF)
      .append("}");

    return sb.toString();
  }
}
