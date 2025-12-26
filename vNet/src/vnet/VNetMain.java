
package vnet;

import java.awt.*;
import vgui.vGUI;

/**
 * Using Swing components
 * Contacts and Net program components.
 * @author nurali
 */
public class VNetMain {
    
    
    public VNetMain(){
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();
        int screenheight = screensize.height;
        int screenwidth = screensize.width;
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                // "Nimbus"
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            //logger.log(java.util.logging.Level.SEVERE, null, ex);
            System.err.println(ex);
        }
        
        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new vGUI().setVisible(true));
        vGUI view = new vGUI();
        view.setLocation(screenwidth/4, screenheight/4);
        view.setVisible(true);
        kit.beep();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new VNetMain();
    }
    
}
