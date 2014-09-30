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
package edu.emory.mathcs.cs323.dynamic.hanoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DHanoi extends AbstractHanoi
{
	@Override
	public List<String> solve(int n, char source, char intermediate, char destination)
	{
		Map<String,int[]> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		solve(map, list, n, source, intermediate, destination);
		return list;
	}
	
	private void solve(Map<String,int[]> map, List<String> list, int n, char source, char intermediate, char destination)
	{
		if (n == 0) return;
		int fromIndex = list.size();
		
		int[] sub = map.get(getStep(n-1, source, intermediate));
		if (sub != null)	addAll(list, sub[0], sub[1]);
		else				solve(map, list, n-1, source, destination, intermediate);		
		
		String step = getStep(n, source, destination);
		list.add(step);
		
		sub = map.get(getStep(n-1, intermediate, destination));
		if (sub != null)	addAll(list, sub[0], sub[1]);
		else				solve(map, list, n-1, intermediate, source, destination);
		
		if (!map.containsKey(step))
			map.put(step, new int[]{fromIndex, list.size()});
	}
	
	private void addAll(List<String> list, int fromIndex, int toIndex)
	{
		for (int i=fromIndex; i<toIndex; i++)
			list.add(list.get(i));
	}
}
