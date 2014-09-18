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
package edu.emory.mathcs.cs323.sort;

import java.util.List;

import edu.emory.mathcs.utils.DSUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractLSDRadixSort<T extends Comparable<T>> extends AbstractSort<T>
{
	private List<T>[] g_buckets;
	
	@SuppressWarnings("unchecked")
	public AbstractLSDRadixSort(int bucketSize)
	{
		g_buckets = (List<T>[])DSUtils.createEmptyListArray(bucketSize);
	}
	
	@Override
	public void sort(T[] array)
	{
		int pointer, index, iterations = 1;
		T MAX = null;
		
		for (int exp=0; exp<iterations; exp++)
		{
			pointer = (int)Math.pow(10, exp);
			
			for (T key : array)
			{
				g_buckets[getBucketIndex(key, pointer)].add(key);
				if (iterations == 1 &&  key.compareTo(MAX) > 0) MAX = key;
			}
			
			index = 0;
			
			for (List<T> bucket : g_buckets)
			{
				for (T key : bucket)
					array[index++] = key;
				
				bucket.clear();
			}
			
			if (iterations == 1) iterations = getIterations(MAX);
		}
	}

	abstract protected int getIterations(T max);
	abstract protected int getBucketIndex(T key, int pointer);
}