package RB_Tree;


public class complementary<T extends Comparable<T>> {
    // singleton Design pattern
    private static complementary instance;
    private static boolean isInstantiated = false;
    private complementary(){}
    public static complementary getInstance(){
        if(!isInstantiated){
            isInstantiated = true;
            return instance = new complementary();
        } else {
            return instance;
        }
    }
    // end singleton Design pattern

    public boolean search(T key, RBNode<T> currentNode){
        if( currentNode.getKey() == null ){
            return false;
        }
        int result = key.compareTo(currentNode.getKey());
        if ( result > 0 ) {
            return search(key, currentNode.getRightChild());
        } else if (result < 0) {
            return search(key, currentNode.getLeftChild());
        } else {
           return true;
        }
    }

    private RBNode<T> getNode(T key,RBNode<T>currentNode){
        if( currentNode.getKey() == null ){
            return null;
        }
        int result = key.compareTo(currentNode.getKey());
        if ( result > 0 ) {
            return getNode(key, currentNode.getRightChild());
        } else if (result < 0) {
            return getNode(key, currentNode.getLeftChild());
        } else {
            return currentNode;
        }
    }
    // wrong because of position of parent to grandpa.
    public void leftRotate(RBNode<T> currentNode){
        RBNode<T> oldGrandPa = currentNode.getParent().getParent();
        RBNode<T> oldParent = currentNode.getParent();

        // change connections of old grand pa.
        currentNode.setParent( oldGrandPa );
        if( oldGrandPa!=null ){
            if( oldGrandPa.getLeftChild().getKey() != null && oldGrandPa.getLeftChild() == oldParent ){
                oldGrandPa.setLeftChild( currentNode );
            } else {
                oldGrandPa.setRightChild( currentNode );
            }
        }

        // change intermediate value connections
        oldParent.setRightChild( currentNode.getLeftChild() );
        //if( currentNode.getLeftChild().getKey() != null ){
            currentNode.getLeftChild().setParent( oldParent );
        //} // ok

        // change current node and its old parent connections
        currentNode.setLeftChild( oldParent );
        oldParent.setParent( currentNode );

    }

    public RBNode<T> rightRotate(RBNode<T> currentNode) {
        RBNode<T> oldGrandPa = currentNode.getParent().getParent();
        RBNode<T> oldParent = currentNode.getParent();

        // change connections of old grand pa.
        currentNode.setParent( oldGrandPa );
        if( oldGrandPa != null ){
            if( oldGrandPa.getLeftChild().getKey() != null && oldGrandPa.getLeftChild() == oldParent ){
                oldGrandPa.setLeftChild( currentNode );
            } else {
                oldGrandPa.setRightChild( currentNode );
            }
        }

        // change intermediate value connections
        oldParent.setLeftChild( currentNode.getRightChild() );
        //if( currentNode.getRightChild().getKey() != null ){
            currentNode.getRightChild().setParent( oldParent );
       // } //

        // change current node and its old parent connections
        currentNode.setRightChild( oldParent );
        oldParent.setParent( currentNode );
        return currentNode;
    }

    public RBNode<T> insert(T valueInserted, RBNode<T> currentNode, RBNode<T> controllerNode, RB RBClass) {
        if( currentNode.getKey() ==null ){
            RBClass.incrementSize();
            currentNode.setKey(valueInserted);
            currentNode.setColor(Color.RED);
            RBNode<T>TNull1=new RBNode<T>(null);
            RBNode<T>TNull2=new RBNode<T>(null);
            TNull1.setColor(Color.BLACK);
            TNull2.setColor(Color.BLACK);
            TNull1.setParent(currentNode);
            TNull2.setParent(currentNode);
            currentNode.setLeftChild(TNull1);
            currentNode.setRightChild(TNull2);
            if( RBClass.getRoot().getKey() !=null ){
                checkBalance(currentNode);
                return RBClass.getRoot();
            } else {
                return currentNode;
            }
        }

        int result = currentNode.getKey().compareTo(valueInserted);
        if (result < 0) {
            insert(valueInserted, currentNode.getRightChild(), currentNode, RBClass);
        } else if( result > 0 ) {
            insert(valueInserted, currentNode.getLeftChild(), currentNode, RBClass);
        } else {
            // already exists.
            return RBClass.getRoot();

        }

        // check balance part.

        checkBalance(currentNode);
        if( RBClass.getRoot().getParent() != null )return RBClass.getRoot().getParent();
        else return RBClass.getRoot();
    }

