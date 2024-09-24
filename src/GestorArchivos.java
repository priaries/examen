import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class GestorArchivos {

    public static void guardarDibujo(LinkedList<Trazo> trazos) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Dibujo");
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; //la persona cancelo 
        }
        
        File fileToSave = fileChooser.getSelectedFile();
        String fileName = fileToSave.getAbsolutePath();
        
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(trazos);
            JOptionPane.showMessageDialog(null, "Dibujo guardado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el dibujo.");
        }
    }

    public static LinkedList<Trazo> cargarDibujo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar Dibujo");
        int userSelection = fileChooser.showOpenDialog(null);
        
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return null; // se cancela por peticion del usuario se hace para poner lo nulo
        }
        
        File fileToLoad = fileChooser.getSelectedFile();
        LinkedList<Trazo> trazos = new LinkedList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileToLoad))) {
            trazos = (LinkedList<Trazo>) in.readObject();
            JOptionPane.showMessageDialog(null, "Dibujo cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el dibujo.");
        }

        return trazos;
    }
}
