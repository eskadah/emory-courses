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
package edu.emory.mathcs.cs323.graph.span;

import org.junit.Ignore;
import org.junit.Test;

import edu.emory.mathcs.cs323.graph.Graph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MinimumSpanningTreeTest
{
	@Test
	@Ignore
	public void testMinimumSpanningTree() throws Exception
	{
//		Scanner scanner = new Scanner(new FileInputStream("/Users/jdchoi/Emory/courses/CS323/tinyEWG.txt"));
//		Scanner scanner = new Scanner(new FileInputStream("/Users/jdchoi/Emory/courses/CS323/mediumEWG.txt"));
//		int V = scanner.nextInt(), E = scanner.nextInt();
//		int source, target;
//		double weight;
//		
//		Graph graph = new Graph(V);
//
//		for (int i=0; i<E; i++)
//		{
//			source = scanner.nextInt();
//			target = scanner.nextInt();
//			weight = scanner.nextDouble();
//			graph.setUndirectedEdge(source, target, weight);
//		}
		
		Graph graph = new Graph(5);
		graph.setUndirectedEdge(0, 1, 5-2);
		graph.setUndirectedEdge(0, 2, 5-1);
		graph.setUndirectedEdge(0, 3, 5-4);
		graph.setUndirectedEdge(0, 4, 5-3);
		graph.setUndirectedEdge(1, 2, 5-3);
		graph.setUndirectedEdge(2, 3, 5-1);
		graph.setUndirectedEdge(3, 4, 5-2);
		
		MSTAlgorithm prim = new MSTPrim();
		MSTAlgorithm kruskal = new MSTKruskal();
		SpanningTree tree;
		
		tree = prim.getMinimumSpanningTree(graph);
		System.out.println(tree);
		System.out.println(tree.getTotalWeight());
		
		tree = kruskal.getMinimumSpanningTree(graph);
		System.out.println(tree);
		System.out.println(tree.getTotalWeight());
	}
}
