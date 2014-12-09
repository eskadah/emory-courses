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

import java.util.List;

import edu.emory.mathcs.cs323.graph.Edge;
import edu.emory.mathcs.cs323.graph.Graph;
import edu.emory.mathcs.cs323.graph.Subgraph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class FordFulkerson extends MFAlgorithm
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
		Subgraph sub;
		double min;
		
		while ((sub = getAugmentingPath(graph, mf, new Subgraph(), source, target)) != null)
		{
			min = getMin(mf, sub.getEdges());
			mf.updateResidual(sub.getEdges(), min);
			updateBackward(graph, sub, mf, min);
		}
		
		return mf;
	}
	
	protected void updateBackward(Graph graph, Subgraph sub, MaxFlow mf, double min)
	{
		boolean found;
		
		for (Edge edge : sub.getEdges())
		{
			found = false;
			
			for (Edge rEdge : graph.getIncomingEdges(edge.getSource()))
			{
				if (rEdge.getSource() == edge.getTarget())
				{
					mf.updateResidual(rEdge, -min);
					found = true;
					break;
				}
			}
			
			if (!found)
			{
				Edge rEdge = graph.setDirectedEdge(edge.getTarget(), edge.getSource(), edge.getWeight());
				mf.updateResidual(rEdge, -min);
			}
		}
	}
	
	private double getMin(MaxFlow mf, List<Edge> path)
	{
		double min = mf.getResidual(path.get(0));
		int i, size = path.size();

		for (i=1; i<size; i++)
			min = Math.min(min, mf.getResidual(path.get(i)));
		
		return min;
	}
	
	private Subgraph getAugmentingPath(Graph graph, MaxFlow mf, Subgraph sub, int source, int target) 
	{
		if (source == target) return sub;
		Subgraph tmp;
		
		for (Edge edge : graph.getIncomingEdges(target))
		{
			if (sub.contains(edge.getSource()))	continue;	// cycle
			if (mf.getResidual(edge) <= 0)		continue;	// no residual
			tmp = new Subgraph(sub);
			tmp.addEdge(edge);
			
			tmp = getAugmentingPath(graph, mf, tmp, source, edge.getSource());
			if (tmp != null) return tmp;
		}
		
		return null;
	}
}
