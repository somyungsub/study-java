package design_pattern.chap12_compound.src2;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import java.util.ArrayList;
import java.util.List;

public class BeatModel implements BeatModelInterface, MetaEventListener {

  Sequencer sequencer;

  List<BeatObserver> beatObservers = new ArrayList<>();

  List<BPMObserver> bpmObservers = new ArrayList<>();

  int bpm = 90;

  @Override
  public void initialize() {
//    setUpMidi();
//    buildTrackAndStart();
  }

  @Override
  public void on() {
    sequencer.start();
    setBPM(90);
  }

  @Override
  public void off() {
    setBPM(0);
    sequencer.stop();
  }

  @Override
  public void setBPM(int bpm) {
    this.bpm = bpm;
    sequencer.setTempoInBPM(getBPM());
//    notifyBPMObservers();
  }

  @Override
  public int getBPM() {
    return bpm;
  }

  @Override
  public void registerObserver(BeatObserver observer) {
    beatObservers.add(observer);
  }

  @Override
  public void removeObserver(BeatObserver observer) {
    beatObservers.remove(observer);
  }

  @Override
  public void registerObserver(BPMObserver observer) {
    bpmObservers.add(observer);
  }

  @Override
  public void removeObserver(BPMObserver observer) {
    bpmObservers.remove(observer);
  }

  @Override
  public void meta(MetaMessage meta) {

  }

  void beatEvent() {
//    notifyBeatObservers();
  }
}
