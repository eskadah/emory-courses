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
public class BinarySearchTree<T extends Comparable<T>>
{
	protected BinaryNode<T> n_root;
	
	public BinarySearchTree()
	{
		setRoot(null);
	}
	
	/** @return the minimum key in this tree if exists; otherwise, {@code null}. */
	public T min()
	{
		return (n_root != null) ? findMinNode(n_root).getKey() : null;
	}
	
	/** @return the maximum key in this tree if exists; otherwise, {@code null}. */
	public T max()
	{
		return (n_root != null) ? findMaxNode(n_root).getKey() : null;
	}
	
	/** @return {@code true} if the specific key exists; otherwise, {@code false}. */
	public boolean contains(T key)
	{
		return findNode(n_root, key) != null;
	}
	
	public void setRoot(BinaryNode<T> node)
	{
		if (node != null) node.setParent(null);
		n_root = node;
	}
	
	/** If this tree already contains the key, nothing is added. */
	public BinaryNode<T> add(T key)
	{
		BinaryNode<T> node = null;
		
		if (n_root == null)
			setRoot(node = new BinaryNode<T>(key));
		else
			node = addRec(n_root, key);
		
		return node;
	}
	
	private BinaryNode<T> addRec(BinaryNode<T> node, T key)
	{
		int diff = key.compareTo(node.getKey());
		BinaryNode<T> child, newNode = null;
		
		if (diff < 0)
		{
			if ((child = node.getLeftChild()) == null)
				node.setLeftChild(newNode = new BinaryNode<T>(key));
			else
				newNode = addRec(child, key);
		}
		else if (diff > 0)
		{
			if ((child = node.getRightChild()) == null)
				node.setRightChild(newNode = new BinaryNode<T>(key));
			else
				newNode = addRec(child, key);
		}
		
		return newNode;
	}
	
	/** @return the removed node if the specific key exists; otherwise, {@code false}. */
	public BinaryNode<T> remove(T key)
	{
		BinaryNode<T> node = findNode(n_root, key);
		
		if (node != null)
		{
			if (node.hasBothChildren())
				removeHibbard(node);
			else
				removeSelf(node);
		}
		
		return node;
	}
	
	protected BinaryNode<T> removeHibbard(BinaryNode<T> node)
	{
		BinaryNode<T> successor = node.getRightChild();
		BinaryNode<T> min = findMinNode(successor);
		BinaryNode<T> parent = min.getParent();
		
		min.setLeftChild(node.getLeftChild());
		
		if (min != successor)
		{
			parent.setLeftChild(min.getRightChild());
			min.setRightChild(successor);
		}
		
		replaceChild(node, min);
		return parent;
	}
	
	protected BinaryNode<T> removeSelf(BinaryNode<T> node)
	{
		BinaryNode<T> parent = node.getParent();
		BinaryNode<T> child  = null;
		
		if      (node.hasLeftChild())	child = node.getLeftChild();
		else if (node.hasRightChild())	child = node.getRightChild();
		replaceChild(node, child);
		
		return parent;
	}
	
	private void replaceChild(BinaryNode<T> oldNode, BinaryNode<T> newNode)
	{
		if (oldNode == n_root)
			setRoot(newNode);
		else
			oldNode.getParent().replaceChild(oldNode, newNode);
	}
	
//	============================== Helper methods ==============================
	
	/** @return the node with the minimum key under the subtree of {@code node}. */
	protected BinaryNode<T> findMinNode(BinaryNode<T> node)
	{
		return node.hasLeftChild() ? findMinNode(node.getLeftChild()) : node; 
	}
	
	/** @return the node with the maximum key under the subtree of {@code node}. */
	protected BinaryNode<T> findMaxNode(BinaryNode<T> node)
	{
		return node.hasRightChild() ? findMaxNode(node.getRightChild()) : node; 
	}
	
	/** @return the node with the specific key if exists; otherwise, {@code null}. */
	protected BinaryNode<T> findNode(BinaryNode<T> node, T key)
	{
		if (node == null) return null;
		int diff = key.compareTo(node.getKey());
		
		if (diff < 0)
			return findNode(node.getLeftChild(), key);
		else if (diff > 0)
			return findNode(node.getRightChild(), key);
		else
			return node;		
	}
	
	public String toString()
	{
		return (n_root != null) ? n_root.toString() : "null";
	}
}