    public void checkBalance(RBNode<T> currentNode) {
        if( currentNode.getColor() == Color.BLACK ){
            return;
        }
        else if( currentNode.getParent() == null && currentNode.getColor() == Color.RED ){
            currentNode.setColor( Color.BLACK );
        }
        else if( currentNode.getColor() == Color.RED && currentNode.getParent().getColor() == Color.BLACK ){
            return;
        }
        else if( currentNode.getColor() == Color.RED
                && currentNode.getParent().getColor() == Color.RED
                && uncleNode( currentNode ).getKey() != null
                && uncleNode(currentNode).getColor() == Color.RED ){

            currentNode.getParent().setColor( Color.BLACK );
            uncleNode( currentNode ).setColor( Color.BLACK );
            currentNode.getParent().getParent().setColor( Color.RED );

        }
        else if( currentNode.getColor() == Color.RED
                && currentNode.getParent().getColor() == Color.RED
                && (uncleNode( currentNode ).getKey() == null || uncleNode(currentNode).getColor() == Color.BLACK ) ){

            boolean startedByLeft, endedByLeft;
            startedByLeft = ( currentNode.getParent().getParent().getLeftChild() == currentNode.getParent() );
            endedByLeft   = ( currentNode.getParent().getLeftChild() == currentNode );

            // four cases LL, RR, LR and RL
            if( startedByLeft && endedByLeft ){
                rightRotate( currentNode.getParent() );
                currentNode.getParent().setColor( Color.BLACK );
                currentNode.getParent().getRightChild().setColor( Color.RED );
            } else if( !startedByLeft && !endedByLeft ){
                leftRotate( currentNode.getParent() );
                currentNode.getParent().setColor( Color.BLACK );
                currentNode.getParent().getLeftChild().setColor( Color.RED );
            } else if( startedByLeft && !endedByLeft ){
                leftRotate( currentNode );
                rightRotate( currentNode );
                currentNode.setColor( Color.BLACK );
                currentNode.getLeftChild().setColor( Color.RED );
                currentNode.getRightChild().setColor( Color.RED );
            } else {
                rightRotate( currentNode );
                leftRotate( currentNode );
                currentNode.setColor( Color.BLACK );
                currentNode.getLeftChild().setColor( Color.RED );
                currentNode.getRightChild().setColor( Color.RED );
            }
        }
        else {
            System.out.println( "unknown case in insertion cases" );
        }

    }
    private RBNode uncleNode(RBNode<T> currentNode) {
        RBNode<T> grandPa = currentNode.getParent().getParent();
        RBNode<T> parent = currentNode.getParent();

        if( grandPa.getLeftChild() == parent ){
            return grandPa.getRightChild();
        }
        else {
            return grandPa.getLeftChild();
        }
    }

    public boolean deleteNode(T key,RBNode<T>currentNode,RB<T>usedTree){
      RBNode<T> nodeToBeDeleted=new RBNode<T>(null);
      RBNode<T>fixup_start;
      RBNode<T>replacement;
      nodeToBeDeleted=getNode(key,currentNode);
      if(nodeToBeDeleted==null){
          return false;
      }
      replacement=nodeToBeDeleted;
      Color originalColor=replacement.getColor();
      if(replacement.getLeftChild().getKey()==null){
          fixup_start=replacement.getRightChild();
          usedTree.transplant(replacement,replacement.getRightChild());
      }
      else if(replacement.getRightChild().getKey()==null){
          fixup_start=replacement.getLeftChild();
          usedTree.transplant(replacement,replacement.getLeftChild());
      }
      else{
          replacement=minimum(nodeToBeDeleted.getRightChild());
          originalColor=replacement.getColor();
          fixup_start=replacement.getRightChild();
          if(replacement.getParent()==nodeToBeDeleted){
              fixup_start.setParent(replacement);
          }
          else{
              usedTree.transplant(replacement,replacement.getRightChild());
              replacement.setRightChild(nodeToBeDeleted.getRightChild());
              replacement.getRightChild().setParent(replacement);
          }
          usedTree.transplant(nodeToBeDeleted,replacement);
          replacement.setLeftChild(nodeToBeDeleted.getLeftChild());
          replacement.getLeftChild().setParent(replacement);
          replacement.setColor(nodeToBeDeleted.getColor());
      }
      if(originalColor==Color.BLACK){
          delete_fixup(fixup_start,usedTree);
      }
      return true;
    }

