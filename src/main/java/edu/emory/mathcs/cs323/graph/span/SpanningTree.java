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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.emory.mathcs.cs323.graph.Edge;
import edu.emory.mathcs.utils.MathUtils;
import edu.emory.mathcs.utils.Pair;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class SpanningTree
{
	private List<Edge> l_edges;
	private double d_weight;
	
	public SpanningTree()
	{
		l_edges = new ArrayList<>();
	}
	
	public int size()
	{
		return l_edges.size();
	}
	
	public double getTotalWeight()
	{
		return d_weight;
	}
	
	public List<Edge> getEdges()
	{
		return l_edges;
	}
	
	public void addEdge(Edge edge)
	{
		l_edges.add(edge);
		d_weight += edge.getWeight();
	}
	
	public Pair<List<Set<Integer>>,List<List<Edge>>> handleCycles()
	{
		List<Set<Integer>> forest = new ArrayList<>();
		List<List<Edge>> cycles = new ArrayList<>();
		Map<Integer,Edge> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		List<Edge> tmp = new ArrayList<>();
		Edge curr = null;

		for (Edge edge : l_edges)
			map.put(edge.getSource(), edge);
		
		while (!map.isEmpty())
		{
			if (curr == null) curr = getEdge(map);
			tmp.add(curr);
			set.add(curr.getSource());
			
			if (set.contains(curr.getTarget()))		// cycle
			{
				cycles.add(tmp);
				forest.add(set);
				curr = null;
				
				removeAll(map, set);
				tmp = new ArrayList<>();
				set = new HashSet<>();
			}
			else if ((curr = map.get(curr.getTarget())) == null)	// disjoint
			{
				removeAll(map, set);
				tmp = new ArrayList<>();
				set = new HashSet<>();
			}
		}
		
		return new Pair<>(forest, cycles);
	}
	
	private Edge getEdge(Map<Integer,Edge> map)
	{
		for (Edge edge : map.values())
			return edge;
		
		return null;
	}
	
	private void removeAll(Map<Integer,Edge> map, Set<Integer> set)
	{
		for (int source : set) map.remove(source);
	}
	
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		int size = MathUtils.getMaxBit(size());
		
		for (Edge edge : l_edges)
			build.append(String.format("\n%"+size+"d <- %"+size+"d : %f", edge.getTarget(), edge.getSource(), edge.getWeight()));
		
		return build.substring(1);
	}
}
