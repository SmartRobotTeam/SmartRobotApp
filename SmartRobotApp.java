import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
/**
 * Classe définissant l'interface de la SmartRobotApp
 *
 * @author (votre nom)
 * @version 09/02/2021
 */
public class SmartRobotApp extends JFrame
{
    // variables d'instance
    private JPanel pan = new JPanel();
    // variables pour la barre de menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu help = new JMenu("Help");
    private JMenu options = new JMenu("Options");
    private JMenu tools = new JMenu("Tools");
    private JMenu edits = new JMenu("Edits");
    private JMenu files = new JMenu("Files");
    private JMenu profile = new JMenu("Profile");
    private JMenuItem helpItem = new JMenuItem("Help"); // help
    private JMenuItem about = new JMenuItem("About"); // help
    private JMenuItem font = new JMenuItem("Font color"); // options
    private JMenuItem imageRes = new JMenuItem("Image resolution"); // options
    private JMenuItem size = new JMenuItem("Image size"); // options
    private JMenuItem filter = new JMenuItem("Image filter"); // options
    private JMenuItem minConfidence = new JMenuItem("Min confidence"); // options
    private JMenuItem recObjects = new JMenuItem("Objects recognition"); // tools
    private JMenuItem recFaces = new JMenuItem("Faces recognition"); // tools
    private JMenuItem newObject = new JMenuItem("Add new object"); // edits
    private JMenuItem newFace = new JMenuItem("Add new face"); // edits
    private JMenuItem contribute = new JMenuItem("Contribute"); // help
    private JMenuItem donate = new JMenuItem("Donate"); // help
    private JMenuItem maxConfidence = new JMenuItem("Max confidence"); // options
    private JMenuItem takePhoto = new JMenuItem("Take photo"); // tools
    private JMenuItem startVideo = new JMenuItem("Start video"); // tools
    private JMenuItem endVideo = new JMenuItem("Stop video"); // tools
    private JMenuItem pauseVideo = new JMenuItem("Pause video"); //tools
    private JMenuItem openVideo = new JMenuItem("Open a video"); // files
    private JMenuItem openPhoto = new JMenuItem("Open a photo"); // files
    private JMenuItem modifyPhoto = new JMenuItem("Modify a photo"); // edits
    private JMenuItem modifyVideo = new JMenuItem("Modify a video"); // edits
    private JMenuItem newScene = new JMenuItem("New"); // files
    private JMenuItem connect = new JMenuItem("Connect"); // profile
    private JMenuItem disconnect = new JMenuItem("Disconnect"); // profile
    private JMenuItem info = new JMenuItem("Info"); // profile
    //initialisation des autres variables
    boolean home = true;
    int video = 0; // 0=not taking video ; 1=video paused ; 2=taking video
    int photo = 0; // 0=photo not taken ; 1=photo taken
    /**
     * Constructeur d'objets de classe SmartRobotApp
     */
    public SmartRobotApp()
    {
        // initialisation des variables d'instance
        this.setSize(new Dimension(800,600));
        this.setTitle("SmartRobotApp");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pan.setBackground(Color.white);
        //initialisation des raccourcis
        this.newScene.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        this.openVideo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        this.openPhoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        this.takePhoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,KeyEvent.CTRL_DOWN_MASK));
        this.helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK));
        this.about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        this.contribute.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        this.donate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        this.info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK));
        //initialisation des ActionListener
        this.newScene.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pan.removeAll();
                pan.add(new NewScene());
                home = false;
                pan.revalidate();
            }
        });
        this.connect.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new Connect());
                home = false;
                pan.revalidate();
            }
        });
        this.disconnect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new Disconnect());
                home = false;
                pan.revalidate();
            }
        });
        this.info.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new Info());
                home = false;
                pan.revalidate();
            }
        });
        this.newObject.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new NewObject());
                home = false;
                pan.revalidate();
            }
        });
        this.newFace.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new NewFace());
                home = false;
                pan.revalidate();
            }
        });
        this.modifyPhoto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new ModifyPhoto());
                home = false;
                pan.revalidate();
            }
        });
        this.modifyVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new ModifyVideo());
                home = false;
                pan.revalidate();
            }
        });
        this.recObjects.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new RecObjects());
                home = false;
                pan.revalidate();
            }
        });
        this.recFaces.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                pan.removeAll();
                pan.add(new RecFaces());
                home = false;
                pan.revalidate();
            }
        });
        this.helpItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Help h = new Help();
            }
        });
        this.about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                About ab = new About();
            }
        });
        this.contribute.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Contribute cb = new Contribute();
            }
        });
        this.donate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Donate don = new Donate();
            }
        });
        this.font.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Font ft = new Font();
            }
        });
        this.minConfidence.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                MinConfidence min = new MinConfidence();
            }
        });
        this.maxConfidence.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                MaxConfidence max = new MaxConfidence();
            }
        });
        this.imageRes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                ImageRes imRes = new ImageRes();
            }
        });
        this.size.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Size imSize = new Size();
            }
        });
        this.filter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Filter imFilter = new Filter();
            }
        });
        this.takePhoto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                photo = 1;
            }
        });
        this.openPhoto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                OpenPhoto opPhoto = new OpenPhoto();
            }
        });
        this.openVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                OpenVideo opVideo = new OpenVideo();
            }
        });
        this.startVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                video = 2;
            }
        });
        this.pauseVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                video = 1;
            }
        });
        this.endVideo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                video = 0;
            }
        });
        //initialisation de la barre de menu
        this.help.add(helpItem);
        this.help.add(about);
        this.help.addSeparator();
        this.help.add(contribute);
        this.help.add(donate);
        this.files.add(newScene);
        this.files.add(openPhoto);
        this.files.add(openVideo);
        this.options.add(font);
        this.options.addSeparator();
        this.options.add(minConfidence);
        this.options.add(maxConfidence);
        this.options.addSeparator();
        this.options.add(imageRes);
        this.options.add(size);
        this.options.add(filter);
        this.tools.add(takePhoto);
        this.tools.addSeparator();
        this.tools.add(startVideo);
        this.tools.add(pauseVideo);
        this.tools.add(endVideo);
        this.tools.addSeparator();
        this.tools.add(recObjects);
        this.tools.add(recFaces);
        this.edits.add(modifyPhoto);
        this.edits.add(modifyVideo);
        this.edits.addSeparator();
        this.edits.add(newObject);
        this.edits.add(newFace);
        this.profile.add(connect);
        this.profile.addSeparator();
        this.profile.add(info);
        this.profile.add(disconnect);
        this.menuBar.add(files);
        this.menuBar.add(edits);
        this.menuBar.add(tools);
        this.menuBar.add(options);
        this.menuBar.add(profile);
        this.menuBar.add(help);
        this.setJMenuBar(menuBar);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    /**
     * Création des classes pour l'affichage
     *
     * 
     *
     */
    class NewScene extends JPanel{
        public NewScene(){
        }
    }
    class Connect extends JPanel{
        public Connect(){
        }
    }
    class Disconnect extends JPanel{
        public Disconnect(){
        }
    }
    class Info extends JPanel{
        public Info(){
        }
    }
    class NewObject extends JPanel{
        public NewObject(){
        }
    }
    class NewFace extends JPanel{
        public NewFace(){
        }
    }
    class ModifyPhoto extends JPanel{
        public ModifyPhoto(){
        }
    }
    class ModifyVideo extends JPanel{
        public ModifyVideo(){
        }
    }
    class RecObjects extends JPanel{
        public RecObjects(){
        }
    }
    class RecFaces extends JPanel{
        public RecFaces(){
        }
    }
    class Help extends JOptionPane{
        public Help(){
            this.showMessageDialog(null,"Help","Help",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    class About extends JOptionPane{
        public About(){
            this.showMessageDialog(null,"About","About",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    class Contribute extends JOptionPane{
        public Contribute(){
            this.showMessageDialog(null,"Contribute","Contribute",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    class Donate extends JOptionPane{
        public Donate(){
            this.showMessageDialog(null,"Donate","Donate",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    class MinConfidence extends JOptionPane{
        public MinConfidence(){
            String minimum = this.showInputDialog(null,"Min confidence","Min confidence :",JOptionPane.QUESTION_MESSAGE);
        }
    }
    class MaxConfidence extends JOptionPane{
        public MaxConfidence(){
            String maximum = this.showInputDialog(null,"Max Confidence", "Max Confidence :",JOptionPane.QUESTION_MESSAGE);
        }
    }
    class ImageRes extends JOptionPane{
        public ImageRes(){
            String resolution = this.showInputDialog(null,"Image Resolution", "Image Resolution :", JOptionPane.QUESTION_MESSAGE);
        }
    }
    class Size extends JOptionPane{
        public Size(){
            String imageSize = this.showInputDialog(null, "Image Size", "Image Size :", JOptionPane.QUESTION_MESSAGE);
        }
    }
    class Filter extends JOptionPane{
        public Filter(){
            String imageFilter = this.showInputDialog(null, "Image Filter", "Image filter :", JOptionPane.QUESTION_MESSAGE);
        }
    }
    class OpenPhoto extends JOptionPane{
        public OpenPhoto(){
        }
    }
    class OpenVideo extends JOptionPane{
        public OpenVideo(){
        }
    }
    class Font extends JOptionPane{
        public Font(){
            String imageFont = this.showInputDialog(null, "Image Font", "Image font :", JOptionPane.QUESTION_MESSAGE);
        }
    }
}
