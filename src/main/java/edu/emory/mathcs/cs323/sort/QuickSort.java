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





/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T>
{
	@Override
	public void sort(T[] array)
	{
		sort(array, 0, array.length-1);
	}
	
	/**
	 * @param beginIndex the beginning index of the 1st partition (inclusive).
	 * @param endIndex the ending index of the 2nd partition (inclusive).
	 */
	private void sort(T[] array, int beginIndex, int endIndex)
	{
		if (beginIndex >= endIndex) return;
		int middleIndex = partition(array, beginIndex, endIndex);
		sort(array, beginIndex, middleIndex-1);
		sort(array, middleIndex+1, endIndex);
	}
	
	/**
	 * @param beginIndex the beginning index of the 1st partition (inclusive).
	 * @param endIndex the ending index of the 2nd partition (inclusive).
	 */
	private int partition(T[] array, int beginIndex, int endIndex)
	{
		int fst = beginIndex, snd = endIndex + 1;
		
		while (true)
		{
			while (compareTo(array, beginIndex, ++fst) >= 0 && fst < endIndex);		// fst > pivot
			while (compareTo(array, beginIndex, --snd) <= 0 && snd > beginIndex);	// snd < pivot
			if (fst >= snd) break;
			swap(array, fst, snd);
		}
		
		swap(array, beginIndex, snd);
		return snd;
	}
}
