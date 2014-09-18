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

import java.util.Collections;
import java.util.List;

import edu.emory.mathcs.utils.DSUtils;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractBucketSort<T extends Comparable<T>> extends AbstractSort<T>
{
	/** The list of buckets. */
	private List<T>[] g_buckets;
	/** if {@code true}, sort each bucket. */
	private final boolean b_sort; 
	
	/** @param bucketSize the total number of buckets. */
	@SuppressWarnings("unchecked")
	public AbstractBucketSort(int bucketSize, boolean sort)
	{
		g_buckets = (List<T>[])DSUtils.createEmptyListArray(bucketSize);
		b_sort = sort;
	}
	
	@Override
	public void sort(T[] array)
	{
		for (T key : array)
			g_buckets[getBucketIndex(key)].add(key);
		
		int index = 0;
		
		for (List<T> bucket : g_buckets)
		{
			if (b_sort)
				Collections.sort(bucket);
			
			for (T key : bucket)
				array[index++] = key;
			
			bucket.clear();
		}
	}
	
	/**
	 * @param key a comparable key.
	 * @return the index of the bucket that the key should be inserted.
	 */
	abstract protected int getBucketIndex(T key);
}
