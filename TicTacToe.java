
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
public class TicTacToe extends Window{
    public static String BoardStatus = "";
    public static String status = "";
    public int P1_match=0;
    public int P2_match=0;
    static Character Player1 = 'X';
    static Character Player2 = 'O';
    static Character CurrentPlayer;
    int player1_score = 0;
    int Player2_score = 0;
    double roll;
    public int CharArrayRow;
    public int CharArrayCol;

    int min=1, max=2;
    TicTacToe()
    {    
        roll = min + (Math.random() * (max - min + 1)); 
        roll = Math.floor(roll);

        if (roll == 1) CurrentPlayer = Player2;
        else CurrentPlayer = Player1;
    }  
    protected void switchTurn(JLabel Announcer){
       
        if(CurrentPlayer == Player1)
        CurrentPlayer = Player2;
        
        else 
        CurrentPlayer = Player1; 

        Announcer.setText("Player: "+CurrentPlayer+" turn.");
    }
    public boolean WinnerFound(Character CurrentPlayer, JLabel Announcer){
        String winner = "Player "+CurrentPlayer+" wins!";
        Announcer.setText(winner);
        Rematch.setEnabled(true);
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++)
            if(CharGameInputs[i][j] == ' ') // ' ' -> '_'
            Window.CharGameInputs[i][j] = '_';
        }
        status = "Status: "+winner;
        SaveBoardStatus(CharGameInputs);
        Menu.saveFile.setEnabled(true);
        
    return true;
    }
    protected static void SaveBoardStatus(char CharGameInputs[][]){
        BoardStatus = "";
        for(int r = 0; r<3; r++)
        {
            for(int c = 0; c< 3; c++)
            {
                if(CharGameInputs[r][c] == ' ' || CharGameInputs[r][c] == '\0')
                CharGameInputs[r][c] = '_';
            
                BoardStatus += CharGameInputs[r][c];
            }
            BoardStatus += " ";
        }  
        BoardStatus+=status;  
        LocalDateTime currenttime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");       
        String time = currenttime.format(format);
        BoardStatus+= " Time recorded: "+time;
    }
    public void DrawGame(Events handler, JLabel Announcer) {
    if (!handler.isMatchfound(CurrentPlayer) && handler.isGridFull()) {
        Announcer.setText("DRAW!");
        handler.disableAllButtons();
        Rematch.setEnabled(true);
        Menu.confirm.setEnabled(true);
        Menu.saveFile.setEnabled(true);
        status = "Status: DRAW!";
    }
}  
}
