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
package edu.emory.mathcs.cs323.graph.flow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.emory.mathcs.cs323.graph.Edge;
import edu.emory.mathcs.cs323.graph.Graph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class FordFulkerson extends MaximumFlow
{
	/**
	 * @param graph a graph.
	 * @param source the source vertex.
	 * @param target the target vertex.
	 * @return the maximum flow from the source to the target vertices.
	 */
	public MaxFlow getMaximumFlow(Graph graph, int source, int target)
	{
		MaxFlow mf = new MaxFlow(graph);
		List<Edge> path;
		double min;
		
		while ((path = getPathDF(graph, mf, new ArrayList<>(), new HashSet<>(), source, target)) != null)
		{
			min = Collections.min(path).getWeight();
			mf.updateResidual(path, min);
		}
		
		return mf;
	}
	
	private List<Edge> getPathDF(Graph graph, MaxFlow mf, List<Edge> path, Set<Integer> visited, int source, int target) 
	{
		if (source == target) return path;
		Set<Integer> set;
		List<Edge> list;
		
		for (Edge edge : graph.getIncomingEdges(target))
		{
			if (visited.contains(edge.getSource())) continue;			// cycle
			if (edge.getWeight() - mf.getResidual(edge) <= 0) continue;	// no capacity
			list = new ArrayList<Edge>(path);
			set  = new HashSet<Integer>(visited);
			add(list, set, edge);
			
			list = getPathDF(graph, mf, list, set, source, edge.getSource());
			if (list != null) return list;
		}
		
		return null;
	}
	
	private void add(List<Edge> path, Set<Integer> visited, Edge edge)
	{
		path.add(edge);
		visited.add(edge.getSource());
	}
	
	@Test
	public void test()
	{
		Graph graph = new Graph(6);
		
		graph.setDirectedEdge(0, 1, 4);
		graph.setDirectedEdge(0, 2, 2);
		graph.setDirectedEdge(1, 3, 3);
		graph.setDirectedEdge(2, 3, 2);
		graph.setDirectedEdge(2, 4, 3);
		graph.setDirectedEdge(3, 2, 1);
		graph.setDirectedEdge(3, 5, 2);
		graph.setDirectedEdge(4, 5, 4);
		
		FordFulkerson n = new FordFulkerson();
		MaxFlow mf = n.getMaximumFlow(graph, 0, 5);
		System.out.println(mf.getFlow());
		System.out.println(mf.getEdges().toString());
	}
}
