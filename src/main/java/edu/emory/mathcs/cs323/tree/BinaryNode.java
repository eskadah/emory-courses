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
public class BinaryNode<T extends Comparable<T>>
{
	private T t_key;
	private int i_height;	// height of this node in the tree
	private BinaryNode<T> n_parent;
	private BinaryNode<T> n_leftChild;
	private BinaryNode<T> n_rightChild;
	
	public BinaryNode(T key)
	{
		t_key        = key;
		i_height     = 1;
		n_parent     = null;
		n_leftChild  = null;
		n_rightChild = null;
	}
	
	public T getKey()
	{
		return t_key;
	}
	
	public int getHeight()
	{
		return i_height;
	}
	
	public BinaryNode<T> getParent()
	{
		return n_parent;
	}
	
	public BinaryNode<T> getLeftChild()
	{
		return n_leftChild;
	}
	
	public BinaryNode<T> getRightChild()
	{
		return n_rightChild;
	}
	
	public boolean hasParent()
	{
		return n_parent != null;
	}
	
	public boolean hasLeftChild()
	{
		return n_leftChild != null;
	}
	
	public boolean hasRightChild()
	{
		return n_rightChild != null;
	}
	
	public boolean hasBothChildren()
	{
		return hasLeftChild() && hasRightChild();
	}
	
	public int getBalanceFactor()
	{
		if (hasBothChildren())
			return n_leftChild.getHeight() - n_rightChild.getHeight();
		else if (hasLeftChild())
			return n_leftChild.getHeight();
		else if (hasRightChild())
			return -n_rightChild.getHeight();
		else
			return 0;
	}
	
	private void updateHeight(BinaryNode<T> node, int height)
	{
		if (node != null && node.i_height < height)
		{
			node.i_height = height;
			updateHeight(node.getParent(), height+1);	
		}
	}
	
	public void resetHeight()
	{
		resetHeightAux(this);
	}
	
	private void resetHeightAux(BinaryNode<T> node)
	{
		if (node != null)
		{
			int lh = node.hasLeftChild()  ? node.getLeftChild() .getHeight() : 0;
			int rh = node.hasRightChild() ? node.getRightChild().getHeight() : 0;
			node.i_height = (lh > rh) ? lh + 1 : rh + 1;
			resetHeightAux(node.getParent());
		}
	}
	
	public void setParent(BinaryNode<T> node)
	{
		n_parent = node;
	}
	
	public void setLeftChild(BinaryNode<T> node)
	{
		if (node != null)
		{
			updateHeight(this, node.getHeight()+1);
			replaceParent(node);
		}
		
		n_leftChild = node;
	}
	
	public void setRightChild(BinaryNode<T> node)
	{
		if (node != null)
		{
			updateHeight(this, node.getHeight()+1);
			replaceParent(node);
		}
		
		n_rightChild = node;
	}
	
	private void replaceParent(BinaryNode<T> node)
	{
		if (node.hasParent()) node.getParent().replaceChild(node, null);
		node.n_parent = this;
	}
	
	public void replaceChild(BinaryNode<T> oldNode, BinaryNode<T> newNode)
	{
		if      (n_leftChild  == oldNode) 	setLeftChild (newNode);
		else if (n_rightChild == oldNode)	setRightChild(newNode);
	}
	
	@Override
	public String toString()
	{
		return t_key+":"+i_height + " -> (" + n_leftChild +", " + n_rightChild +")";
	}
}