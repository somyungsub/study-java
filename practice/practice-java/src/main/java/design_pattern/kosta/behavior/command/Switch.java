package design_pattern.kosta.behavior.command;

/*the Invoker class*/
public class Switch {
  private Command flipUpCmd;
  private Command flipDownCmd;

  public Switch(Command flipUpCmd, Command flipDownCmd) {
    this.flipUpCmd = flipUpCmd;
    this.flipDownCmd = flipDownCmd;
  }


  public void flipUp() {
    flipUpCmd.execute();
  }

  public void flipDown() {
    flipDownCmd.execute();
  }
}
