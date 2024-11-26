package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

public class WordCensor extends SocialChannelDecorator {
    private static final String[] FORBIDDEN_WORDS = { "just", "dont", "lose" };

    @Override
    public void deliverMessage(String message) {
        String censoredMessage = message;
        for (String forbiddenWord : FORBIDDEN_WORDS) {
            String replacement = "";
            for (int i = 0; i < forbiddenWord.length(); i++) {
                replacement += "*";
            }
            censoredMessage = censoredMessage.replaceAll(forbiddenWord, replacement);
        }
        if(delegate != null) {
            delegate.deliverMessage(censoredMessage);
        }
    }
}
