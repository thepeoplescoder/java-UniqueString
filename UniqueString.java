package com.thepeoplescoder;

import java.util.*;

public class UniqueString
{
	/**
	 * Attempt at solving that one problem I was given today.
	 * Given a list of source strings, return a list of strings
	 * that are unique.
	 * 
	 * Equality constraints:
	 * 1) case doesn't matter.
	 * 2) word order doesn't matter.
	 * 
	 * The results should come out in the same order they appear in the source
	 * and we will use the most recent one we find.
	 * 
	 * @param source
	 * @return
	 */
	public static List<String> uniqueString(List<String> source)
	{
		// Keeps track of the first time we find a string with these words
		Map<Map<String, Integer>, Integer> positionMap =
				new HashMap<Map<String, Integer>, Integer>();
		
		// Keeps track of the most recent string containing these words
		Map<Map<String, Integer>, String> stringMap =
				new HashMap<Map<String, Integer>, String>();
		
		// To keep track of the next available position
		int position = 0;
		
		// Look at everything.  Iterate through the list
		// since we don't know what it's implementation is.
		for (String s : source)
		{
			Map<String, Integer> bag = stringToBag(s);
			if (positionMap.containsKey(bag))
			{
				stringMap.put(bag, s);
			}
			else
			{
				positionMap.put(bag, position++);
				stringMap.put(bag, s);
			}
		}
		
		List<String> result =
				new ArrayList<String>(Collections.<String>nCopies(position, null));
		
		for (Map<String, Integer> key : positionMap.keySet())
		{
			result.set(positionMap.get(key), stringMap.get(key));
		}
		
		
		return result;
	}
	
	public static Map<String, Integer> stringToBag(String s)
	{
		String[] words = s.toLowerCase().trim().split("\\s+");
		Map<String, Integer> bag = new HashMap<String, Integer>();
		
		for (String word : words)
		{
			if (bag.containsKey(word))
			{
				bag.put(word, bag.get(word) + 1);
			}
			else
			{
				bag.put(word, 1);
			}
		}
		
		return bag;
	}
	
	public static void main(String[] args)
	{
		String[] inputArray = new String[] { "it sunny is", "hello world", "Hello world", "it IS sunny", "   WORLD HELLO", "sunny it is" };
		List<String> input = Arrays.asList(inputArray);
		
		List<String> output = uniqueString(input);
		
		for (int index = 0; index < output.size(); index++)
		{
			System.out.println(output.get(index));
		}
	}
}
