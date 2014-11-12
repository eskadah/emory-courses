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

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Test;

import edu.emory.mathcs.cs323.graph.Edge;
import edu.emory.mathcs.cs323.graph.Graph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class PathDijkstra
{
	public Integer[] getShortestPath(Graph graph, int source, int target)
	{
		PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>();
		Integer[] previous = new Integer[graph.size()];
		double[] distances = new double[graph.size()];
		Set<Integer> visited = new HashSet<>();

		init(distances, previous, target);
		queue.add(new VertexDistancePair(target, 0));
		
		while (!queue.isEmpty())
		{
			VertexDistancePair u = queue.poll();
			visited.add(u.vertex);
			
			for (Edge edge : graph.getIncomingEdges(u.vertex))
			{
				int v = edge.getSource();
				
				if (!visited.contains(v))
				{
					double dist = distances[u.vertex] + edge.getWeight();
					
					if (dist < distances[v])
					{
						distances[v] = dist;
						previous [v] = u.vertex;
						queue.add(new VertexDistancePair(v, dist));
					}
				}
			}
		}
		
		return previous;
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
	
	private class VertexDistancePair implements Comparable<VertexDistancePair>
	{
		public int    vertex;
		public double distance;
		
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
	
	@Test
	public void test()
	{
		PathDijkstra d = new PathDijkstra();
		Graph g = new Graph(6);
		
		g.setDirectedEdge(0, 1, 4);
		g.setDirectedEdge(0, 2, 2);
		g.setDirectedEdge(1, 2, 5);
		g.setDirectedEdge(1, 3, 10);
		g.setDirectedEdge(2, 4, 3);
		g.setDirectedEdge(3, 5, 3);
		g.setDirectedEdge(4, 3, 4);
		g.setDirectedEdge(4, 5, 9);
		
		System.out.println(d.getShortestPath(g, 0, 5));
//		g = new Graph(5);
//		
//		g.setDirectedEdge(0, 1, 2);
//		g.setDirectedEdge(0, 2, 8);
//		g.setDirectedEdge(0, 3, 5);
//		g.setDirectedEdge(1, 2, 1);
//		g.setDirectedEdge(2, 4, 3);
//		g.setDirectedEdge(3, 4, 4);
//		
//		System.out.println(d.getShortestPath(g, 0, 4));		
	}
}
