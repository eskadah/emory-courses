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
package edu.emory.mathcs.cs323.dynamic.lcs;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DLCS extends AbstractLCS
{
	@Override
	protected String get(char[] c, char[] d, int i, int j)
	{
		return getAux(c, d, i, j, createTable(c, d));
	}
	
	private String getAux(char[] c, char[] d, int i, int j, int[][] table)
	{
		if (i < 0 || j < 0)
			return "";
		
		if (c[i] == d[j])
			return getAux(c, d, i-1, j-1, table) + c[i];
		
		if (i == 0)	return getAux(c, d, i, j-1, table);
		if (j == 0)	return getAux(c, d, i-1, j, table);
		
		return (table[i-1][j] > table[i][j-1]) ? getAux(c, d, i-1, j, table) : getAux(c, d, i, j-1, table);
	}
	
	private int[][] createTable(char[] c, char[] d)
	{
		final int N = c.length, M = d.length;
		int[][] table = new int[N][M];
		
		for (int i=1; i<N; i++)
			for (int j=1; j<M; j++)
				table[i][j] = (c[i] == d[j]) ? table[i-1][j-1] + 1 : Math.max(table[i-1][j], table[i][j-1]);
		
		return table;
	}
}

