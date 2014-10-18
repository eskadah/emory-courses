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

import edu.emory.mathcs.cs323.tree.node.AbstractBinaryNode;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractBalancedBinarySearchTree<T extends Comparable<T>, N extends AbstractBinaryNode<T,N>> extends AbstractBinarySearchTree<T,N>
{
	@Override
	public N add(T key)
	{
		N node = super.add(key);
		balance(node);
		return node;
	}
	
	@Override
	public N remove(T key)
	{
		N node = findNode(n_root, key);
		
		if (node != null)
		{
			N lowest = node.hasBothChildren() ? removeHibbard(node) : removeSelf(node);
			if (lowest != null && lowest != node) balance(lowest);
		}
		
		return node;
	}

	/** Preserves the balance of the specific node and its ancestors after {@code #add(Comparable)}. */
	protected abstract void balance(N node);
	
	protected void rotateLeft(N node)
	{
		N child = node.getRightChild();
		
		node.setRightChild(child.getLeftChild());
		
		if (node.hasParent())
			node.getParent().replaceChild(node, child);
		else
			setRoot(child);
		
		child.setLeftChild(node);
	}
	
	protected void rotateRight(N node)
	{
		N child = node.getLeftChild();
		
		node.setLeftChild(child.getRightChild());
		
		if (node.hasParent())
			node.getParent().replaceChild(node, child);
		else
			setRoot(child);
		
		child.setRightChild(node);
	}
}
