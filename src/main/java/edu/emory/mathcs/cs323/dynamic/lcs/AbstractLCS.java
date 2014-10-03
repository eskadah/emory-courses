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
public abstract class AbstractLCS
{
	/**
	 * @param a the first string.
	 * @param b the second string.
	 * @return a longest common sequence of the specific strings {@code a} and {@code b}.
	 */
	public String get(String a, String b)
	{
		return get(a.toCharArray(), b.toCharArray(), a.length()-1, b.length()-1);
//		return get(a.toCharArray(), b.toCharArray(), 0, 0);
	}
	
	protected abstract String get(char[] c, char[] d, int i, int j);
}

