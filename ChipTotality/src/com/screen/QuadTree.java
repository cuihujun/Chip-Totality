package com.screen;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * A QuadTree implementation to reduce collision checks. Every level contains
 * a maximum of 10 objects and the tree sub divides on exceeding this limit.
 * 
 * @author Sri Harsha Chilakapati
 */
public class QuadTree {

    // The MAX_OBJECTS and LEVEL constants
    private final int MAX_OBJECTS = 10;
    private final int level;
    
    // The objects list
    private final ArrayList<StageObject> objects;
    // The retrieve list
    private ArrayList<StageObject> retrieveList;

    // The bounds of this tree
    private final Rectangle bounds;

    // Branches of this tree a.k.a the quadrants
    private final QuadTree[] nodes;

    /**
     * Construct a QuadTree with default values. Set's for the whole screen
     */


    /**
     * Construct a QuadTree with custom values. Used to create sub trees or branches
     * @param l The level of this tree
     * @param b The bounds of this tree
     */
    public QuadTree(int l, Rectangle b) {
        level = l;
        bounds = b;
        objects = new ArrayList<StageObject>();
        retrieveList = new ArrayList<StageObject>();
        nodes = new QuadTree[4];
    }
    
    /**
     * Set's the bounds of this tree.
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param width The width
     * @param height The height
     */
    public void setBounds(int x, int y, int width, int height){
        bounds.x = x;
        bounds.y = y;
        bounds.width = width;
        bounds.height = height;
        clear();
        split();
    }

