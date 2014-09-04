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
package edu.emory.mathcs.cs323.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class EagerPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T>
{
	private List<T> l_keys;
	
	public EagerPriorityQueue()
	{
		l_keys = new LinkedList<>();
	}

	@Override
	public int size()
	{
		return l_keys.size();
	}

	@Override
	public void add(T key)
	{
		int index = Collections.binarySearch(l_keys, key);
		if (index < 0) index = -(index + 1);
		l_keys.add(index, key);
	}

	@Override
	public T removeMax()
	{
		return l_keys.remove(l_keys.size()-1);
	}
}
