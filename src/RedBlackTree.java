//import RBTree.Node;

public class RedBlackTree {
	public static final boolean RED = true;
	public static final boolean BLACK = false;
	//private RBNode nil = new RBNode();
	private RBNode root;
	public class RBNode
	{
		public char key;
		public RBNode left, right, parent;
		public boolean color;
		
		public RBNode()
		{
			
			root.right = null;
			root.left = null;
			root.parent = null;
			root.color = RedBlackTree.RED;
			
		}
		
		public RBNode (char key)
		{
			this.key = key;
			/*
			root.right = nil;
			root.left = nil;
			root.parent = nil;
			*/
		}
	}
	
	public RedBlackTree()
	{
		/*
		root.right = nil;
		root.left = nil;
		root.parent = nil;
		root.color = RedBlackTree.RED;
		*/
	}
	
	public void insert(char key)
	{
		root = insertRec(root, key);
	}
	
	 RBNode insertRec(RBNode root, char key) 
	 {
		 if (root == null) 
		 {
			 root = new RBNode(key);
			 return root;
		 }
	 
		 if (key < root.key)
			 root.left = insertRec(root.left, key);
		 else if (key > root.key)
			 root.right = insertRec(root.right, key);
	 
		 return root;
	  }
	 
	 public void printTree()
	 {
		 printTree(root);
	 }
	 
	 private void printTree(RBNode x)
	 {
		 if(x != null)
		 {
			 System.out.print(root.key + " ");
			 printTree(x.left);
			 printTree(x.right);
		 }
	 }
	 
	 public void addNode(char key)
	 {
		 RBNode z = new RBNode(key);
		 RBNode y = null;
		 RBNode x = root;
		 
		 while (!isNil(x))
		 {
			y = x;
			if (z.key < x.key)
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;
			
		if (isNil(y))
			root = z;
		else if (z.key < y.key)
			y.left = z;
		else
			y.right = z;

		z.left = null;
		z.right = null;
		z.color = RedBlackTree.RED;
		fixTree(z);
	 }
	 
	 public void leftRotate(RBNode x)
	 {
		 RBNode y = x.right;
		 x.right = y.left;
		 if(!isNil(y.left))
			 y.left.parent = x;
		 y.parent = x.parent;
		 
		 if (isNil(x.parent))
			root = y;
		else if (x.parent.left == x)
			x.parent.left = y;
		else
			x.parent.right = y;
		 y.left = x;
		x.parent = y;
	 }
	 
	 public void rightRotate(RBNode y)
	 {
		 RBNode x = y.left;
		 y.left = x.right;
		 
		 if (isNil(x.parent))
				root = y;
		 else if (x.parent.left == x)
			x.parent.left = y;
		 else
			x.parent.right = y;

		 y.left = x;
		x.parent = y;
	 }
	 
	 public RBNode getSiblibgs(RBNode node)
	 {
		 if (node.parent == null) 
	            return null;
		 if (node == node.parent.left) 
	            return node.parent.right;
	        return node.parent.left;
	 }
	 
	 public RBNode getAunt(RBNode node)
	 {
		 RBNode grandParent = getGrandParent(node);
		 if (grandParent == null) 
			 return null;
	     if (node.parent == grandParent.left) 
	    	 return grandParent.right;
	     else 
	    	 return grandParent.left;
	        
	 }
	 
	 public RBNode getGrandParent(RBNode node)
	 {
		 if (node.parent == null) 
	            return null;
	        return node.parent.parent;
	 }
	 
	 public void fixTree(RBNode node)
	 {
		 if (node.parent == null) 
		 {
			 node.color = BLACK;
			 this.root = node;
			 return;
		 }
		 if (node.parent.color == BLACK) 
	            return;
		 if(node.color == RED && node.parent.color == RED)
		 {
			 RBNode aunt = getAunt(node);
			 RBNode grandParent = getGrandParent(node);
			 RBNode parent = node.parent;
			 if(getAunt(node) == null || getAunt(node).color == BLACK)
			 {
				 if(grandParent.left == parent && parent.right == node)
				 {
					 leftRotate(parent);
					 fixTree(parent);
				 }
				 else if(grandParent.right == parent && parent.left == node)
				 {
					 rightRotate(parent);
					 fixTree(parent);
				 }
				 else if(grandParent.left == parent && parent.right == node)
				 {
					 parent.color = BLACK;
					 grandParent.color = RED;
					 rightRotate(grandParent);
				 }
				 else if(grandParent.right == parent && parent.right == node)
				 {
					 parent.color = BLACK;
					 grandParent.color = RED;
					 leftRotate(grandParent);
				 }
			 }
			 else if(aunt.color == RED)
			 {
				 parent.color = BLACK;
				 aunt.color = BLACK;
				 grandParent.color = RED;
				 fixTree(grandParent);
			 }
		 }
	 }
	 
	 private boolean isNil(RBNode n)
	 {
		 return n == null;
	 }
	 
	 public static interface Visitor
	 {
	  //  	/**
	  //     This method is called at each node.
	  //     @param n the visited node
	  //  	 */
		 void visit(RBNode n);
	 }
	 
	  
	 public void preOrderVisit(Visitor v)
	 {
		 preOrderVisit(root, v);
	 }


	 private static void preOrderVisit(RBNode n, Visitor v)
	 {
		 if (n == null) return;
		 v.visit(n);
		 preOrderVisit(n.left, v);
		 preOrderVisit(n.right, v);
		 }
}
