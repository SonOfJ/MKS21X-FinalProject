import javax.swing.JFrame;
public class Display {
  private JFrame frame;
  private String name;
  private int height;
  private int width;
  public Display(String newName, int newHeight, int newWidth) {
    name = newName;
    height = newHeight;
    width = newWidth;
    create();
  }
  private void create() { //Makes the actual display.
    frame = new JFrame(name);
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Shuts down game when terminal closes.
    frame.setResizable(false); //No reason for the user to resize the window.
  }
}
