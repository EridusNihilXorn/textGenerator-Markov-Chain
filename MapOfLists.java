package markovModel;

import java.util.HashMap;
import java.util.LinkedList;

public class MapOfLists  {
	//Map of unique words connected to lists with non-unique words
	//that go right after unique word in text.
	private HashMap<String, LinkedList<String>> dictionary; 
	public MapOfLists()
	{
		dictionary = new HashMap<String, LinkedList<String>>();
	}
	public MapOfLists(String text)
	{
		this();
		this.fullfilDictionary(text);
	}
	public void fullfilDictionary(String text)
	{
		text = TextAdaptorUtil.adaptText(text);
		int word1End, word2End;
		for (int i = 0; i < text.length(); i++)
		{
			word1End = text.indexOf(" ", i);
			//System.out.println("I: " + i + " Word1End-1: " + (word1End-1) + " :" + text.substring(i, word1End) + "/");
			if (word1End == -1)
				break;
			word2End = text.indexOf(" ", word1End + 1);
			//if(word2End != -1)
				//System.out.println("word1End+1: " + (word1End+1) + " Word2 End: " + (word2End-1) + " :" + text.substring(word1End+1, word2End)  + "/");
			//Found end positions of 2 words.
			if (word2End == -1)
			{ // If it is end of a text, connect end -> begin words.
				word2End = text.indexOf(" ");
				//If unique word1 was not found in dictionary, put it and allocate memory for it's list.
				if (!(dictionary.containsKey(text.substring(i, word1End))))
					dictionary.put(text.substring(i, word1End), new LinkedList<String>());
				//System.out.println("///I: " + i + " Word1End-1: " + (word1End-1) + " :" + text.substring(i, word1End));
				dictionary.get(text.substring(i, word1End)).add(text.substring(0, word2End));
				break;
			}
			if (!(dictionary.containsKey(text.substring(i, word1End))))
				dictionary.put(text.substring(i, word1End), new LinkedList<String>());
			//Add non-unique following word2 to list of unique word1.
			dictionary.get(text.substring(i, word1End)).add(text.substring(word1End+1, word2End));
			i = word1End;
		}
	}
	public void clearDictionary()
	{
		dictionary.clear();
	}
	public HashMap<String, LinkedList<String>> getMap()
	{
		return this.dictionary;
	}
}
