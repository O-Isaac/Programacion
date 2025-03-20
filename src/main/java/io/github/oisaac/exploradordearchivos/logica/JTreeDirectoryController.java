/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.exploradordearchivos.logica;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.function.Consumer;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


/**
 * Este controlador se encarga de generar la estructura de directorios y archivos
 * @author Isaac
 */
public class JTreeDirectoryController extends JTree {
    private JPopupMenu contextMenu = new JPopupMenu();
    private JGuiController jgc = new JGuiController(this);
    
    private File rootFile;
    private File currentFileSelected;
    
    public JTreeDirectoryController(File rootFile) {
        super();
        this.rootFile = rootFile;
        this.setModel(getRootTreeModel(rootFile));
        this.initContextMenu();
    }
    
    private void initContextMenu() {
        // TODO: Refactor
        JMenuItem deleteItem = new JMenuItem("Eliminar");
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        contextMenu.add(deleteItem);
        
        JMenuItem propsItem = new JMenuItem("Propiedades");
        contextMenu.add(propsItem);
        
        propsItem.addActionListener((ActionEvent e) -> {
            showFilePropertiesDialog(currentFileSelected);
        });

        deleteItem.addActionListener((ActionEvent e) -> {
            boolean elimina = jgc.prompt("Aviso", "Estas seguro de eliminar " + currentFileSelected.getName());
            
            if (elimina) {
                currentFileSelected.delete();
                jgc.alert("Exito", currentFileSelected.getName() + " Eliminado con exito!");
                updateTree();
            }
        });
    }
    
    private void showFilePropertiesDialog(File file) {
        String name = file.getName();
        long size = file.length(); // Tamaño en bytes
        
        String permission =
            (file.canRead() ? "r" : "-") +
            (file.canWrite() ? "w" : "-") +
            (file.canExecute() ? "x" : "-");
        
        String message = String.format(
            """
            Nombre: %s
            Peso: %s bytes
            Permisos: %s
            """,
           name, size, permission
        );

        JOptionPane.showMessageDialog(null, message, "Propiedades del archivo", JOptionPane.PLAIN_MESSAGE);
    }

    
    private DefaultTreeModel getRootTreeModel(File rootFile) {
        JTreeDirectoryNodeController jtdnc = new JTreeDirectoryNodeController(rootFile);
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(jtdnc);
        DefaultTreeModel dtm = new DefaultTreeModel(dmtn);
        
        buildDirectoryTree(dmtn, rootFile);
        
        return dtm;
    }
    
    private void showContextMenu(JTree tree, MouseEvent e, TreePath path) {
        setSelectionPath(path);
        contextMenu.show(tree, e.getX(), e.getY());
    }
    
    public void onClick(Consumer<JTreeDirectoryNodeController> callback) {
        JTree tree = this; // Usarlo en mouseClicked
        
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                if (e.isPopupTrigger() && path != null) {
                    
                    showContextMenu(tree, e, path);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                
                // Solamente necesito el archivo cuando deje presione el click derecho
                if (e.isPopupTrigger() && path != null) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    JTreeDirectoryNodeController jtdnc = (JTreeDirectoryNodeController) selectedNode.getUserObject();
                    currentFileSelected = jtdnc.getFile();
                    showContextMenu(tree, e, path);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                if (path != null) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    JTreeDirectoryNodeController jtdnc = (JTreeDirectoryNodeController) selectedNode.getUserObject();
                    currentFileSelected = jtdnc.getFile();
                    callback.accept(jtdnc);
                }
            }
            
        });
    }
    
    private void buildDirectoryTree(DefaultMutableTreeNode node, File file) {
        File[] files = file.listFiles();

        if (files != null) {
            for (File dirFile : files) {
                JTreeDirectoryNodeController jtdnc = new JTreeDirectoryNodeController(dirFile);
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(jtdnc);

                node.add(childNode);

                if (file.isDirectory()) {
                    buildDirectoryTree(childNode, dirFile);
                }
            }
        }
    }
    
    
    public void updateTree() {
        this.setModel(getRootTreeModel(rootFile));
        this.updateUI();
    }
}
