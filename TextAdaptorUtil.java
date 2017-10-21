package markovModel;

public final class TextAdaptorUtil {
	static private String adaptedText;
	static public String adaptText(String inputText)
	{
		adaptedText = inputText.replaceAll("/n", " ");
		//adaptedText = adaptedText.replaceAll(".", ".");
		adaptedText = adaptedText.replaceAll(",", ", ");
		adaptedText = adaptedText.replaceAll("/r", " ");
		adaptedText = adaptedText.trim();
		while (adaptedText.indexOf("  ") != -1)
			adaptedText = adaptedText.replaceAll("  ", " ");
		adaptedText = adaptedText.concat(" ");
		return adaptedText;
	}
}
