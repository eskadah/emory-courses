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
public class AVLNode<T extends Comparable<T>> extends AbstractBinaryNode<T,AVLNode<T>>
{
	private int i_height;
	
	public AVLNode(T key)
	{
		super(key);
		i_height = 1;
	}
	
	public int getHeight()
	{
		return i_height;
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
	
	public void setLeftChild(AVLNode<T> node)
	{
		super.setLeftChild(node);
		resetHeights();
	}
	
	public void setRightChild(AVLNode<T> node)
	{
		super.setRightChild(node);
		resetHeights();
	}
	
	public void resetHeights()
	{
		resetHeightsAux(this);
	}
	
	private void resetHeightsAux(AVLNode<T> node)
	{
		if (node != null)
		{
			int lh = node.hasLeftChild()  ? node.getLeftChild() .getHeight() : 0;
			int rh = node.hasRightChild() ? node.getRightChild().getHeight() : 0;
			node.i_height = (lh > rh) ? lh + 1 : rh + 1;
			if (node.getParent() != this) resetHeightsAux(node.getParent());
		}
	}
	
	public String toString()
	{
		return t_key+":"+i_height + " -> (" + n_leftChild +", " + n_rightChild +")";
	}
}
