package markovModel;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Model {
	private MapOfLists mol;
	
	Model()
	{
		mol = new MapOfLists();
	}
	Model(String text)
	{
		text = TextAdaptorUtil.adaptText(text);
		mol = new MapOfLists(text);
	}
	
	public void retrain(String text)
	{
		mol.clearDictionary();
		mol.fullfilDictionary(text);
	}
	public MapOfLists getMapOfLists()
	{
		return mol;
	}
	public void showMap()
	{
		for (Map.Entry<String, LinkedList<String>> entry: this.getMapOfLists().getMap().entrySet())
		{
			String uniqueWord = entry.getKey();
			System.out.print(uniqueWord + " : ");
			for (String nonUniqueWord: entry.getValue())
				System.out.print(nonUniqueWord + " ");
			System.out.println("");
		}
	}
	
	public String generateText(int words)
	{
		String generatedText = "";
		LinkedList<String> currentList;
		String currentWord = "";
		Random rand = new Random(System.currentTimeMillis());
		int counter = 0, limit = rand.nextInt(this.getMapOfLists().getMap().size());
		if (limit == 0)
			limit = (this.getMapOfLists().getMap().size())/2;
		for (Map.Entry<String, LinkedList<String>> entry : this.getMapOfLists().getMap().entrySet())
		{
			if (counter == limit )
				break;
			currentWord = entry.getKey();
			counter++;
		}
		if (currentWord == "" || currentWord == null)
		{
			System.err.println("Base word was null.");
		}
		for (int i = 0; i < words; i++)
		{
			generatedText = generatedText + currentWord + " ";
			currentList = this.getMapOfLists().getMap().get(currentWord);
			currentWord = currentList.get(rand.nextInt(currentList.size()));
			while(i == words - 1)
			{
				generatedText = generatedText + currentWord + " ";
				if (currentWord.contains("."))
					break;
				currentList = this.getMapOfLists().getMap().get(currentWord);
				currentWord = currentList.get(rand.nextInt(currentList.size()));
			}
			// Take wordBuffer, choice randomly depending on possibilities in it's lists what is the next wordBuffer; Do while i != words + currentWord != "smth" + "."
		}
		System.out.print("\n" + generatedText);
		return generatedText;
	}
	
}
