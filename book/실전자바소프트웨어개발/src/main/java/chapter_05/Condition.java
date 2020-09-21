package chapter_05;

@FunctionalInterface
public interface Condition {
  boolean evaluate(Facts facts);
}