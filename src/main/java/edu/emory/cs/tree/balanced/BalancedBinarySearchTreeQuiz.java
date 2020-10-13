/*
 * Copyright 2020 Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.BinaryNode;

/** @author Jinho D. Choi */
public class BalancedBinarySearchTreeQuiz<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, BinaryNode<T>> {
    @Override
    public BinaryNode<T> createNode(T key) {
        return new BinaryNode<>(key);
    }

    @Override
    protected void balance(BinaryNode<T> node) {
        // check conditions
        if(!node.hasParent()) return; //needs to have a parent
        if(!node.getParent().hasParent()) return; //needs to have a grandparent
        BinaryNode<T> parent = node.getParent();
        BinaryNode<T> grandparent = node.getGrandParent();
        BinaryNode<T> uncle = node.getUncle();
        if(node.getSibling() != null || grandparent.getRightChild() != node.getParent() || uncle.hasBothChildren()) return;

        //if conditions are satisfied, perform rotations
        if(uncle.hasRightChild() && parent.hasLeftChild()){  //case 3
            rotateLeft(uncle);
            rotateRight(parent);
        }

        if(uncle.hasRightChild() && parent.hasRightChild()) {  //case 2
            rotateLeft(uncle);
        }

        if(uncle.hasLeftChild() && parent.hasLeftChild()) { //case 1
            rotateRight(parent);
        }

        rotateLeft(grandparent); //all cases end up as case 4
        rotateRight(grandparent);
    }
}