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
package edu.emory.mathcs.cs323.dynamic.fibonacci;

import java.util.Arrays;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DFibonacci extends AbstractFibonacci
{
	int[] g_table;
	
	public DFibonacci(int tableSize)
	{
		g_table = new int[tableSize];
	}
	
	@Override
	public int get2p(int k)
	{
		initTable(k);
		return get2pAux(k);
	}
	
	private int get2pAux(int k)
	{
		if (g_table[k] < 0)
			g_table[k] = get2pAux(k-1) + get2pAux(k-2);
		
		return g_table[k];
	}

	private void initTable(int k)
	{
		g_table[0] = 0;
		g_table[1] = 1;
		Arrays.fill(g_table, 2, k+1, -1);
	}
}
