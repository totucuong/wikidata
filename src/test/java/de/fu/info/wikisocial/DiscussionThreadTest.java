package de.fu.info.wikisocial;

import de.fu.info.wikisocial.wikidata.DiscussionThread;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by totucuong on 8/8/16.
 */
public class DiscussionThreadTest {
    private DiscussionThread discussion_thread;

    @Before
    public void setUp() {
        String content = "Op User:RobotMichiel1972/Items_zonder_rijksmonumentnummer een lijst met waarschijnlijk rijksmonumenten (aldus claim P31) maar geen nummer. Zijn er ruim 1500. Moeten dus handmatig worden gekoppeld (samengevoegd) aan bestaande items met het bijhorende nummer. Is daar nog iets aan te automatiseren, of handwerk? RobotMichiel1972 (talk) 10:54, 17 January 2015 (UTC)\n" +
                "\n" +
                "@Michiel1972:, hmm, die lijst is wel flink gegroeid. Het is volgens mij onder te verdelen in twee delen:\n" +
                "Artikelen en items die al bestonden voor de import, maar waar geen id voor was gevonden\n" +
                "Artikelen en items die zijn aangemaakt na de import waar vergeten is dat er al een item is voor elk Rijksmonument\n" +
                "Duplicaten komen altijd bovendrijven als er meer informatie aan items wordt toegevoegd. Bijvoorbeeld omdat er twee keer hetzelfde id wordt gebruikt of dat dezelfde foto op twee items zit. Het daadwerkelijk samenvoegen is meer iets voor mensen. Ik denk dat de volgende aanpak wel gaat werken:\n" +
                "Ik laat het foto import botje over de lijst rammelen (is nu gestart, zie bijvoorbeeld no label (Q172550))\n" +
                "We laten een bot over het gedeelte met een foto gaan en proberen het Rijksmonument van de foto te plukken (File:Oegstgeest Endegeest.jpg -> 31233)\n" +
                "We krijgen een mooi overzicht van constraint violations die mensen langzaam kunnen oplosen\n" +
                "Wat denk je ervan? Multichill (talk) 12:11, 17 January 2015 (UTC)\n" +
                "Oh leuk, het voorbeeld is meteen lastig. Multichill (talk) 12:14, 17 January 2015 (UTC)";
        discussion_thread = new DiscussionThread(content, "Multichill");

    }

    @Test
    public void testExtract_items() throws Exception {
        HashSet<String> items = discussion_thread.extract_items();
        assertEquals(1, items.size());
        assertTrue(items.contains("Q172550"));
    }

    @Test
    public void testExtract_properties() throws Exception {
        HashSet<String> properties = discussion_thread.extract_properties();
        assertEquals(1, properties.size());
        assertTrue(properties.contains("P31"));
    }

    @Test
    public void testExtract_users() throws Exception {
        HashSet<String> users = discussion_thread.extract_users();
        assertEquals(2, users.size());
        assertTrue(users.contains("Multichill"));
    }
}