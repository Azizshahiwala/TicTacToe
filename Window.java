import java.awt.*;
import javax.swing.*;

//Window.class will only create the UI.

public class Window extends Thread{
    protected ImageIcon icon;
    protected JLabel Gameheading, Announcer;
    protected JButton[][] ButtonGameInputs;
    protected JFrame frame;
    protected JButton ResetBoard, Rematch;
    public static char[][] CharGameInputs;
    protected JPanel Button_panel, GamematchOptions;
    protected Events eventHandler;
    public static TicTacToe tictactoe;
    Window(){
        frame = new JFrame(); // a frame / top most window

        ButtonGameInputs = new JButton[3][3]; // group of buttons (9)
        CharGameInputs = new char[3][3]; // mapping of chars
        
        ResetBoard = new JButton();
        Rematch = new JButton();

        Announcer = new JLabel();
        
        Button_panel = new JPanel(); // A section inside a frame.
       
        GamematchOptions = new JPanel();
        icon = new ImageIcon(getClass().getResource("/Logo.png"));
    }
    
    public void run(){
        Image image = icon.getImage();
        // Set the icon of the frame
        frame.setIconImage(image);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().setBackground(new Color(95,76,76));
        frame.setForeground(Color.BLACK);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("TicTacToe ~Aziz Shahiwala");
        
    Gameheading = new JLabel("Tic Tac Toe");
    Gameheading.setBounds(160, 20, 500, 50);
    Gameheading.setFont(new Font("Lucida Typewriter", Font.BOLD, 50));
    Gameheading.setForeground(new Color(197,170,106));
    frame.add(Gameheading);   
        CreateCharMapping();  
        tictactoe = new TicTacToe(); 
        Rematch.setVisible(true);  
        Rematch.setEnabled(false); 
    }
    
    protected void CreateButtons(Events game){    
        // Creating 3 x 3 grid of buttons.
        Button_panel.setLayout(new GridLayout(3, 3)); // We need setLayout for Jpanel button.
        Button_panel.setBounds(50,100,450,450);
        Font font1 = new Font("Arial",Font.PLAIN,30);

        //Add two more buttons below the grid: re-match and reset
        ResetBoard.setText("Reset game");
        Rematch.setText("Rematch");

        ResetBoard.setFont(font1);
        Rematch.setFont(font1);
        
        ResetBoard.setActionCommand("Reset board");
        Rematch.setActionCommand("Rematch");

        ResetBoard.addActionListener(game);
        Rematch.addActionListener(game);
        
        GamematchOptions.setLayout(new GridLayout(1,2));
        GamematchOptions.setBounds(50, 700, 450, 70);

        GamematchOptions.add(ResetBoard);
        GamematchOptions.add(Rematch);
        
    // Simple Announcer JLabel
    Announcer.setFont(new Font("Arial", Font.BOLD, 30));
    Announcer.setHorizontalAlignment(SwingConstants.CENTER);
    Announcer.setBounds(50, 630, 450, 70);
    Announcer.setForeground(Color.WHITE);
    Announcer.setBackground(Color.BLACK);
    Announcer.setOpaque(true);
    

    // Add components to frame
    frame.add(Button_panel);
    frame.add(GamematchOptions);
    frame.add(Announcer);
     }
    public void CreateCharMapping(){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++)
               CharGameInputs[r][c] = ' ';
        }
    }  
    public static void main(String[] args) {
    Events game = new Events();  
    game.start();
    game.CreateButtons(game);
    for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                game.ButtonGameInputs[r][c] = new JButton();
                game.ButtonGameInputs[r][c].setFont(new Font("Arial",Font.PLAIN,50));     
                game.ButtonGameInputs[r][c].setActionCommand(r+","+c);
                game.ButtonGameInputs[r][c].addActionListener(game);
                game.Button_panel.add(game.ButtonGameInputs[r][c]);
            }
        }

        //Add a menu on right side. 
        Menu menu = new Menu(game);
        game.frame.add(menu,BorderLayout.EAST);
        game.frame.setVisible(true);
        game.frame.revalidate();
        game.resetGrid();
    }
}
