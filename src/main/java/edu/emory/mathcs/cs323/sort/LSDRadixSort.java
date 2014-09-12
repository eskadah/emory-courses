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
public class LSDRadixSort extends AbstractSort<Integer>
{
	private List<Integer>[] g_buckets;
	
	@SuppressWarnings("unchecked")
	public LSDRadixSort()
	{
		g_buckets = (List<Integer>[])DSUtils.createEmptyListArray(10);
	}
	
	@Override
	public void sort(Integer[] array)
	{
		int div, index, maxExp = 1;
		Integer max = null;
		
		for (int exp=0; exp<maxExp; exp++)
		{
			div = (int)Math.pow(10, exp);
			
			for (Integer key : array)
			{
				g_buckets[getBucketIndex(key, div)].add(key);
				if (maxExp == 1 && (max == null || key.compareTo(max) > 0)) max = key;
			}
			
			index = 0;
			
			for (List<Integer> bucket : g_buckets)
			{
				for (Integer key : bucket)
					array[index++] = key;
				
				bucket.clear();
			}
			
			if (maxExp == 1) maxExp = getMaxExponential(max);
		}
	}
	
	private int getBucketIndex(Integer key, int div)
	{
		return (key / div) % 10;
	}
	
	private int getMaxExponential(Integer max)
	{
		int exp = 1;
		
		while (Math.pow(10, exp) <= max)
			exp++;
		
		return exp;
	}
}
