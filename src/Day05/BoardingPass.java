package Day05;

public class BoardingPass {
  protected String instructions;
  protected char[] rowCommands;
  protected char[] colCommands;
  int row;
  int col;
  int seatId;


  public BoardingPass(String instructions) {
    this.instructions = instructions;
    this.parse();
    this.process();
  }

  public int getSeatId() {
    return this.seatId;
  }

  private void parse() {
    this.rowCommands = instructions.substring(0, 7).toCharArray();
    this.colCommands = instructions.substring(7).toCharArray();
  }

  private void process() {
    row = getRow();
    col = getCol();
    seatId = (row * 8) + col;
  }

  private int getRow() {
    return binarySearch(rowCommands, 128, 'F');
  }

  private int getCol() {
    return binarySearch(colCommands, 8, 'L');
  }

  private int binarySearch(char[] commands, int numEntities, char lowerId) {
    int lo = 0;
    int hi = numEntities - 1;
    int mid;
    for (int i = 0; i < commands.length; i++) {
      mid = ((hi - lo) / 2) + lo;
      char instruction = commands[i];
      if (instruction == lowerId) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    return lo;
  }
}
