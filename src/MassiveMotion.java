import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import javax.swing.*;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;

    // Properties of the simulation
    protected int timer_delay;
    protected String listType;
    protected int window_size_x;
    protected int window_size_y;
    protected int star_position_x;
    protected int star_position_y;
    protected int star_size;
    protected int star_velocity_x;
    protected int star_velocity_y;
    protected double gen_x;
    protected double gen_y;
    protected int body_size;
    protected int body_velocity;
    protected List<Body> bodies;
    protected Random random;


    public MassiveMotion(String propfile) {
        // Following code taken from Kode Java and modified
        Properties prop = new Properties();
        try {
            // Getting the onfiguration file name
            String fileName = propfile;
            ClassLoader classLoader = MassiveMotion.class.getClassLoader();

            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                "Can't find configuration file " + propfile);

            InputStream is = new FileInputStream(res.getFile());

            // Load the properties file
            prop.load(is);

            // Getting the values from the config file, using default values if not something is not found
            this.timer_delay = (int) Integer.valueOf(prop.getProperty("timer_delay", "75"));
            this.window_size_x = (int) Integer.valueOf(prop.getProperty("window_size_x", "1024"));
            this.window_size_y = (int) Integer.valueOf(prop.getProperty("window_size_y", "768"));
            this.star_position_x = (int) Integer.valueOf(prop.getProperty("star_position_x", "512"));
            this.star_position_y = (int) Integer.valueOf(prop.getProperty("star_position_y", "384"));
            this.star_size = (int) Integer.valueOf(prop.getProperty("star_size", "30"));
            this.star_velocity_x = (int) Integer.valueOf(prop.getProperty("star_velocity_x", "0"));
            this.star_velocity_y = (int) Integer.valueOf(prop.getProperty("star_velocity_y", "0"));
            this.gen_x = (double) Double.valueOf(prop.getProperty("gen_x", "0.06"));
            this.gen_y = (double) Double.valueOf(prop.getProperty("gen_y", "0.06"));
            this.body_size = (int) Integer.valueOf(prop.getProperty("body_size", "10"));
            this.body_velocity = (int) Integer.valueOf(prop.getProperty("body_velocity", "3"));
            this.random = new Random();

            // Create a list to store bodies, with the list type being specified by the config file
            this.listType = prop.getProperty("list", "arraylist");
            switch (this.listType) {
                case "arraylist":
                    this.bodies = new ArrayList<Body>();
                    break;
                case "single":
                    this.bodies = new LinkedList<Body>();
                    break;
                case "double":
                    this.bodies = new DoublyLinkedList<Body>();
                    break;
                case "dummyhead":
                    this.bodies = new DummyHeadLinkedList<Body>();
                    break;
                default:
                    throw new AssertionError();
            }


            // Create the star and add it to the list
            Body star = new Body(this.star_position_x, this.star_position_y, this.star_velocity_x, this.star_velocity_y, this.star_size, Color.RED);
            this.bodies.add(star);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tm = new Timer(this.timer_delay, this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // Paint each body in the list
        try {
            for (int i = 0; i < this.bodies.size(); i++) {
                Body tempBody = this.bodies.get(i);
                g.setColor(tempBody.color);
                g.fillOval(tempBody.x, tempBody.y, tempBody.size * 2, tempBody.size * 2);
            }
        } catch (Exception e) {
            System.err.println("Error while accessing bodies to paint");
            System.exit(0);
        }

        // Recommend you leave the next line as is
        tm.start();
    }

    /** generate method creates a body on the X or Y axis depending on
    * the given input.
    * @param char for 'X' or 'Y' to decide if the generated body starts on the X or Y axis
    * @return none
    */
    public void generate(char input) {

        int newBodyX;
        int newBodyY;
        if(input == 'X') {
            if (this.random.nextInt(2) == 0) {
                newBodyY = 0;
            } else {
                newBodyY = this.window_size_y;
            }
            newBodyX = this.random.nextInt(this.window_size_x + 1);
        } else {
            if (this.random.nextInt(2) == 0) {
                newBodyX = 0;
            } else {
                newBodyX = this.window_size_x;
            }
            newBodyY = this.random.nextInt(this.window_size_y + 1);            
        }

        // Making sure the velocities can't be 0 with the while loops
        int newBodyXVelocity = 0;
        int newBodyYVelocity = 0;
        while(newBodyXVelocity == 0) {
            int xRange = (this.body_velocity * 2) + 1;
            int xTempVelocity = (this.random.nextInt(xRange));
            xTempVelocity -= this.body_velocity;
            newBodyXVelocity = xTempVelocity;
        }
        while(newBodyYVelocity == 0) {
            int yRange = (this.body_velocity * 2) + 1;
            int yTempVelocity = (this.random.nextInt(yRange));
            yTempVelocity -= this.body_velocity;
            newBodyYVelocity = yTempVelocity;
        }
        
        Body newBody = new Body(newBodyX, newBodyY, newBodyXVelocity, newBodyYVelocity, this.body_size, Color.BLACK);
        this.bodies.add(newBody);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Randomly generates new bodies based on a chance given
        if (this.random.nextInt(100) < (this.gen_x * 100)) {
            generate('X');
        }

        if (this.random.nextInt(100) < (this.gen_y * 100)) {
            generate('Y');
        }

        // Go through list of bodies, moving each one, removing the body if it moves out of bounds;
        try {
            for (int i = (this.bodies.size() - 1); i >= 0; i--) {
                Body tempBody = this.bodies.get(i);
                tempBody.move();
                if (tempBody.x < 0 || tempBody.x > this.window_size_x || tempBody.y < 0 || tempBody.y > this.window_size_y) {
                    this.bodies.remove(i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while moving/removing bodies");
            System.exit(0);
        }

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.window_size_x, mm.window_size_y);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