    /**
     * Clear this tree. Also clears any subtrees.
     */
    public void clear(){
        objects.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    // Split the tree into 4 quadrants
    private void split(){
        int subWidth = (int) (bounds.getWidth() / 2);
        int subHeight = (int) (bounds.getHeight() / 2);
        int x = (int) bounds.getX();
        int y = (int) bounds.getY();
        nodes[0] = new QuadTree(level + 1, new Rectangle(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new QuadTree(level + 1, new Rectangle(x, y, subWidth, subHeight));
        nodes[2] = new QuadTree(level + 1, new Rectangle(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new QuadTree(level + 1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
    }

    // Get the index of an object
    private int getIndex(StageObject r){
        int index = -1;
        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
        boolean topQuadrant = (r.getY() < horizontalMidpoint && r.getY() + r.getHeight() < horizontalMidpoint);
        boolean bottomQuadrant = (r.getY() > horizontalMidpoint);
        if (r.getX() < verticalMidpoint && r.getX() + r.getWidth() < verticalMidpoint){
            if (topQuadrant){
                index = 1;
            } else if (bottomQuadrant){
                index = 2;
            }
        } else if (r.getX() > verticalMidpoint){
            if (topQuadrant){
                index = 0;
            } else if (bottomQuadrant){
                index = 3;
            }
        }
        return index;
    }
    
    // Get the index of a rectangle
    private int getIndex(Rectangle r){
        int index = -1;
        double verticalMidpoint = bounds.x + (bounds.width / 2);
        double horizontalMidpoint = bounds.y + (bounds.height / 2);
        boolean topQuadrant = (r.y < horizontalMidpoint && r.y + r.height < horizontalMidpoint);
        boolean bottomQuadrant = (r.y > horizontalMidpoint);
        if (r.x < verticalMidpoint && r.x + r.width < verticalMidpoint){
            if (topQuadrant){
                index = 1;
            } else if (bottomQuadrant){
                index = 2;
            }
        } else if (r.getX() > verticalMidpoint){
            if (topQuadrant){
                index = 0;
            } else if (bottomQuadrant){
                index = 3;
            }
        }
        return index;
    }
    
    /**
     * Insert an object into this tree
     */
    public void insert(StageObject r){
        if (nodes[0]!=null){
            int index = getIndex(r);
            if (index!=-1){
                nodes[index].insert(r);
                return;
            }
        }
        objects.add(r);
        if (objects.size() > MAX_OBJECTS){
            if (nodes[0]==null){
                split();
            }
            for (int i=0; i<objects.size(); i++){
                int index = getIndex(objects.get(i));
                if (index!=-1){
                    nodes[index].insert(objects.remove(i));
                }
            }
        }
    }
    
    /**
     * Insert an ArrayList of objects into this tree
     */
    public void insert(ArrayList<StageObject> o){
        for (int i=0; i<o.size(); i++){
            insert(o.get(i));
        }
    }
    
    /**
     * Returns the collidable objects with the given object
     */
    public ArrayList<StageObject> retrieve(StageObject r){
        retrieveList.clear();
        int index = getIndex(r);
        if (index != -1 && nodes[0] != null){
            retrieveList = nodes[index].retrieve(r);
        }
        retrieveList.addAll(objects);
        return retrieveList;
    }
    
    /**
     * Returns the collidable objects with the given rectangle
     */
    public ArrayList<StageObject> retrieve(Rectangle r){
        retrieveList.clear();
        int index = getIndex(r);
        if (index != -1 && nodes[0] != null){
            retrieveList = nodes[index].retrieve(r);
        }
        retrieveList.addAll(objects);
        return retrieveList;
    }
} 







/*
// 1|0	quadtree Rectangle2D division
// ---
// 2|3
public class QuadTree<T extends StageObject> {
	private final int MAX_OBJECTS = 1;
	private final int MAX_LEVELS = 1;

	private final int level;
	private final Rectangle2D bounds;
	private final List<T> objects;
	private final QuadTree<T>[] nodes;

	public QuadTree(int level, Rectangle2D bounds) {
		this.level = level;
		this.bounds = bounds;
		objects = new ArrayList<T>();
		nodes = new QuadTree[4];
	}

	// clears all objects
	public void clear() {
		objects.clear();

		for (QuadTree<T> node : nodes) {
			if (node != null) {
				node.clear();
				node = null;
			}
		}
	}

	// splits current node into 4 new ones
	 private void split() {
		   int subWidth = (int)(bounds.getWidth() / 2);
		   int subHeight = (int)(bounds.getHeight() / 2);
		   int x = (int)bounds.getX();
		   int y = (int)bounds.getY();
		 
		   nodes[0] = new QuadTree<T>(level+1, new Rectangle2D.Float(x + subWidth, y, subWidth, subHeight));
		   nodes[1] = new QuadTree<T>(level+1, new Rectangle2D.Float(x, y, subWidth, subHeight));
		   nodes[2] = new QuadTree<T>(level+1, new Rectangle2D.Float(x, y + subHeight, subWidth, subHeight));
		   nodes[3] = new QuadTree<T>(level+1, new Rectangle2D.Float(x + subWidth, y + subHeight, subWidth, subHeight));
		 }

	 private int getIndex(T pRect) {
		   int index = -1;
		   double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		   double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
		 
		   // Object can completely fit within the top quadrants
		   boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
		   // Object can completely fit within the bottom quadrants
		   boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
		 
		   // Object can completely fit within the left quadrants
		   if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
		      if (topQuadrant) {
		        index = 1;
		      }
		      else if (bottomQuadrant) {
		        index = 2;
		      }
		    }
		    // Object can completely fit within the right quadrants
		    else if (pRect.getX() > verticalMidpoint) {
		     if (topQuadrant) {
		       index = 0;
		     }
		     else if (bottomQuadrant) {
		       index = 3;
		     }
		   }
		 
		   return index;
		 }

	 public void insert(T pRect) {
		   if (nodes[0] != null) {
		     int index = getIndex(pRect);
		 
		     if (index != -1) {
		       nodes[index].insert(pRect);
		 
		       return;
		     }
		   }
		 
		   objects.add(pRect);
		 
		   if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
		      if (nodes[0] == null) { 
		         split(); 
		      }
		 
		     int i = 0;
		     while (i < objects.size()) {
		       int index = getIndex(objects.get(i));
		       if (index != -1) {
		         nodes[index].insert(objects.get(i));
		         objects.remove(i);
		       }
		       else {
		         i++;
		       }
		     }
		   }
		 }

	// returns all objects potentially colliding
	public List<T> retrieve(List<T> returnObjects, T object) {
		int index = getIndex(object);
		// until it has some nodes, retrieve them...
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, object);
		}
		// and add them to list of object, to be returned
		returnObjects.addAll(objects);

		return returnObjects;
	}*/


