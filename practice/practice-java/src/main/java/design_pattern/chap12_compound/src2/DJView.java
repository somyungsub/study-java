package design_pattern.chap12_compound.src2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DJView implements ActionListener, BeatObserver, BPMObserver {
  BeatModelInterface model;
  ControllerInterface controller;

  JFrame viewFrame;
  JPanel viewPanel;

  BeatBar beatBar;
  JLabel bpmOutputLabel;

  public DJView(ControllerInterface controller, BeatModelInterface model) {
    this.model = model;
    this.controller = controller;
    model.registerObserver((BeatObserver)this);
    model.registerObserver((BPMObserver)this);
  }

  public void createView() {
    // 스윙
  }


  public void updateBPM() {
    int bpm = model.getBPM();
    if (bpm == 0) {
      bpmOutputLabel.setText("offline");
      return;
    }
    bpmOutputLabel.setText("Current BPM: " + model.getBPM());
  }

  public void updateBeat() {
    beatBar.setValue(100);
  }
  @Override
  public void actionPerformed(ActionEvent e) {

  }


}
