import java.util.ArrayList;

class BinaryTreeB {

    public static class Node{
        int data;
        Node right;
        Node left;

        public Node(int data){
            this.data = data;
        }
    }

    public static Node insert(Node root,int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            root.left = insert(root.left,val);
        }else{
            root.right = insert(root.right,val);
        }
        return root;
    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }
       inorder(root.left);
        System.out.print(root.data+"  ");
        inorder(root.right);
    }


    public static boolean Search(Node root,int key){   //O(H)
        // in worst case = O(N)
        //in average and best case = O(logN)
        if(root == null){
            return false;
        }
        if (root.data == key) {
            return true;
        }
        if(root.data > key){
           return  Search(root.left,key);
        }
        else{
          return  Search(root.right,key);
        }

    }

    public static Node delete(Node root,int val){
        if(root.data < val){
            root.right = delete(root.right,val);
        }else if(root.data > val){
            root.left = delete(root.right,val);
        }else{
            //case1
            if(root.left == null && root.right == null){
                return null;
            }

            //case2
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }

            //case3

            Node  IS = findinorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);

        }
        return root;
    }
    public static Node findinorderSuccessor(Node root){
        while(root.left!=null){
            root = root.left;

        }
        return root;
    }

    public static void printInRange(Node root,int k1,int k2){
        if(root == null){
            return;
        }

         if(root.data > k1){
            printInRange(root.left,k1,k2);
        }
        if(root.data >= k1 && root.data <= k2){
            System.out.print(root.data+" ");
        }
        if(root.data < k2){
            printInRange(root.right,k1,k2);
        }
    }



    public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "->");
        }
        System.out.println("null");
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }

        // Add the current node to the path
        path.add(root.data);

        // If it's a leaf node, print the path
        if (root.left == null && root.right == null) {
            printPath(path);
        } else {
            // Otherwise, continue the search on the left and right subtrees
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }

        // Remove the current node from the path to backtrack
        path.remove(path.size() - 1);
    }

    public static boolean isValidBST(Node root,Node min,Node max){
         if(root == null){
             return true;
         }
         if(min !=null && root.data <= min.data){
             return false;
         }
         else if(max != null && root.data >= max.data){
             return false;
         }
           return  isValidBST(root.left,min,root) && isValidBST(root.right,root,max);


    }

    public static Node MirrorBST(Node root){
        if(root == null){
            return null;
        }
        Node leftmirror = MirrorBST(root.left);
        Node rightmirror = MirrorBST(root.right);

        root.left = rightmirror;
        root.right = leftmirror;

        return root;
    }



    public static void main(String[] args) {

        int values[]={7,5,9,3,6,8,10};  //invalid bst
        Node root =null;

        for(int i=0;i< values.length;i++){
           root = insert(root,values[i]);
        }
        inorder(root);
        System.out.println();



//        Node root = new Node(7);           //valid bst
//        root.left = new Node(5);
//        root.right = new Node(9);
//        root.left.left = new Node(3);
//        root.left.right = new Node(6);
//        root.right.left = new Node(8);
//        root.right.right = new Node(10);


//
//            if(Search(root,1)){
//                System.out.println("found key");
//            }else{
//                System.out.println(" not found key");
//            }

//            root = delete(root,10);
//            System.out.println();
//            inorder(root);

        //print the nodes in range
//             System.out.print("print the node in range = ");
//            printInRange(root,5,12);

        //printrootleaf path
        ArrayList<Integer> path = new ArrayList<>();
            printRootToLeaf(root,path);


            if(isValidBST(root,null,null)){
                System.out.print("valid BST");
        }else{
                System.out.print("invalid BST");
            }
            System.out.println();


            System.out.print("mirror image of BST = ");
        root = MirrorBST(root);
            inorder(root);


    }
}