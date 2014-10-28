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
package edu.emory.mathcs.cs323.graph;

import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.utils.MathUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Tree
{
	private List<Edge> l_edges;
	private double d_weight;
	
	public Tree()
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
	
	public void addEdge(Edge edge)
	{
		l_edges.add(edge);
		d_weight += edge.getWeight();
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
