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
package edu.emory.mathcs.cs323.trie;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Trie<T>
{
	private TrieNode<T> n_root;
	
	public Trie()
	{
		n_root = new TrieNode<>(null, (char)0);
	}
	
	public T get(String key)
	{
		TrieNode<T> node = find(key);
		return (node != null) ? node.getValue() : null;
	}
	
	public boolean contains(String key)
	{
		return get(key) != null;
	}
	
	/** @return the previously inserted value if the key already exists; otherwise, the new value. */
	public T put(String key, T value)
	{
		char[] array = key.toCharArray();
		int i, len = key.length();
		TrieNode<T> node = n_root;
		
		for (i=0; i<len ; i++)
			node = node.addChild(array[i]);

		if (!node.hasValue())
		{
			node.setValue(value);
			return value;
		}
		else
			return node.getValue();
	}
	
	public T remove(String key)
	{
		TrieNode<T> node = find(key);
		
		if (node == null || !node.hasValue())
			return null;
		
		if (node.hasChildren())
			return node.setValue(null);

		TrieNode<T> parent = node.getParent();

		while (parent != null)
		{
			parent.removeChild(node.getKey());
			
			if (parent.hasChildren() || parent.hasValue())
				break;
			else
			{
				node = parent;
				parent = parent.getParent();
			}	
		}

		return null;
	}
	
	private TrieNode<T> find(String key)
	{
		char[] array = key.toCharArray();
		int i, len = key.length();
		TrieNode<T> node = n_root;
		
		for (i=0; i<len ; i++)
		{
			node = node.getChild(array[i]);
			if (node == null) return null;
		}
		
		return node;	
	}
}