    public void delete_fixup(RBNode<T> currentNode,RB<T> tree){
            RBNode<T> sibling;
            while (currentNode != tree.getRoot() && currentNode.getColor() == Color.BLACK) {
                if (currentNode == currentNode.getParent().getLeftChild()) {
                    sibling = currentNode.getParent().getRightChild();
                    if (sibling.getColor() == Color.RED) {
                        sibling.setColor(Color.BLACK);
                        currentNode.getParent().setColor(Color.RED);
                        leftRotate_delete(currentNode.getParent(),tree);
                        sibling = currentNode.getParent().getRightChild();
                    }

                    if (sibling.getLeftChild().getColor() == Color.BLACK && sibling.getRightChild().getColor() == Color.BLACK) {
                        sibling.setColor( Color.RED);
                        currentNode = currentNode.getParent();
                    } else {
                        if (sibling.getRightChild().getColor() == Color.BLACK) {
                            sibling.getLeftChild().setColor(Color.BLACK);
                            sibling.setColor( Color.RED);
                            rightRotate_delete(sibling,tree);
                            sibling = currentNode.getParent().getRightChild();
                        }

                        sibling.setColor(currentNode.getParent().getColor());
                        currentNode.getParent().setColor(Color.BLACK);
                        sibling.getRightChild().setColor(Color.BLACK);
                        leftRotate_delete(currentNode.getParent(),tree);
                        currentNode = tree.getRoot();
                    }
                }
                else {
                    sibling = currentNode.getParent().getLeftChild();
                    if (sibling.getColor() == Color.RED) {
                        sibling.setColor(Color.BLACK);
                        currentNode.getParent().setColor(Color.RED);
                        rightRotate_delete(currentNode.getParent(),tree);
                        sibling = currentNode.getParent().getLeftChild();
                    }

                    if (sibling.getRightChild().getColor() == Color.BLACK && sibling.getLeftChild().getColor() == Color.BLACK) {
                        sibling.setColor(Color.RED);
                        currentNode = currentNode.getParent();
                    } else {
                        if (sibling.getLeftChild().getColor() == Color.BLACK) {
                            sibling.getRightChild().setColor(Color.BLACK);
                            sibling.setColor(Color.RED);
                            leftRotate_delete(sibling,tree);
                            sibling = currentNode.getParent().getLeftChild();
                        }

                        sibling.setColor(currentNode.getParent().getColor());
                        currentNode.getParent().setColor(Color.BLACK);
                        sibling.getLeftChild().setColor(Color.BLACK);
                        rightRotate_delete(currentNode.getParent(),tree);
                        currentNode = tree.getRoot();
                    }
                }
            }
            currentNode.setColor(Color.BLACK);
    }
    public RBNode<T> minimum(RBNode<T> currentNode){
        while (currentNode.getLeftChild().getKey()!=null){
            currentNode=currentNode.getLeftChild();
        }
        return currentNode;
    }


    public int updateHeight( RBNode<T> currentNode ) {
        if( currentNode.getKey() == null ){
            return -1;
        }
        int leftHeight = updateHeight( currentNode.getLeftChild() );
        int rightHeight = updateHeight( currentNode.getRightChild() );
        currentNode.setHeight( Math.max(leftHeight + 1, rightHeight + 1) );
        return currentNode.getHeight();
    }
    public void leftRotate_delete(RBNode<T> x,RB<T> tree) {
        RBNode<T> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild().getKey() != null) {
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            tree.setRoot(y);
        } else if (x == x.getParent().getLeftChild()) {
            x.getParent().setLeftChild(y);
        } else {
            x.getParent().setRightChild(y);
        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    public void rightRotate_delete(RBNode<T> x,RB<T> tree) {
        RBNode<T> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (y.getRightChild().getKey() !=null ) {
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            tree.setRoot(y);
        } else if (x == x.getParent().getRightChild()) {
            x.getParent().setRightChild(y);
        } else {
            x.getParent().setLeftChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);
    }
}
