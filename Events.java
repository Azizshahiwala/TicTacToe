import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Events extends TicTacToe implements ActionListener{
    public String buttonText = "";
    private JButton[][] GameInput;
    String statement;
    //I will pass 2d button array to this constructor.
    Events(){
        super();
        GameInput = ButtonGameInputs;
    }
    @Override 
    public void actionPerformed(ActionEvent click){   
    JButton buttonClicked = (JButton)click.getSource();
    String command = click.getActionCommand();
 
    if (command.equals("Reset board")) {
        resetGrid('X','O');
        Rematch.setEnabled(false);
        return;
    }
    if (command.equals("Rematch") && (isGridFull() || isMatchfound(CurrentPlayer))) {
        rematch();
        return;
    }

    if(buttonClicked.getText().equals(""))
        {
    SetButtonOutput(click);

        if (isMatchfound(CurrentPlayer))
        {
            WinnerFound(CurrentPlayer,Announcer); 
            Rematch.setEnabled(true);
            disableAllButtons();
            return;
        }

        if (isGridFull()){
            DrawGame(this,this.Announcer);
            return;
        } 
        
        switchTurn(Announcer);
        }
    }
    public void rematch(){
        for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            GameInput[r][c].setEnabled(true);
            GameInput[r][c].setText("");
            CharGameInputs[r][c] = ' ';
        }
    }
        min = 1;
        max = 2;

        double roll = min + (Math.random() * (max - min + 1)); 
        roll = Math.floor(roll);

        if (roll == 1) CurrentPlayer = Player2;
        else CurrentPlayer = Player1;

        statement = "Start: "+CurrentPlayer;
        Announcer.setText(statement);
        Announcer.repaint();
        Announcer.revalidate();
        
        frame.repaint();
        Rematch.setEnabled(false);
        Menu.saveFile.setEnabled(false);
        return;
}
    public void resetGrid(char P1_char, char P2_char){
    min = 1;
    max = 2;

    Player1 = P1_char;
    Player2 = P2_char;

    double roll = min + (Math.random() * (max - min + 1)); 
    roll = Math.floor(roll);

    if (roll == 1) CurrentPlayer = Player2;
    else CurrentPlayer = Player1;

    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            GameInput[r][c].setEnabled(true);
            GameInput[r][c].setText("");
            CharGameInputs[r][c] = ' ';
        }
    }
    Menu.saveFile.setEnabled(false);
    Menu.confirm.setEnabled(true);
    Announcer.setText("Start: " + CurrentPlayer);
    Announcer.repaint();
    Announcer.revalidate();
    frame.revalidate();
    frame.repaint();
}
public void resetGrid(){
    min = 1;
    max = 2;

    double roll = min + (Math.random() * (max - min + 1)); 
    roll = Math.floor(roll);

    if (roll == 1) CurrentPlayer = Player2;
    else CurrentPlayer = Player1;

    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            GameInput[r][c].setEnabled(true);
            GameInput[r][c].setText("");
            CharGameInputs[r][c] = ' ';
        }
    }
    Menu.saveFile.setEnabled(false);
    Menu.confirm.setEnabled(true);
    Announcer.setText("Start: " + CurrentPlayer);
    Announcer.repaint();
    Announcer.revalidate();
    frame.revalidate();
    frame.repaint();
}
    public void exitButton(){
     System.exit(0);   
    }
    private void SetButtonOutput(ActionEvent click){
        buttonText = Character.toString(CurrentPlayer);
        JButton buttonClicked = (JButton)click.getSource();

        buttonClicked.setText(buttonText);   
        MapCharButton(buttonClicked.getActionCommand());    
    }
    private void MapCharButton(String CharInfo){
        //To get row and col from Button object, Pass a string using getActionCommand.
        //get character from index 0 and 2 because 1 is ','
        //Convert char row and col into int using Character.getNumericValue
        //Store the results into chargameinput.
        int row = Character.getNumericValue(CharInfo.charAt(0));
        int col = Character.getNumericValue(CharInfo.charAt(2));
        Window.CharGameInputs[row][col] = CurrentPlayer;

        //Backup fetched row and col
        CharArrayRow = row;
        CharArrayCol = col;
    }
    protected boolean Player1Match(){
        if(CurrentPlayer == Player1){
          //Search for Left to Right
          for(int r=0; r<3; r++){
            P1_match=0;
            for(int c=0; c<3; c++){
                if(CharGameInputs[r][c] == Player1)
                    P1_match++;
            }
            if(P1_match == 3){
            return true;
            }
          }

          //Search from Top to bottom
          for(int c=0; c<3; c++){
            P1_match=0;
            for(int r=0; r<3; r++){
                if(CharGameInputs[r][c] == Player1)
                    P1_match++;
            }
            if(P1_match == 3){
            return true;
            }
          }

          //Search from Top left to bottom Right - \
          P1_match=0;
          for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(1*c-r == 0 && CharGameInputs[r][c] == Player1)
                    P1_match++;

                if(P1_match == 3){
                return true;
                }
            }       
          }
          //Search from Bottom left to Top r Right check! - /
          P1_match=0;

          for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(c+r == 2 && CharGameInputs[r][c] == Player1)
                    P1_match++;

                if(P1_match == 3){
                return true;
                }
            }       
          }
          P1_match = 0;
        }
        return false;
    }
    protected boolean Player2Match(){
        if(CurrentPlayer == Player2){
          //Search for Left to Right
          for(int r=0; r<3; r++){
            P2_match=0;
            for(int c=0; c<3; c++){
                if(CharGameInputs[r][c] == Player2)
                    P2_match++;
            }
            if(P2_match == 3){
            return true;
            }
          }

          //Search from Top to bottom
          for(int c=0; c<3; c++){
            P2_match=0;
            for(int r=0; r<3; r++){
                if(CharGameInputs[r][c] == Player2)
                    P2_match++;
            }
            if(P2_match == 3){
            return true;
            }
          }

          //Search from Top left to bottom Right - \
          P2_match=0;
          for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(1*c-r == 0 && CharGameInputs[r][c] == Player2)
                    P2_match++;

                if(P2_match == 3){
                return true;
                }
            }       
          }
          //Search from Bottom left to Top r Right check! - /
          P2_match=0;

          for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(c+r == 2 && CharGameInputs[r][c] == Player2)
                    P2_match++;

                if(P2_match == 3){
                return true;
                }
            }       
          }
          P2_match = 0;
        }
        return false;
    }
    public void disableAllButtons() {
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            GameInput[r][c].setEnabled(false);
        }
    }
}
    public boolean isMatchfound(Character CurrentPlayer){

        if(CurrentPlayer == Player1 && Player1Match()){
            return true;
         //Check each Combination after clicking by player 1 
        }
        else if(CurrentPlayer == Player2 && Player2Match()){
            return true;
        //Check each Combination after clicking by player 2
        }

        //else: stalemate if no combination exists!
        return false;
    }
    public boolean isGridFull() {
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            if (CharGameInputs[r][c] == ' ' || CharGameInputs[r][c] == '\0') {
                return false;  // Empty space found
            }
        }
    }
    return true;  // No empty spaces, grid is full
    }
    
}
