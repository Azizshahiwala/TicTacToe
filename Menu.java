import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{
    boolean isP1empty=true,isP2empty=true,conflict=false;
    static char P1_char,P2_char;
    String currentTime= "";
    private Events game;
    static JButton confirm,exit,saveFile;
    JTextField PlayerSymbol1 , PlayerSymbol2;
    JLabel newP1info, newP2info, headingForPlayerInfo, Exit_Save_Label, Menu_heading, filesaved;
    Font LabelFont,ButtonFont;
    Menu(Events game){
        this.game = game;

        LabelFont = new Font("Verdana",Font.BOLD,17);
        ButtonFont = new Font("Tahoma",Font.BOLD,15);

        confirm = new JButton("Use these symbols");
        exit = new JButton("Exit");
        saveFile = new JButton("Save current game");

        PlayerSymbol1 = new JTextField();
        PlayerSymbol2 = new JTextField();

        newP1info = new JLabel("For player 1: ");
        newP2info = new JLabel("For player 2: ");
        headingForPlayerInfo = new JLabel("Add your own symbol to play!");
        Exit_Save_Label = new JLabel("Exit and Save Options:");
        Menu_heading = new JLabel("Menu");
        filesaved = new JLabel();
        setBounds(550,100,400,450);
        setVisible(true);

        setOpaque(true);
        setBackground(new Color(197,170,106));
        setLayout(null);

        Menu_heading.setBounds(160, 15, 112,40);// Relative to Menu's (0,0)
        add(Menu_heading);

        Exit_Save_Label.setBounds(90, 260, 300,40);
        add(Exit_Save_Label);

        headingForPlayerInfo.setBounds(60, 75, 320,40);
        add(headingForPlayerInfo);

        PlayerSymbol1.setBounds(150, 130, 30,20);
        add(PlayerSymbol1);

        PlayerSymbol2.setBounds(350, 130, 30,20);
        add(PlayerSymbol2);

        newP1info.setBounds(20, 130, 200,20);
        add(newP1info);

        newP2info.setBounds(220, 130, 200,20);
        add(newP2info);

        confirm.setBounds(100, 170, 200,40);
        add(confirm);

        saveFile.setBounds(200, 330, 180,50);
        add(saveFile);

        filesaved.setBounds(50, 370, 280,50);
        add(filesaved);

        exit.setBounds(10, 330, 180,50);
        add(exit);

        Exit_Save_Label.setFont(LabelFont);
        headingForPlayerInfo.setFont(LabelFont);
        Menu_heading.setFont(new Font("Segoe UI",Font.BOLD,40));
        
        newP1info.setFont(LabelFont);
        newP2info.setFont(LabelFont);
        
        filesaved.setFont(new Font("Segoe UI",Font.BOLD,18));
        confirm.setFont(ButtonFont);
        saveFile.setFont(ButtonFont);
        exit.setFont(ButtonFont);

        setOpaque(true);
        setExit();
        setSymbolcontrolText();
        setSaveFile();
        game.frame.repaint();
        game.frame.revalidate();
    } 
    private void setExit() {
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.exitButton();  // Call method from Events class
        }
    });
}
private void showTemporaryMessage(String message, Color color, int durationMillis, int x) {
    //To show a warning msg, we use a thread to sleep and then wake up.
    //set color red for warning msg.
    filesaved.setForeground(color);
    filesaved.setText(message);

    //Sleep
    new Thread(() -> {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    //set color black to reset.
        filesaved.setForeground(Color.GREEN);
        filesaved.setText("");
    }).start();
}
private void showTemporaryMessage(String message, Color color, int durationMillis) {
    //To show a warning msg, we use a thread to sleep and then wake up.
    //set color red for warning msg.
    headingForPlayerInfo.setForeground(color);
    headingForPlayerInfo.setText(message);

    //Sleep
    new Thread(() -> {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    //set color black to reset.
        headingForPlayerInfo.setForeground(Color.BLACK);
        headingForPlayerInfo.setBounds(60, 75, 320,40);
        headingForPlayerInfo.setText("Add your own symbol to play!");
    }).start();
}
    private void setSymbolcontrolText(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    CheckForText();
                    
                }catch(ErrorMsg err){              
                    showTemporaryMessage(err.getMessage(),Color.RED,3000);
                }     
            }
        });
    }
    private void setSaveFile(){
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent click){
                try{
                    TicTacToe.SaveBoardStatus(Window.CharGameInputs);
                    //this const will be used to send String to a python file.
                    ProcessBuilder builder = new ProcessBuilder("python","savefile.py",TicTacToe.BoardStatus);
                    Process process = builder.start();
                    showTemporaryMessage("File saved to your current path.", Color.GREEN, 3000,1);
                }
                catch(Exception err){
                    System.err.println(err);
                }
            }
        });
        Menu.saveFile.setEnabled(false);
    }
    private boolean isTextavailable(){
        String P1_str = PlayerSymbol1.getText();
        String P2_str = PlayerSymbol2.getText();

        isP1empty = P1_str.isEmpty();
        isP2empty = P2_str.isEmpty();

        if(isP1empty || isP2empty) 
        return false;

        P1_char = P1_str.charAt(0);
        P2_char = P2_str.charAt(0);

        if((P1_char == ' ' || P1_char == '\0') && (P2_char == ' ' || P2_char == '\0'))
        return false;
        
        else{
            isP1empty = false;
            isP2empty = false;
        }
        return true;

    }
    private void CheckForText() throws ErrorMsg{
        
        if(isTextavailable() && P1_char != P2_char){
                game.resetGrid(P1_char,P2_char); 
                TicTacToe.Player1 = P1_char;
                TicTacToe.Player2 = P2_char;
                confirm.setEnabled(false);
                game.Rematch.setEnabled(false);
               }
               else if((P1_char == ' ' || P1_char == '\0') && (P2_char == ' ' || P2_char == '\0')){
                headingForPlayerInfo.setBounds(145, 75, 320,40);
                throw new ErrorMsg("Empty fields");
               }
               else{
                throw new ErrorMsg("Same characters are not allowed!");
               }
    }
}