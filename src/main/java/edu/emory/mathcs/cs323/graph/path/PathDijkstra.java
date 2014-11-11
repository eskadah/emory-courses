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
package edu.emory.mathcs.cs323.graph.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Test;

import edu.emory.mathcs.cs323.graph.Graph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class PathDijkstra
{
	public List<Integer> getShortestPath(Graph graph, int source, int target)
	{
		PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>();
		Integer[] previous = new Integer[graph.size()];
		double[] distances = new double[graph.size()];
		Set<Integer> visited = new HashSet<>();
		
		init(distances, previous, target);
		queue.add(new VertexDistancePair(target, 0));
		
		while (!queue.isEmpty())
		{
			// To be filled.
		}
		
		return getPath(previous, source);
	}
	
	private void init(double[] distances, Integer[] previous, int target)
	{
		for (int i=0; i<distances.length; i++)
		{
			if (i == target)
				distances[i] = 0;
			else
			{
				distances[i] = Double.MAX_VALUE;
				previous[i]  = null;
			}
		}
	}
	
	private List<Integer> getPath(Integer[] previous, int source)
	{
		List<Integer> path = new ArrayList<>();
		
		while (previous[source] != null)
		{
			path.add(source);
			source = previous[source];
		}
		
		path.add(source);
		return path;
	}
	
	private class VertexDistancePair implements Comparable<VertexDistancePair>
	{
		int    vertex;
		double distance;
		
		public VertexDistancePair(int vertex, double distance)
		{
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(VertexDistancePair p)
		{
			double diff = this.distance - p.distance;
			if (diff > 0) return  1;
			if (diff < 0) return -1;
			return 0;
		}
	}
}
