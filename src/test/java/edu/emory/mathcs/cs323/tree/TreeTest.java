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
package edu.emory.mathcs.cs323.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import edu.emory.mathcs.utils.DSUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class TreeTest
{
	@Test
	public void testBinarySearchTree()
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(8);
		
		assertEquals(1, t.min().intValue());
		assertEquals(8, t.max().intValue());
		assertTrue(t.contains(7));
		assertFalse(t.contains(0));
		
		assertEquals(2, t.remove(2).getKey().intValue());	// no child
		assertEquals(3, t.remove(3).getKey().intValue());	// two children
		assertEquals(null, t.remove(2));
		t.add(2);
		assertEquals(1, t.remove(1).getKey().intValue());	// only right child
		assertEquals(4, t.remove(4).getKey().intValue());	// only left child
		assertEquals(5, t.remove(5).getKey().intValue());	// root
	}
	
	@Test
	public void testAccuracy()
	{
		testAccuracy(1000, 100, new BinarySearchTree<>());
		testAccuracy(1000, 100, new AVLTree<>());
	}
	
	void testAccuracy(final int ITERATIONS, final int SIZE, BinarySearchTree<Integer> t)
	{
		Random rand = new Random(0);
		List<Integer> list;
		
		for (int i=0; i<ITERATIONS; i++)
		{
			list = DSUtils.getRandomIntegerList(rand, SIZE);
			
			for (int key : list) t.add(key);
			for (int key : list) assertTrue(t.contains(key));
			
			assertEquals(t.max(), Collections.max(list));
			assertEquals(t.min(), Collections.min(list));
			
			Collections.shuffle(list);

			for (int key : list)
				assertEquals(key, t.remove(key).getKey().intValue());
		}
	}
}
