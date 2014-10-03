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

import org.junit.Test;

import edu.emory.mathcs.cs323.dynamic.lcs.AbstractLCS;
import edu.emory.mathcs.cs323.dynamic.lcs.DLCS;
import edu.emory.mathcs.cs323.dynamic.lcs.RLCS;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LCSTest
{
	@Test
	public void testAccuracy()
	{
		String a = "ACGTCGTGT";
		String b = "CTAGTGGAG";
		
		AbstractLCS r = new RLCS();
		AbstractLCS d = new DLCS();
		System.out.println(r.get(a, b));
		System.out.println(d.get(a, b));
		
		a = "GAATGTCCTTTCTCTAAGTCCTAAG";
		b = "GGAGACTTACAGGAAAGAGATTCAGGATTCAGGAGGCCTACCATGAAGATCAAG";
	}
}
