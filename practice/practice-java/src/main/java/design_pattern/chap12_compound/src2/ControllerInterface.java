package design_pattern.chap12_compound.src2;

public interface ControllerInterface {
  void start();   // 연주 시작

  void stop();    // 연주 중단

  void increaseBPM();     // BPM 증가(빠르게 연주하기)

  void decreaseBPM();     // BPM 감소(느리게 연주하기)

  void setBPM(int bpm);   // 분당 비트수 지정

}
