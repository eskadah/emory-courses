/**
 * Copyright 2014, Emory University
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
package edu.emory.mathcs.cs323.tree;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>
{
	public AVLTree()
	{
		setRoot(null);
	}
	
	/** If this tree already contains the key, nothing is added. */
	public BinaryNode<T> add(T key)
	{
		BinaryNode<T> node = super.add(key);
		balance(node);
		return node;
	}
	
	public BinaryNode<T> remove(T key)
	{
		BinaryNode<T> node = findNode(n_root, key);
		
		if (node != null)
		{
			BinaryNode<T> update = node.hasBothChildren() ? removeHibbard(node) : removeSelf(node);
			if (update != null) update.resetHeight();
		}
		
		return node;
	}
	
	private void balance(BinaryNode<T> node)
	{
		if (node == null) return;
		int bf = node.getBalanceFactor();
		
		if (bf == 2)
		{
			BinaryNode<T> child = node.getLeftChild();
			
			if (child.getBalanceFactor() == -1)
				rotateLeft(child);
			
			rotateRight(node);
		}
		else if (bf == -2)
		{
			BinaryNode<T> child = node.getRightChild();
			
			if (child.getBalanceFactor() == 1)
				rotateRight(child);
			
			rotateLeft(node);
		}
		else
			balance(node.getParent());
	}
	
	private void rotateLeft(BinaryNode<T> node)
	{
		BinaryNode<T> child = node.getRightChild();
		
		node.setRightChild(child.getLeftChild());
		
		if (node.hasParent())
			node.getParent().replaceChild(node, child);
		else
			setRoot(child);
		
		child.setLeftChild(node);
		node.resetHeight();
	}
	
	private void rotateRight(BinaryNode<T> node)
	{
		BinaryNode<T> child = node.getLeftChild();
		
		node.setLeftChild(child.getRightChild());
		
		if (node.hasParent())
			node.getParent().replaceChild(node, child);
		else
			setRoot(child);
		
		child.setRightChild(node);
		node.resetHeight();
	}
}
