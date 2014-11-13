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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.emory.mathcs.cs323.assignment.hw3.MSTChoi;
import edu.emory.mathcs.cs323.graph.Edge;
import edu.emory.mathcs.cs323.graph.Graph;
import edu.emory.mathcs.utils.StringUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MSTAllTest
{
	@Test
	public void test()
	{
		Graph[] graphs = new Graph[2];
		graphs[0] = getGraph0();
		graphs[1] = getGraph1();

		int i, correct = 0, total = graphs.length;
		MSTAll gold = new MSTChoi();
		MSTAll system = new MSTChoi();
		Graph graph;
		boolean b;
		
		for (i=0; i<total; i++)
		{
			graph = graphs[i];
			b = false;
			
			try
			{
				if (test(gold.getMinimumSpanningTrees(graph), system.getMinimumSpanningTrees(graph)))
				{
					b = true;
					correct++;
				}
			}
			catch (Exception e) {}
			
			System.out.printf("%2d: %b\n", (i+1), b);
		}
		
		System.out.printf("Score: %d/%d\n", correct, total);
	}
	
	Graph getGraph0()
	{
		Graph graph = new Graph(5);
		
		graph.setUndirectedEdge(0, 1, 3);
		graph.setUndirectedEdge(0, 2, 4);
		graph.setUndirectedEdge(0, 3, 1);
		graph.setUndirectedEdge(0, 4, 2);
		graph.setUndirectedEdge(1, 2, 2);
		graph.setUndirectedEdge(2, 3, 4);
		graph.setUndirectedEdge(3, 4, 3);
		
		return graph;
	}
	
	Graph getGraph1()
	{
		Graph graph = new Graph(4);

		graph.setUndirectedEdge(0, 1, 1);
		graph.setUndirectedEdge(0, 2, 1);
		graph.setUndirectedEdge(0, 3, 1);
		graph.setUndirectedEdge(1, 2, 1);
		graph.setUndirectedEdge(1, 3, 1);
		graph.setUndirectedEdge(2, 3, 1);
		
		return graph;
	}

	boolean test(List<SpanningTree> gTrees, List<SpanningTree> sTrees)
	{
		if (sTrees == null) return false;
		if (gTrees.size() != sTrees.size()) return false;
		
		Set<String> g = getSequenceSet(gTrees);
		Set<String> s = getSequenceSet(sTrees);
		
		if (g.size() != s.size()) return false;
		g.removeAll(s);
		if (!g.isEmpty()) return false;
		
		return true;
	}
	
	public Set<String> getSequenceSet(List<SpanningTree> trees)
	{
		Set<String> set = new HashSet<>();
		
		for (SpanningTree tree : trees)
			set.add(getSequence(tree));
		
		return set;
	}

	public String getSequence(SpanningTree tree)
	{
		List<String> sequence = new ArrayList<>();
		String s;
		
		for (Edge edge : tree.getEdges())
		{
			if (edge.getSource() < edge.getTarget())
				s = edge.getSource()+"-"+edge.getTarget();
			else
				s = edge.getTarget()+"-"+edge.getSource();
			
			sequence.add(s);
		}
		
		Collections.sort(sequence);
		return StringUtils.join(sequence, " ").trim();
	}
}
