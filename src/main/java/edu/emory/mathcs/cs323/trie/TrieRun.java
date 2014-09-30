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
package edu.emory.mathcs.cs323.trie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class TrieRun
{
	private Trie<List<String>> g_trie;
	
	public TrieRun()
	{
		g_trie = new Trie<>();
	}
	
	public void putDictionary(InputStream in) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		
		while ((line = reader.readLine()) != null)
			g_trie.put(line.trim(), new ArrayList<String>());
	}
	
	public void run() throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		do 
		{
			System.out.print("Enter a prefix: ");
			s = reader.readLine();
			
			// TODO: print out the top 10 candidates
			System.out.println("she\nshell\nship\n...");
			
			System.out.print("Pick: ");
			s = reader.readLine();
			// TODO: update your Trie with this pick.
			
			System.out.println("\""+s+"\" is learned.\n");
		}
		while (true);
	}
	
	static public void main(String[] args) throws Exception
	{
		String dictFile = "/Users/jdchoi/Documents/Archive/Emory/webpage/public_html/courses/cs323-14f/dat/dict.txt";
		TrieRun tr = new TrieRun();
		
		tr.putDictionary(new FileInputStream(dictFile));
		tr.run();
	}
}
