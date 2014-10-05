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

import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public interface IAutocomplete<T>
{
	/**
	 * @param prefix the prefix of candidate words to return.
	 * @return the list of candidate words for the specific prefix.
	 */
	List<String> getCandidates(String prefix);
	
	/**
	 * Memorize the specific candidate word for the specific prefix.
	 * @param prefix the prefix.
	 * @param candidate the selected candidate for the prefix.
	 */
	void pickCandidate(String prefix, String candidate);
	
	/** @return the previously inserted value if the key already exists; otherwise, the new value. */
	T put(String key, T value);
}