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
package edu.emory.mathcs.cs323.dynamic;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import edu.emory.mathcs.cs323.dynamic.fibonacci.AbstractFibonacci;
import edu.emory.mathcs.cs323.dynamic.fibonacci.DFibonacci;
import edu.emory.mathcs.cs323.dynamic.fibonacci.LFibonacci;
import edu.emory.mathcs.cs323.dynamic.fibonacci.RFibonacci;
import edu.emory.mathcs.utils.StringUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class FibonacciTest
{
	@Test
	public void testAccuracy()
	{
		int[] gold = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
		
		testAccuracy(new RFibonacci()  , gold);
		testAccuracy(new LFibonacci()  , gold);
		testAccuracy(new DFibonacci(12), gold);
	}
	
	void testAccuracy(AbstractFibonacci f, int[] gold)
	{
		for (int i=0; i<gold.length; i++)
			assertEquals(f.get(i), gold[i]);
	}
	
	@Test
	@Ignore
	public void testSpeed()
	{
		final int ITERATIONS = 100;
		final int MAX_K      = 35;
		
		AbstractFibonacci recursive = new RFibonacci();
		AbstractFibonacci loop = new LFibonacci();
		AbstractFibonacci dynamic = new DFibonacci(MAX_K+1);
		
		for (int k=2; k<MAX_K; k++)
			 System.out.println(testSpeed(ITERATIONS, k, loop, dynamic, recursive));
	}
	
	String testSpeed(final int iterations, final int k, AbstractFibonacci... solver)
	{
		long[] times = new long[solver.length];
		int i, j, len = solver.length;
		long st, et;
		
		for (i=0; i<len; i++)
		{
			st = System.currentTimeMillis();
			for (j=0; j<iterations; j++) solver[i].get(k);
			et = System.currentTimeMillis();
			times[i] = et - st;
		}
		
		return k+"\t"+StringUtils.join(times, "\t");
	}
}