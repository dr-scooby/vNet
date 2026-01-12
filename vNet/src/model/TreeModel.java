
package model;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Handle the JTree and treenodes here
 * @author nurali
 */
public class TreeModel {
    
    private DefaultMutableTreeNode treerotenode; // root node 
    private DefaultTreeModel treemodel; // tree model 
    
    // default constructor
    public TreeModel(){
        // init the root node
        treerotenode = new DefaultMutableTreeNode("No Data");
        // init the tree model
        treemodel = new DefaultTreeModel(treerotenode);
    }
    
    // clear the Tree data
    public void clearTree(){
        treerotenode = new DefaultMutableTreeNode("No Data");
        treemodel.setRoot(treerotenode);
        treemodel.reload();
    }
    
    // init the Tree with a server ip
    public void init(String server){
        treerotenode = new DefaultMutableTreeNode(server);
        treemodel.setRoot(treerotenode);
        treemodel.reload();
    }
    
    // set up tree with a File object
    public void setupTree(File f){
        DefaultMutableTreeNode node; // stores a tree node
        File[] listing =  f.listFiles();// get the files listing
        for(int i=0; i < listing.length; i++){
            // check if directory
            if(listing[i].isDirectory()){
                // create a tree node
                node = new DefaultMutableTreeNode(listing[i].getName()); // adding to node as String object, IMPORTANT !!!
                // add the node to the root
                treerotenode.add(node);
                // since it's a directory, send the node and directory to add the node
                addNode(node, listing[i]);
            }else{
                // not a directory, only a file name
                node = new DefaultMutableTreeNode(listing[i].getName()); // adding to node as String object, IMPORTANT !!!
                treerotenode.add(node);
            }
        }
        
        // important to reload the tree model
         treemodel.reload();
    }
    
    // add a tree node, perform recursive calls
    public void addNode(DefaultMutableTreeNode node, File filenode){
        DefaultMutableTreeNode anode; // stores a tree node
        File[] listing =  filenode.listFiles();
        for(int i=0; i < listing.length; i++){
            if(listing[i].isDirectory()){
                anode = new DefaultMutableTreeNode(listing[i].getName()); // adding to node as String object, IMPORTANT !!!
                node.add(anode);
                addNode(anode, listing[i]); // recursive call
            }else{
                anode = new DefaultMutableTreeNode(listing[i].getName()); // adding to node as String object, IMPORTANT !!!
                node.add(anode);
            }
        }
    }
    
    public DefaultTreeModel getTreeModel(){
        return treemodel;
    }
}
